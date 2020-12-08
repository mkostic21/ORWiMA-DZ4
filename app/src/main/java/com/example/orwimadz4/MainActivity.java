package com.example.orwimadz4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private Button btnSearch;
    private EditText edQuery;
    private RecyclerView recyclerView;
    private RecyclerAdapter adapter;
    private TextView tvEmpty;
    private ProgressBar loading;

    private Call<List<Product>> apiCall;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupRecycler();
        initUI();
        setupSearchButtonListener();
    }

    private void setupApiCall(String brand) {
        apiCall = NetworkUtils.getApiInterface().getProducts(brand);

        recyclerView.setVisibility(View.GONE);
        tvEmpty.setVisibility(View.GONE);
        loading.setVisibility(View.VISIBLE);

        apiCall.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()) {
                    loading.setVisibility(View.GONE);

                    if (response.body().isEmpty()) {
                        recyclerView.setVisibility(View.GONE);
                        tvEmpty.setVisibility(View.VISIBLE);
                    } else {
                        recyclerView.setVisibility(View.VISIBLE);
                        tvEmpty.setVisibility(View.GONE);

                        adapter.setProducts(response.body());
                        adapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();

                loading.setVisibility(View.GONE);
                recyclerView.setVisibility(View.GONE);
                tvEmpty.setVisibility(View.VISIBLE);
            }

        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (apiCall != null) {
            apiCall.cancel();
        }
    }

    private void setupRecycler() {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecyclerAdapter();
        recyclerView.setAdapter(adapter);
    }

    private void initUI() {
        btnSearch = findViewById(R.id.btnSearch);
        edQuery = findViewById(R.id.edQuery);
        tvEmpty = findViewById(R.id.tvEmpty);
        loading = findViewById(R.id.loading);
    }

    private void setupSearchButtonListener() {
        btnSearch.setOnClickListener(view -> {
            String brand = edQuery.getText().toString();
            edQuery.setText("");
            if (!brand.isEmpty()) {
                setupApiCall(brand);
            } else {
                Toast.makeText(this, "Please enter brand name", Toast.LENGTH_SHORT).show();
            }
        });
    }

}