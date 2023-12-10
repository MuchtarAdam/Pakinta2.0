package com.example.pakinta.datasource;

import com.example.pakinta.R;
import com.example.pakinta.model.RiwayatModel;

import java.util.ArrayList;
import java.util.List;

public class RiwayatDataSource {
    List<RiwayatModel> riwayatModels;

    public List<RiwayatModel> getRiwayatModels() {

        riwayatModels = new ArrayList<>();

        riwayatModels.add(new RiwayatModel(R.drawable.round_engineering_24,"Pembangunan Rumah","Dibatalkan","24 Juni, 16:18","Rp. 0"));
        riwayatModels.add(new RiwayatModel(R.drawable.round_construction_24,"Perbaikan","Selesai","28 Juni, 16:18"," Rp.400.000"));
        riwayatModels.add(new RiwayatModel(R.drawable.round_engineering_24,"Pembangunan Rumah","Selesai","28 Juni, 16:18","Rp.5.000.000"));
        riwayatModels.add(new RiwayatModel(R.drawable.round_construction_24,"Perbaikan","Selesai","28 Juni, 16:18","Rp.200.000"));
        return riwayatModels;
    }
}
