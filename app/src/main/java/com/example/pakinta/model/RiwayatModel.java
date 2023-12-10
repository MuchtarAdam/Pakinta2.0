package com.example.pakinta.model;

public class RiwayatModel {
    private int image;
    private String project_name, status, date_time, price;

    public RiwayatModel(int image, String project_name, String status, String date_time, String price) {
        this.image = image;
        this.project_name = project_name;
        this.status = status;
        this.date_time = date_time;
        this.price = price;
    }

    public int getImage() {
        return image;
    }

    public String getProject_name() {
        return project_name;
    }

    public String getStatus() {
        return status;
    }

    public String getDate_time() {
        return date_time;
    }

    public String getPrice() {
        return price;
    }
}
