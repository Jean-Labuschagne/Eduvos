package com.eduv4955673.itmba2_33_formativeassessment_tygervally_eduv4955673;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistrationActivity extends AppCompatActivity {

    private EditText editTextFullName, editTextEmail, editTextUsername, editTextPassword, editTextConfirmPassword;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        sessionManager = new SessionManager(this);

        editTextFullName = findViewById(R.id.editText_full_name);
        editTextEmail = findViewById(R.id.editText_email);
        editTextUsername = findViewById(R.id.editText_username_register);
        editTextPassword = findViewById(R.id.editText_password_register);
        editTextConfirmPassword = findViewById(R.id.editText_confirm_password);

        Button registerButton = findViewById(R.id.button_register);
        Button gotoLoginButton = findViewById(R.id.button_goto_login);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullName = editTextFullName.getText().toString();
                String email = editTextEmail.getText().toString();
                String username = editTextUsername.getText().toString();
                String password = editTextPassword.getText().toString();
                String confirmPassword = editTextConfirmPassword.getText().toString();

                if (fullName.isEmpty() || email.isEmpty() || username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(RegistrationActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!password.equals(confirmPassword)) {
                    Toast.makeText(RegistrationActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length() < 4) {
                    Toast.makeText(RegistrationActivity.this, "Password must be at least 4 characters", Toast.LENGTH_SHORT).show();
                    return;
                }

                // In a real app, you would save to database here
                // For now, we'll just create a session and go to home
                sessionManager.createLoginSession(username, email);
                Toast.makeText(RegistrationActivity.this, "Account created successfully!", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(RegistrationActivity.this, HomeDashboardActivity.class);
                startActivity(intent);
                finish();
            }
        });

        gotoLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}