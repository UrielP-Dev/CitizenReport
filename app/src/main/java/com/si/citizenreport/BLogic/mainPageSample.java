package com.si.citizenreport.BLogic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.si.citizenreport.R;
import com.si.citizenreport.model.User;

public class mainPageSample extends AppCompatActivity {

    private TextView welcomeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page_sample);

        welcomeTextView = findViewById(R.id.welcomeTextView);

        // Obtener el objeto User del Intent
        User user = (User) getIntent().getSerializableExtra("user");

        // Mostrar el nombre del usuario en el TextView
        if (user != null) {
            welcomeTextView.setText("Bienvenido, " + user.getFullName());
        } else {
            welcomeTextView.setText("Bienvenido");
        }
    }
}

