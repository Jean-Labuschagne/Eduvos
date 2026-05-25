package com.eduv4955673.itmba2_33_formativeassessment_tygervally_eduv4955673;


import android.content.Context;
import android.content.SharedPreferences;

public class TripHistoryManager {
    private static final String PREFS_NAME = "TripBuddyPrefs";
    private static final String KEY_TRIP_COUNT = "trip_count";

    public static void incrementTripCount(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        int currentCount = prefs.getInt(KEY_TRIP_COUNT, 0);
        prefs.edit().putInt(KEY_TRIP_COUNT, currentCount + 1).apply();
    }

    public static int getTripCount(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return prefs.getInt(KEY_TRIP_COUNT, 0);
    }

    public static void setTripCount(Context context, int count) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        prefs.edit().putInt(KEY_TRIP_COUNT, count).apply();
    }
}
