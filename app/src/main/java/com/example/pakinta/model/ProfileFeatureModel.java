package com.example.pakinta.model;

public class ProfileFeatureModel {
    private int feature_image;
    private String tv_feature_name;

    public ProfileFeatureModel(int feature_image, String tv_feature_name) {
        this.feature_image = feature_image;
        this.tv_feature_name = tv_feature_name;
    }

    public int getFeature_image() {
        return feature_image;
    }

    public String getTv_feature_name() {
        return tv_feature_name;
    }
}
