package com.eduv4955673.itmba2_33_formativeassessment_tygervally_eduv4955673;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HomeDashboardActivity extends AppCompatActivity {

    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_dashboard);

        sessionManager = new SessionManager(this);

        TextView welcomeMessage = findViewById(R.id.textView_welcomeMessage);
        String username = sessionManager.getUsername();
        String welcomeText = "Hello, " + username + "! Ready for your next adventure?";
        welcomeMessage.setText(welcomeText);

        Button createMemoryButton = findViewById(R.id.button_create_memory);
        Button viewGalleryButton = findViewById(R.id.button_view_gallery);
        Button planTripButton = findViewById(R.id.button_plan_trip);
        Button logoutButton = findViewById(R.id.button_logout);

        createMemoryButton.setOnClickListener(v -> {
            Intent intent = new Intent(HomeDashboardActivity.this, MemoryCreationActivity.class);
            startActivity(intent);
        });

        viewGalleryButton.setOnClickListener(v -> {
            Intent intent = new Intent(HomeDashboardActivity.this, GalleryActivity.class);
            startActivity(intent);
        });

        planTripButton.setOnClickListener(v -> {
            Intent intent = new Intent(HomeDashboardActivity.this, TripPlanningActivity.class);
            startActivity(intent);
        });

        logoutButton.setOnClickListener(v -> {
            sessionManager.logoutUser();
            startActivity(new Intent(HomeDashboardActivity.this, LoginActivity.class));
            finish();
        });
        Button settingsButton = findViewById(R.id.button_settings);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeDashboardActivity.this, SettingsActivity.class));
            }
        });
    }
}