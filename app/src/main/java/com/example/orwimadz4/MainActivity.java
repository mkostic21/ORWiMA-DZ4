package com.example.orwimadz4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private Button btnSearch;
    private EditText edQuery;
    private RecyclerAdapter adapter;

    //TODO: Pojo from JSON
    //TODO: NetworkUtils
    //TODO: Glide integration
    //TODO: ApiInterface

    //TODO: onClick -> search


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupRecycler();
        initUI();

    }

    private void setupRecycler() {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecyclerAdapter();
        recyclerView.setAdapter(adapter);
    }

    private void initUI() {
        btnSearch = findViewById(R.id.btnSearch);
        edQuery = findViewById(R.id.edQuery);
    }


}