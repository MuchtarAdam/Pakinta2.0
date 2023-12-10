package com.example.pakinta.parcelabel;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class PickWorkerData implements Parcelable {
    String totalWorkers, totalDays, totalEstimate;

    public PickWorkerData(){
        this.totalWorkers = totalWorkers;
        this.totalDays = totalDays;
        this.totalEstimate = totalEstimate;
    }
    protected PickWorkerData(Parcel in) {
        totalWorkers = in.readString();
        totalDays = in.readString();
        totalEstimate = in.readString();
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(totalWorkers);
        parcel.writeString(totalDays);
        parcel.writeString(totalEstimate);
    }

    public String getTotalWorkers() {
        return totalWorkers;
    }

    public void setTotalWorkers(String totalWorkers) {
        this.totalWorkers = totalWorkers;
    }

    public String getTotalDays() {
        return totalDays;
    }

    public void setTotalDays(String totalDays) {
        this.totalDays = totalDays;
    }

    public String getTotalEstimate() {
        return totalEstimate;
    }

    public void setTotalEstimate(String totalEstimate) {
        this.totalEstimate = totalEstimate;
    }

    public static final Creator<PickWorkerData> CREATOR = new Creator<PickWorkerData>() {
        @Override
        public PickWorkerData createFromParcel(Parcel in) {
            return new PickWorkerData(in);
        }

        @Override
        public PickWorkerData[] newArray(int size) {
            return new PickWorkerData[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }
}
