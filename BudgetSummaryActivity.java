package com.eduv4955673.itmba2_33_formativeassessment_tygervally_eduv4955673;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class BudgetSummaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget_summary);

        Intent intent = getIntent();
        String destination = intent.getStringExtra("DESTINATION");
        String dates = intent.getStringExtra("DATES");
        double subtotal = intent.getDoubleExtra("SUBTOTAL", 0.0);
        double discount = intent.getDoubleExtra("DISCOUNT", 0.0);
        double total = intent.getDoubleExtra("TOTAL", 0.0);
        int tripCount = intent.getIntExtra("TRIP_COUNT", 0);

        TextView destinationView = findViewById(R.id.textView_summary_destination);
        TextView datesView = findViewById(R.id.textView_summary_dates);
        TextView subtotalView = findViewById(R.id.textView_summary_subtotal);
        TextView discountView = findViewById(R.id.textView_summary_discount);
        TextView totalView = findViewById(R.id.textView_summary_total);
        TextView loyaltyView = findViewById(R.id.textView_loyalty_status);
        Button doneButton = findViewById(R.id.button_summary_done);

        destinationView.setText("Destination: " + (destination != null ? destination : "N/A"));
        datesView.setText("Dates: " + (dates != null ? dates : "N/A"));
        subtotalView.setText(String.format("Subtotal: R%.2f", subtotal));

        if (discount > 0) {
            discountView.setVisibility(TextView.VISIBLE);
            discountView.setText(String.format("Discount (10%%): -R%.2f", discount));
        }

        totalView.setText(String.format("Total: R%.2f", total));

        String loyaltyStatus;
        if (tripCount >= 3) {
            loyaltyStatus = "Gold Member (" + tripCount + " trips)";
        } else {
            loyaltyStatus = "Earn discount at " + (3 - tripCount) + " more trip(s) (" + tripCount + "/3)";
        }
        loyaltyView.setText("Loyalty Status: " + loyaltyStatus);

        doneButton.setOnClickListener(v -> {
            Intent homeIntent = new Intent(BudgetSummaryActivity.this, HomeDashboardActivity.class);
            startActivity(homeIntent);
            finish();
        });
    }
}