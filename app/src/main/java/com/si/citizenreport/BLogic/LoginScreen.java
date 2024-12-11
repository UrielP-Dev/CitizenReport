package com.si.citizenreport.BLogic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.si.citizenreport.ApiReport;
import com.si.citizenreport.R;
import com.si.citizenreport.connection.APIConnection;
import com.si.citizenreport.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginScreen extends AppCompatActivity {
    private static final String TAG = "LoginScreen";
    private EditText email, password;
    private Button loginButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        email = findViewById(R.id.emial);
        password = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailText = email.getText().toString();
                String passwordText = password.getText().toString();

                if (emailText.isEmpty() || passwordText.isEmpty()) {
                    Toast.makeText(LoginScreen.this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
                } else {
                    loginUser(emailText, passwordText);
                }
            }
        });
    }

    private void loginUser(String email, String password) {
        ApiReport apiReport = APIConnection.getRetrofitInstance().create(ApiReport.class);

        User loginUser = User.builder()
                .email(email)
                .password(password)
                .build();

        Call<User> call = apiReport.loginUser(loginUser);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful() && response.body() != null) {
                    User user = response.body();
                    // El usuario ha iniciado sesión correctamente
                    Toast.makeText(LoginScreen.this, "Login exitoso: " + user.getFullName(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginScreen.this, mainPageSampleU.class);
                    intent.putExtra("user", user);
                    startActivity(intent);
                } else {
                    // El login ha fallado
                    Toast.makeText(LoginScreen.this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e(TAG, "Error en login: ", t);
                Toast.makeText(LoginScreen.this, "Error en login " + t, Toast.LENGTH_SHORT).show();
            }
        });
    }
}