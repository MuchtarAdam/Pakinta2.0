package com.example.pakinta.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pakinta.R;
import com.example.pakinta.adapter.ProfileFeatureAdapter;
import com.example.pakinta.datasource.ProfileFeatureDataSource;
import com.example.pakinta.model.ProfileFeatureModel;

import java.util.List;

public class ProfileFragment extends Fragment {

    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        recyclerView = view.findViewById(R.id.rv_profile_feature);

        ProfileFeatureDataSource dataSource = new ProfileFeatureDataSource();

        List<ProfileFeatureModel> profileFeatureModels = dataSource.getProfileFeatureModels();

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        ProfileFeatureAdapter profileFeatureAdapter = new ProfileFeatureAdapter(profileFeatureModels);
        recyclerView.setAdapter(profileFeatureAdapter);

        return view;
    }
}