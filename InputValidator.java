package com.eduv4955673.itmba2_33_formativeassessment_tygervally_eduv4955673;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import java.util.regex.Pattern;

public class InputValidator implements TextWatcher {

    private final EditText editText;
    private String previousValidValue;

    public InputValidator(EditText editText) {
        this.editText = editText;
        this.previousValidValue = "";
        this.editText.addTextChangedListener(this);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    @Override
    public void afterTextChanged(Editable s) {
        String input = s.toString();
        if (input.isEmpty()) {
            previousValidValue = "";
            return;
        }

        if (isValidNonNegativeDecimal(input)) {
            previousValidValue = input;
        } else {
            editText.removeTextChangedListener(this);
            editText.setText(previousValidValue);
            editText.setSelection(previousValidValue.length());
            editText.addTextChangedListener(this);
        }
    }

    private boolean isValidNonNegativeDecimal(String input) {
        String decimalPattern = "^\\d*(\\.\\d{0,2})?$";
        return Pattern.matches(decimalPattern, input);
    }
}
