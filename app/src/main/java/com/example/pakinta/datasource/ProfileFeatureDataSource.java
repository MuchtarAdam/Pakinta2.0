package com.example.pakinta.datasource;

import com.example.pakinta.R;
import com.example.pakinta.model.ChatModel;
import com.example.pakinta.model.ProfileFeatureModel;

import java.util.ArrayList;
import java.util.List;

public class ProfileFeatureDataSource {
    List<ProfileFeatureModel> profileFeatureModels;

    public List<ProfileFeatureModel> getProfileFeatureModels() {
        profileFeatureModels = new ArrayList<>();
        profileFeatureModels.add(new ProfileFeatureModel(R.drawable.outline_business_center_24,"Profil Bisnis"));
        profileFeatureModels.add(new ProfileFeatureModel(R.drawable.outline_article_24,"Syarat & Ketentuan"));
        profileFeatureModels.add(new ProfileFeatureModel(R.drawable.outline_gpp_maybe_24,"Kebijakan Privasi"));
        profileFeatureModels.add(new ProfileFeatureModel(R.drawable.baseline_logout_24,"Keluar"));

        return profileFeatureModels;
    }
}
