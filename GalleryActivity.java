package com.eduv4955673.itmba2_33_formativeassessment_tygervally_eduv4955673;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.widget.Toast;
import java.util.Arrays;
import java.util.List;

public class GalleryActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        dbHelper = new DatabaseHelper(this);

        RecyclerView recyclerView = findViewById(R.id.recyclerView_gallery);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));


        List<Memory> memories = dbHelper.getAllMemories();

        if (memories.isEmpty()) {
            Toast.makeText(this, "No memories found. Create some first!", Toast.LENGTH_SHORT).show();
        }


        List<Integer> memoryImages = Arrays.asList(
                R.drawable.mountain_memory,
                R.drawable.beach_memory,
                R.drawable.city_memory
        );

        GalleryAdapter adapter = new GalleryAdapter(this, memoryImages);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbHelper.close();
    }
}