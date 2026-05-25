package com.eduv4955673.itmba2_33_formativeassessment_tygervally_eduv4955673;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class FullScreenPhotoActivity extends AppCompatActivity {

    private Button playButton, pauseButton, stopButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen_photo);

        ImageView imageView = findViewById(R.id.imageView_fullscreen);
        playButton = findViewById(R.id.button_play_music);
        pauseButton = findViewById(R.id.button_pause_music);
        stopButton = findViewById(R.id.button_stop_music);

        Intent intent = getIntent();
        int imageResource = intent.getIntExtra("IMAGE_RESOURCE", 0);

        if (imageResource != 0) {
            imageView.setImageResource(imageResource);
        }

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MusicPlayerHelper.isPlaying()) {
                    MusicPlayerHelper.resumeMusic();
                } else {
                    MusicPlayerHelper.playMusic(FullScreenPhotoActivity.this, R.raw.background_music);
                }
                updateButtonStates();
            }
        });

        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MusicPlayerHelper.pauseMusic();
                updateButtonStates();
            }
        });

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MusicPlayerHelper.stopMusic();
                updateButtonStates();
            }
        });

        updateButtonStates();
    }

    private void updateButtonStates() {
        boolean isPlaying = MusicPlayerHelper.isPlaying();
        boolean isPrepared = MusicPlayerHelper.isPrepared();

        playButton.setEnabled(!isPlaying);
        pauseButton.setEnabled(isPlaying);
        stopButton.setEnabled(isPrepared);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MusicPlayerHelper.pauseMusic();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MusicPlayerHelper.stopMusic();
    }
}