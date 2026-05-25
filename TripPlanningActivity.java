package com.eduv4955673.itmba2_33_formativeassessment_tygervally_eduv4955673;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.eduv4955673.itmba2_33_formativeassessment_tygervally_eduv4955673.ui.theme.TripHistoryManager;

import java.util.ArrayList;
import java.util.List;

public class TripPlanningActivity extends AppCompatActivity {

    private EditText editTextStartDate;
    private EditText editTextEndDate;
    private LinearLayout activitiesLayout;
    private TableLayout expensesTable;
    private Button addExpenseButton;
    private List<PredefinedActivity> predefinedActivities;
    private EditText editTextDestination;

    private class CostTextWatcher implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}

        @Override
        public void afterTextChanged(Editable s) {
            calculateAndDisplayTotal();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_planning);

        editTextStartDate = findViewById(R.id.editText_start_date);
        editTextEndDate = findViewById(R.id.editText_end_date);
        activitiesLayout = findViewById(R.id.linearLayout_activities);
        expensesTable = findViewById(R.id.tableLayout_expenses);
        addExpenseButton = findViewById(R.id.button_add_expense);
        Button saveTripButton = findViewById(R.id.button_save_trip);
        editTextDestination = findViewById(R.id.editText_destination);

        setupSaveTripButton(saveTripButton);
        setupDatePickers();
        setupPredefinedActivities();
        displayPredefinedActivities();
        setupAddExpenseButton();
        calculateAndDisplayTotal();

        //debugSetTripCount(3);
    }

    private void setupDatePickers() {
        editTextStartDate.setOnClickListener(v -> DatePickerHelper.showDatePicker(this, editTextStartDate));
        editTextEndDate.setOnClickListener(v -> DatePickerHelper.showDatePicker(this, editTextEndDate));
    }

    private void setupPredefinedActivities() {
        predefinedActivities = new ArrayList<>();
        predefinedActivities.add(new PredefinedActivity("Sightseeing Tour", 50.00));
        predefinedActivities.add(new PredefinedActivity("Hiking Guide", 75.00));
        predefinedActivities.add(new PredefinedActivity("Fine Dining Experience", 120.00));
        predefinedActivities.add(new PredefinedActivity("Museum Entry", 25.00));
        predefinedActivities.add(new PredefinedActivity("Boat Rental", 90.00));
    }

    private void displayPredefinedActivities() {
        for (PredefinedActivity activity : predefinedActivities) {
            CheckBox checkBox = new CheckBox(this);
            String displayText = activity.getName() + " - R" + activity.getCost();
            checkBox.setText(displayText);

            checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
                activity.setSelected(isChecked);
                calculateAndDisplayTotal();
            });

            activitiesLayout.addView(checkBox);
        }
    }

    private void setupAddExpenseButton() {
        addExpenseButton.setOnClickListener(v -> addNewExpenseRow());
    }

    private void addNewExpenseRow() {
        TableRow newRow = new TableRow(this);
        newRow.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));

        EditText descriptionEditText = new EditText(this);
        EditText costEditText = new EditText(this);

        TableRow.LayoutParams params = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1.5f);
        descriptionEditText.setLayoutParams(params);
        descriptionEditText.setHint("Description");

        params = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1.0f);
        costEditText.setLayoutParams(params);
        costEditText.setHint("0.00");
        costEditText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);

        new InputValidator(costEditText);
        costEditText.addTextChangedListener(new CostTextWatcher());

        newRow.addView(descriptionEditText);
        newRow.addView(costEditText);

        expensesTable.addView(newRow);
    }

    private void calculateAndDisplayTotal() {
        double subtotal = 0.0;
        subtotal += calculatePredefinedActivitiesTotal();
        subtotal += calculateCustomExpensesTotal();

        TextView subtotalTextView = findViewById(R.id.textView_subtotal);
        TextView discountTextView = findViewById(R.id.textView_discount);
        TextView totalTextView = findViewById(R.id.textView_total);

        double discountAmount = 0.0;
        double finalTotal = subtotal;

        int previousTripCount = TripHistoryManager.getTripCount(this);
        if (previousTripCount >= 3) {
            discountAmount = subtotal * 0.10;
            finalTotal = subtotal - discountAmount;
            discountTextView.setVisibility(View.VISIBLE);
            discountTextView.setText(String.format("Discount (10%%): -R%.2f", discountAmount));
        } else {
            discountTextView.setVisibility(View.GONE);
        }

        subtotalTextView.setText(String.format("Subtotal: R%.2f", subtotal));
        totalTextView.setText(String.format("Total: R%.2f", finalTotal));
    }

    private double calculatePredefinedActivitiesTotal() {
        double total = 0.0;
        for (PredefinedActivity activity : predefinedActivities) {
            if (activity.isSelected()) {
                total += activity.getCost();
            }
        }
        return total;
    }

    private double calculateCustomExpensesTotal() {
        double total = 0.0;
        for (int i = 1; i < expensesTable.getChildCount(); i++) {
            View view = expensesTable.getChildAt(i);
            if (view instanceof TableRow) {
                TableRow row = (TableRow) view;
                EditText costEditText = (EditText) row.getChildAt(1);
                String costString = costEditText.getText().toString();
                if (!costString.isEmpty()) {
                    try {
                        total += Double.parseDouble(costString);
                    } catch (NumberFormatException e) {
                    }
                }
            }
        }
        return total;
    }

    private void setupSaveTripButton(Button saveButton) {
        saveButton.setOnClickListener(v -> {
            String destination = editTextDestination.getText().toString();
            String startDate = editTextStartDate.getText().toString();
            String endDate = editTextEndDate.getText().toString();

            if (destination.isEmpty()) {
                Toast.makeText(this, "Please enter a destination", Toast.LENGTH_SHORT).show();
                return;
            }

            double subtotal = calculatePredefinedActivitiesTotal() + calculateCustomExpensesTotal();
            int previousTripCount = TripHistoryManager.getTripCount(this);
            double discountAmount = (previousTripCount >= 3) ? subtotal * 0.10 : 0.0;
            double finalTotal = subtotal - discountAmount;

            TripHistoryManager.incrementTripCount(this);
            int newTripCount = TripHistoryManager.getTripCount(this);

            Intent summaryIntent = new Intent(this, BudgetSummaryActivity.class);
            summaryIntent.putExtra("DESTINATION", destination);
            summaryIntent.putExtra("DATES", startDate + " - " + endDate);
            summaryIntent.putExtra("SUBTOTAL", subtotal);
            summaryIntent.putExtra("DISCOUNT", discountAmount);
            summaryIntent.putExtra("TOTAL", finalTotal);
            summaryIntent.putExtra("TRIP_COUNT", newTripCount);

            startActivity(summaryIntent);
            finish();
        });
    }
    private void debugSetTripCount(int count) {
        TripHistoryManager.setTripCount(this, count);
        Toast.makeText(this, "DEBUG: Set trip count to " + count, Toast.LENGTH_SHORT).show();
    }
}