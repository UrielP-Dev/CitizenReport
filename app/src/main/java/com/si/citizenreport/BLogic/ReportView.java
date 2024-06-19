package com.si.citizenreport.BLogic;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.si.citizenreport.ApiReport;
import com.si.citizenreport.R;
import com.si.citizenreport.connection.APIConnection;
import com.si.citizenreport.model.Report;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReportView extends AppCompatActivity implements OnMapReadyCallback {
    GoogleMap mMap;
    Button camaraButton, submitButton;
    ImageView imageView;
    EditText descriptionEditText, plateEditText;
    Bitmap capturedImage;
    private LatLng currentLatLng;

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private FusedLocationProviderClient fusedLocationClient;
    private ApiReport apiReport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_view);

        camaraButton = findViewById(R.id.camaraButton);
        submitButton = findViewById(R.id.submitButton);
        imageView = findViewById(R.id.photoImageView);
        descriptionEditText = findViewById(R.id.descriptionEditText);
        plateEditText = findViewById(R.id.plateEditText);

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }

        apiReport = APIConnection.getRetrofitInstance().create(ApiReport.class);

        camaraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCamara();
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    submitReport();
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(ReportView.this, "Error al enviar el reporte", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
            fusedLocationClient.getLastLocation()
                    .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            if (location != null) {
                                currentLatLng = new LatLng(location.getLatitude(), location.getLongitude());
                                mMap.addMarker(new MarkerOptions().position(currentLatLng).title("Ubicación Actual"));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 15));
                            }
                        }
                    });
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    LOCATION_PERMISSION_REQUEST_CODE);
        }

        mMap.getUiSettings().setAllGesturesEnabled(false);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED) {
                    mMap.setMyLocationEnabled(true);
                    fusedLocationClient.getLastLocation()
                            .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                                @Override
                                public void onSuccess(Location location) {
                                    if (location != null) {
                                        LatLng currentLocation = new LatLng(location.getLatitude(), location.getLongitude());
                                        mMap.addMarker(new MarkerOptions().position(currentLocation).title("Ubicación Actual"));
                                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 15));
                                    }
                                }
                            });
                }
            }
        }
    }

    private void openCamara() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            capturedImage = (Bitmap) extras.get("data");
            imageView.setImageBitmap(capturedImage);
        }
    }

    private void submitReport() throws IOException {
        if (capturedImage == null) {
            Toast.makeText(this, "Por favor toma una foto", Toast.LENGTH_SHORT).show();
            return;
        }

        // Convertir el Bitmap a un archivo temporal
        File imageFile = convertBitmapToFile(capturedImage, "temp_image.jpg");

        // Crear MultipartBody.Part para la foto
        RequestBody photoRequestBody = RequestBody.create(MediaType.parse("image/jpeg"), imageFile);
        MultipartBody.Part photoPart = MultipartBody.Part.createFormData("photo", imageFile.getName(), photoRequestBody);

        // Crear RequestBody para los demás campos
        RequestBody description = createPartFromString(descriptionEditText.getText().toString());
        RequestBody location = createPartFromString(currentLatLng.latitude + ", " + currentLatLng.longitude);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
        String currentDate = dateFormat.format(new Date());
        String currentTime = timeFormat.format(new Date());
        RequestBody date = createPartFromString(currentDate);
        RequestBody time = createPartFromString(currentTime);
        RequestBody plate = createPartFromString(plateEditText.getText().toString());
        RequestBody status = createPartFromString("Pendiente");
        RequestBody userId = createPartFromString("user123");
        RequestBody managerID = createPartFromString("manager123");

        // Llamada a la API
        Call<Report> call = apiReport.createReport(photoPart, description, location, date, time, plate, status, userId, managerID);
        call.enqueue(new Callback<Report>() {
            @Override
            public void onResponse(Call<Report> call, Response<Report> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(ReportView.this, "Reporte creado exitosamente", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ReportView.this, "Error al crear el reporte", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Report> call, Throwable t) {
                Toast.makeText(ReportView.this, "Error de comunicación", Toast.LENGTH_SHORT).show();
                Log.e("ReportView", "Error: " + t.getMessage());
                t.printStackTrace();
            }
        });
    }




    private File convertBitmapToFile(Bitmap bitmap, String fileName) throws IOException {
        File file = new File(getCacheDir(), fileName);
        file.createNewFile();

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
        byte[] bitmapData = bos.toByteArray();

        FileOutputStream fos = new FileOutputStream(file);
        fos.write(bitmapData);
        fos.flush();
        fos.close();

        return file;
    }

    private RequestBody createPartFromString(String descriptionString) {
        return RequestBody.create(MultipartBody.FORM, descriptionString);
    }
}
