package com.si.citizenreport;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.si.citizenreport.connection.APIConnection;
import com.si.citizenreport.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SignUpUser extends AppCompatActivity {

    private EditText nameField, lastNameField, curpField, phoneField, emailField, idmexField, editTextTextPassword;
    private ApiReport apiReport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_user);

        nameField = findViewById(R.id.nameField);
        lastNameField = findViewById(R.id.lastNameField);
        curpField = findViewById(R.id.curpField);
        phoneField = findViewById(R.id.phoneField);
        emailField = findViewById(R.id.emailField);
        idmexField = findViewById(R.id.idmexField);
        editTextTextPassword = findViewById(R.id.editTextTextPassword);
        Button registerButton = findViewById(R.id.button);

        Retrofit retrofit = APIConnection.getRetrofitInstance();
        apiReport = retrofit.create(ApiReport.class);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
    }

    private void registerUser() {
        String fullName = nameField.getText().toString() + " " + lastNameField.getText().toString();
        String curp = curpField.getText().toString();
        String phoneNumber = phoneField.getText().toString();
        String email = emailField.getText().toString();
        String idMex = idmexField.getText().toString();
        String password = editTextTextPassword.getText().toString();

        if (fullName.isEmpty() || curp.isEmpty() || phoneNumber.isEmpty() || email.isEmpty() || idMex.isEmpty() || password.isEmpty()) {
            Toast.makeText(SignUpUser.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        User user = new User( fullName, curp, phoneNumber, email, "defaultAddress", idMex, password);

        Call<User> call = apiReport.registerUser(user);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(SignUpUser.this, "User registered successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SignUpUser.this, "Failed to register user", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(SignUpUser.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                System.out.println("Error: " + t.getMessage());
            }
        });
    }
}
