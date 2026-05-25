package com.eduv4955673.itmba2_33_formativeassessment_tygervally_eduv4955673;


import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {
    private SettingsManager settingsManager;
    private RadioGroup themeRadioGroup;
    private Switch switchBackgroundMusic, switchSoundEffects, switchNotifications;
    private Spinner languageSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        settingsManager = new SettingsManager(this);


        themeRadioGroup = findViewById(R.id.radioGroup_theme);
        switchBackgroundMusic = findViewById(R.id.switch_background_music);
        switchSoundEffects = findViewById(R.id.switch_sound_effects);
        switchNotifications = findViewById(R.id.switch_notifications);
        languageSpinner = findViewById(R.id.spinner_language);
        Button saveButton = findViewById(R.id.button_save_settings);
        Button resetButton = findViewById(R.id.button_reset_settings);


        setupLanguageSpinner();
        loadCurrentSettings();

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveSettings();
                Toast.makeText(SettingsActivity.this, "Settings saved successfully!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetSettingsToDefault();
                Toast.makeText(SettingsActivity.this, "Settings reset to default!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupLanguageSpinner() {
        String[] languages = {"English", "Spanish", "French", "German", "Italian"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, languages);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        languageSpinner.setAdapter(adapter);
    }

    private void loadCurrentSettings() {
        String currentTheme = settingsManager.getTheme();
        switch (currentTheme) {
            case "light":
                themeRadioGroup.check(R.id.radio_light);
                break;
            case "dark":
                themeRadioGroup.check(R.id.radio_dark);
                break;
            default:
                themeRadioGroup.check(R.id.radio_system);
        }

        switchBackgroundMusic.setChecked(settingsManager.isBackgroundMusicEnabled());
        switchSoundEffects.setChecked(settingsManager.isSoundEffectsEnabled());
        switchNotifications.setChecked(settingsManager.areNotificationsEnabled());

        String currentLanguage = settingsManager.getLanguage();
        int position = 0;
        if (currentLanguage.equals("es")) position = 1;
        else if (currentLanguage.equals("fr")) position = 2;
        else if (currentLanguage.equals("de")) position = 3;
        else if (currentLanguage.equals("it")) position = 4;
        languageSpinner.setSelection(position);
    }

    private void saveSettings() {
        int selectedThemeId = themeRadioGroup.getCheckedRadioButtonId();
        if (selectedThemeId == R.id.radio_light) {
            settingsManager.setTheme("light");
        } else if (selectedThemeId == R.id.radio_dark) {
            settingsManager.setTheme("dark");
        } else {
            settingsManager.setTheme("system");
        }

        settingsManager.setBackgroundMusicEnabled(switchBackgroundMusic.isChecked());
        settingsManager.setSoundEffectsEnabled(switchSoundEffects.isChecked());
        settingsManager.setNotificationsEnabled(switchNotifications.isChecked());

        int selectedLanguagePosition = languageSpinner.getSelectedItemPosition();
        String languageCode = "en";
        if (selectedLanguagePosition == 1) languageCode = "es";
        else if (selectedLanguagePosition == 2) languageCode = "fr";
        else if (selectedLanguagePosition == 3) languageCode = "de";
        else if (selectedLanguagePosition == 4) languageCode = "it";
        settingsManager.setLanguage(languageCode);
        applyThemeSettings();
    }

    private void resetSettingsToDefault() {
        settingsManager.clearAllSettings();
        loadCurrentSettings();
    }

    private void applyThemeSettings() {
        recreate();
    }
}
