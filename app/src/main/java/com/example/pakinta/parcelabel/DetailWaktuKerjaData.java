package com.example.pakinta.parcelabel;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class DetailWaktuKerjaData implements Parcelable {
    String startDate, finishDate, startTime, finishTime, workDays, amountDays;

    public DetailWaktuKerjaData(){
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.startTime = startTime;
        this.finishTime = finishTime;
        this.workDays = workDays;
        this.amountDays = workDays;
    }

    protected DetailWaktuKerjaData(Parcel in) {
        startDate = in.readString();
        finishDate = in.readString();
        startTime = in.readString();
        finishTime = in.readString();
        workDays = in.readString();
        amountDays = in.readString();
    }
    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(startDate);
        parcel.writeString(finishDate);
        parcel.writeString(startTime);
        parcel.writeString(finishTime);
        parcel.writeString(workDays);
        parcel.writeString(amountDays);
    }

    public static final Creator<DetailWaktuKerjaData> CREATOR = new Creator<DetailWaktuKerjaData>() {
        @Override
        public DetailWaktuKerjaData createFromParcel(Parcel in) {
            return new DetailWaktuKerjaData(in);
        }

        @Override
        public DetailWaktuKerjaData[] newArray(int size) {
            return new DetailWaktuKerjaData[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(String finishDate) {
        this.finishDate = finishDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }

    public String getWorkDays() {
        return workDays;
    }

    public void setWorkDays(String workDays) {
        this.workDays = workDays;
    }

    public String getAmountDays() {
        return amountDays;
    }

    public void setAmountDays(String amountDays) {
        this.amountDays = amountDays;
    }
}
