package com.example.l7_recyclerview;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ItemAdapter adapter;
    private List<Item> itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize RecyclerView
        recyclerView = findViewById(R.id.recyclerView);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        // Initialize data and adapter
        itemList = new ArrayList<>();
        loadData();
        adapter = new ItemAdapter(this, itemList);

        recyclerView.setAdapter(adapter);

    }

    private void loadData() {
        // Sample data for testing
        itemList.add(new Item(R.drawable.ic_launcher_background, "Title 1", "Subtitle 1"));
        itemList.add(new Item(R.drawable.ic_launcher_background, "Title 2", "Subtitle 2"));
        itemList.add(new Item(R.drawable.ic_launcher_background, "Title 3", "Subtitle 3"));
        itemList.add(new Item(R.drawable.ic_launcher_background, "Title 4", "Subtitle 4"));
        itemList.add(new Item(R.drawable.ic_launcher_background, "Title 5", "Subtitle 5"));
    }
}