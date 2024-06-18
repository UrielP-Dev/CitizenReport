package com.si.citizenreport.BLogic;

// UserDetailsActivity.java
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import com.si.citizenreport.R;

public class UserDetailsActivity extends AppCompatActivity {

    private EditText etName, etUsername, etEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        etName = findViewById(R.id.et_name);
        etUsername = findViewById(R.id.et_username);
        etEmail = findViewById(R.id.et_email);

        // Añadir funcionalidad de edición si es necesario
        // etName.setOnClickListener(view -> enableEditText(etName));
        // etUsername.setOnClickListener(view -> enableEditText(etUsername));
        // etEmail.setOnClickListener(view -> enableEditText(etEmail));
    }

    private void enableEditText(EditText editText) {
        editText.setEnabled(true);
        editText.requestFocus();
    }
}

