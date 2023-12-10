package com.example.pakinta.model;

public class HomeServiceModel {

    private String home_service_image;
    private int home_service_arrow;
    private String home_service_title, home_service_description;

    public HomeServiceModel(String home_service_image, int home_service_arrow, String home_service_title, String home_service_description) {
        this.home_service_image = home_service_image;
        this.home_service_arrow = home_service_arrow;
        this.home_service_title = home_service_title;
        this.home_service_description = home_service_description;
    }

    public String getHome_service_image() {
        return home_service_image;
    }

    public int getHome_service_arrow() {
        return home_service_arrow;
    }

    public String getHome_service_title() {
        return home_service_title;
    }

    public String getHome_service_description() {
        return home_service_description;
    }
}
