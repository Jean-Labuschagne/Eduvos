package com.eduv4955673.itmba2_33_formativeassessment_tygervally_eduv4955673;


import android.content.Context;
import android.content.SharedPreferences;

public class SettingsManager {
    private static final String PREFS_NAME = "TripBuddySettings";
    private static final String KEY_THEME = "theme";
    private static final String KEY_BACKGROUND_MUSIC = "background_music";
    private static final String KEY_SOUND_EFFECTS = "sound_effects";
    private static final String KEY_NOTIFICATIONS = "notifications";
    private static final String KEY_LANGUAGE = "language";

    private SharedPreferences prefs;

    public SettingsManager(Context context) {
        prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    public String getTheme() {
        return prefs.getString(KEY_THEME, "system");
    }

    public void setTheme(String theme) {
        prefs.edit().putString(KEY_THEME, theme).apply();
    }

    public boolean isBackgroundMusicEnabled() {
        return prefs.getBoolean(KEY_BACKGROUND_MUSIC, true);
    }

    public void setBackgroundMusicEnabled(boolean enabled) {
        prefs.edit().putBoolean(KEY_BACKGROUND_MUSIC, enabled).apply();
    }

    public boolean isSoundEffectsEnabled() {
        return prefs.getBoolean(KEY_SOUND_EFFECTS, true);
    }

    public void setSoundEffectsEnabled(boolean enabled) {
        prefs.edit().putBoolean(KEY_SOUND_EFFECTS, enabled).apply();
    }

    public boolean areNotificationsEnabled() {
        return prefs.getBoolean(KEY_NOTIFICATIONS, true);
    }

    public void setNotificationsEnabled(boolean enabled) {
        prefs.edit().putBoolean(KEY_NOTIFICATIONS, enabled).apply();
    }

    public String getLanguage() {
        return prefs.getString(KEY_LANGUAGE, "en");
    }

    public void setLanguage(String language) {
        prefs.edit().putString(KEY_LANGUAGE, language).apply();
    }

    public void clearAllSettings() {
        prefs.edit().clear().apply();
    }
}
