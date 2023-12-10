package com.example.pakinta.parcelabel;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class PembangunanData implements Parcelable {
    String city, detailLocation, projectName, category, buildingType, note;

    public PembangunanData() {
        this.city = city;
        this.detailLocation = detailLocation;
        this.projectName = projectName;
        this.category = category;
        this.buildingType = buildingType;
        this.note = note;
    }

    public PembangunanData(Parcel in) {
        city = in.readString();
        detailLocation = in.readString();
        projectName = in.readString();
        category = in.readString();
        buildingType = in.readString();
        note = in.readString();
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(city);
        parcel.writeString(detailLocation);
        parcel.writeString(projectName);
        parcel.writeString(category);
        parcel.writeString(buildingType);
        parcel.writeString(note);

    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDetailLocation() {
        return detailLocation;
    }

    public void setDetailLocation(String detailLocation) {
        this.detailLocation = detailLocation;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBuildingType() {
        return buildingType;
    }

    public void setBuildingType(String buildingType) {
        this.buildingType = buildingType;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public static final Creator<PembangunanData> CREATOR = new Creator<PembangunanData>() {
        @Override
        public PembangunanData createFromParcel(Parcel in) {
            return new PembangunanData(in);
        }

        @Override
        public PembangunanData[] newArray(int size) {
            return new PembangunanData[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }
}
