package com.example.pakinta.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;


import com.example.pakinta.R;
import com.example.pakinta.adapter.MaterialAdapter;
import com.example.pakinta.datasource.MaterialDataSource;
import com.example.pakinta.model.MaterialModel;

import java.util.ArrayList;
import java.util.List;

public class MaterialActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private SearchView searchView;
    private MaterialAdapter materialAdapter;
    private List<MaterialModel> materialModels;
    private MaterialDataSource materialDataSource;
    private List<MaterialModel> filteredMaterialModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material);

        searchView = findViewById(R.id.searchView);
        recyclerView = findViewById(R.id.recyclerview);
        progressBar = findViewById(R.id.progressBar);

        materialDataSource = new MaterialDataSource();

        recyclerView.setVisibility(View.INVISIBLE);
        searchView.clearFocus();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.INVISIBLE);
                recyclerView.setVisibility(View.VISIBLE);
            }
        }, 750);

        GridLayoutManager layoutManager = new GridLayoutManager(MaterialActivity.this, 2);
        recyclerView.setLayoutManager(layoutManager);

        materialModels = materialDataSource.getMaterials();

        filteredMaterialModels = new ArrayList<>(materialModels); // Initialize the filtered list with all material models

        materialAdapter = new MaterialAdapter((ArrayList<MaterialModel>) filteredMaterialModels, MaterialActivity.this);
        recyclerView.setAdapter(materialAdapter);

        // Add text change listener to searchView
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterItem(newText);
                return true;
            }
        });
    }

    private void filterItem(String newText) {
        filteredMaterialModels.clear();

        // If the search query is empty, show all material models
        if (newText.isEmpty()) {
            filteredMaterialModels.addAll(materialModels);
        } else {
            // Iterate through material models and add matching ones to the filtered list
            for (MaterialModel materialModel : materialModels) {
                if (materialModel.getName().toLowerCase().contains(newText.toLowerCase())) {
                    filteredMaterialModels.add(materialModel);
                }
            }
        }

        materialAdapter.notifyDataSetChanged();
    }
}