package com.eduv4955673.itmba2_33_formativeassessment_tygervally_eduv4955673;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import java.io.File;
import java.io.IOException;

public class MemoryCreationActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;
    private static final int CAMERA_REQUEST = 2;
    private static final int STORAGE_PERMISSION_CODE = 100;

    private LinearLayout musicControlsLayout;
    private Button playButton, pauseButton, stopButton;
    private ImageView imageViewPreview;
    private Uri selectedImageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory_creation);
        testAudioFile();

        musicControlsLayout = findViewById(R.id.musicControlsLayout);
        playButton = findViewById(R.id.button_play);
        pauseButton = findViewById(R.id.button_pause);
        stopButton = findViewById(R.id.button_stop);
        imageViewPreview = findViewById(R.id.imageView_preview);



        Button addPhotoButton = findViewById(R.id.button_add_photo);
        addPhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openImagePicker();
            }
        });


        Button addMusicButton = findViewById(R.id.button_add_music);
        addMusicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MusicPlayerHelper.playMusic(MemoryCreationActivity.this, R.raw.background_music);
                musicControlsLayout.setVisibility(View.VISIBLE);
                updateMusicButtonStates();
            }
        });


        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MusicPlayerHelper.resumeMusic();
                updateMusicButtonStates();
            }
        });

        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MusicPlayerHelper.pauseMusic();
                updateMusicButtonStates();
            }
        });

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MusicPlayerHelper.stopMusic();
                musicControlsLayout.setVisibility(View.GONE);
            }
        });


        Button saveButton = findViewById(R.id.button_save_memory);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveMemory();
            }
        });


        musicControlsLayout.setVisibility(View.GONE);
    }
    private void testAudioFile() {
        try {

            MediaPlayer testPlayer = MediaPlayer.create(this, R.raw.background_music);
            if (testPlayer == null) {
                Toast.makeText(this, "ERROR: Audio file not found or corrupted!", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Audio file loaded successfully!", Toast.LENGTH_SHORT).show();
                testPlayer.release();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Error loading audio: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
    private void openImagePicker() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == PICK_IMAGE_REQUEST && data != null) {
                selectedImageUri = data.getData();
                imageViewPreview.setImageURI(selectedImageUri);
                imageViewPreview.setVisibility(View.VISIBLE);
            }
        }
    }

    private void updateMusicButtonStates() {
        boolean isPlaying = MusicPlayerHelper.isPlaying();
        boolean isPrepared = MusicPlayerHelper.isPrepared();

        playButton.setEnabled(!isPlaying);
        pauseButton.setEnabled(isPlaying);
        stopButton.setEnabled(isPrepared);
    }

    private void saveMemory() {
        EditText titleEditText = findViewById(R.id.editText_memory_title);
        EditText descEditText = findViewById(R.id.editText_description);

        String title = titleEditText.getText().toString();
        String description = descEditText.getText().toString();

        if (title.isEmpty()) {
            Toast.makeText(this, "Please enter a title", Toast.LENGTH_SHORT).show();
            return;
        }


        Memory memory = new Memory();
        memory.setTitle(title);
        memory.setDescription(description);
        memory.setImagePath(selectedImageUri != null ? selectedImageUri.toString() : "");
        memory.setLocation("Unknown Location");
        memory.setDate(new java.util.Date());
        memory.setMood("Happy");
        memory.setMusicTrack("background_music");

        DatabaseHelper dbHelper = new DatabaseHelper(this);
        long id = dbHelper.addMemory(memory);
        dbHelper.close();

        if (id != -1) {
            Toast.makeText(this, "Memory saved successfully!", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Failed to save memory", Toast.LENGTH_SHORT).show();
        }
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