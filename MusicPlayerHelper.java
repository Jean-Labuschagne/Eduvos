package com.eduv4955673.itmba2_33_formativeassessment_tygervally_eduv4955673;


import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;

public class MusicPlayerHelper {
    private static MediaPlayer mediaPlayer;
    private static boolean isPrepared = false;
    private static final String TAG = "MusicPlayerHelper";

    public static void playMusic(Context context, int musicResourceId) {
        stopMusic();
        try {
            mediaPlayer = MediaPlayer.create(context, musicResourceId);
            if (mediaPlayer != null) {
                mediaPlayer.setLooping(true);
                mediaPlayer.start();
                isPrepared = true;
                Log.d(TAG, "Music started playing successfully");
            } else {
                Log.e(TAG, "MediaPlayer creation failed - audio file may be corrupted or missing");
            }
        } catch (Exception e) {
            Log.e(TAG, "Error playing music: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void pauseMusic() {
        if (mediaPlayer != null && isPrepared && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            Log.d(TAG, "Music paused");
        }
    }

    public static void resumeMusic() {
        if (mediaPlayer != null && isPrepared && !mediaPlayer.isPlaying()) {
            mediaPlayer.start();
            Log.d(TAG, "Music resumed");
        }
    }

    public static void stopMusic() {
        if (mediaPlayer != null) {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
                Log.d(TAG, "Music stopped");
            }
            mediaPlayer.release();
            mediaPlayer = null;
            isPrepared = false;
        }
    }

    public static boolean isPlaying() {
        return mediaPlayer != null && mediaPlayer.isPlaying();
    }

    public static boolean isPrepared() {
        return isPrepared;
    }

    public static boolean isPaused() {
        return mediaPlayer != null && isPrepared && !mediaPlayer.isPlaying();
    }
}