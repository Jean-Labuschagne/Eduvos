package com.eduv4955673.itmba2_33_formativeassessment_tygervally_eduv4955673;


import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private EditText editTextUsername, editTextPassword;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sessionManager = new SessionManager(this);
        if (sessionManager.isLoggedIn()) {
            startActivity(new Intent(LoginActivity.this, HomeDashboardActivity.class));
            finish();
            return;
        }

        setContentView(R.layout.activity_login);

        editTextUsername = findViewById(R.id.editText_username);
        editTextPassword = findViewById(R.id.editText_password);
        Button loginButton = findViewById(R.id.button_login);
        Button gotoRegisterButton = findViewById(R.id.button_goto_register);

        loginButton.setOnClickListener(v -> {
            String username = editTextUsername.getText().toString();
            String password = editTextPassword.getText().toString();

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(LoginActivity.this, "Please enter credentials", Toast.LENGTH_SHORT).show();
                return;
            }

            if (password.length() >= 4) {
                sessionManager.createLoginSession(username, username + "@example.com");
                startActivity(new Intent(LoginActivity.this, HomeDashboardActivity.class));
                finish();
            } else {
                Toast.makeText(LoginActivity.this, "Password must be at least 4 characters", Toast.LENGTH_SHORT).show();
            }
        });

        gotoRegisterButton.setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this, RegistrationActivity.class));
        });
    }
}