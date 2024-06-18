package com.si.citizenreport;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.si.citizenreport.BLogic.LoginScreen;
import com.si.citizenreport.BLogic.SignUpUser;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button registerButton = findViewById(R.id.signupButton); // Asegúrate de usar el ID correcto
        Button loginButtton = findViewById(R.id.loginButtton);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SignUpUser.class);
                startActivity(intent);
            }
        });

        loginButtton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( MainActivity.this, LoginScreen.class);
                startActivity(intent);
            }
        });
    }
}
