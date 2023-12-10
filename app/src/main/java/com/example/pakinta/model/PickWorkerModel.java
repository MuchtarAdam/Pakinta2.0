package com.example.pakinta.model;

public class PickWorkerModel {
    private String specialist;
    private String price;
    private int amountWorker;
    private String description;

    public PickWorkerModel(String specialist, String price, int amountWorker, String description) {
        this.specialist = specialist;
        this.price = price;
        this.amountWorker = amountWorker;
        this.description = description;
    }

    public String getSpecialist() {
        return specialist;
    }

    public String getPrice() {
        return price;
    }

    public int getAmountWorker() {
        return amountWorker;
    }

    public void setAmountWorker(int amountWorker) {
        this.amountWorker = amountWorker;
    }

    public String getDescription() {
        return description;
    }
}

