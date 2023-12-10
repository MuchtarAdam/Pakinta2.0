package com.example.pakinta.datasource;

import com.example.pakinta.R;
import com.example.pakinta.model.HomeServiceModel;

import java.util.ArrayList;
import java.util.List;

public class HomeServiceDataSource {
    List<HomeServiceModel> homeServiceModels;

    public List<HomeServiceModel> getHomeServiceModels() {
        homeServiceModels = new ArrayList<>();

        homeServiceModels.add(new HomeServiceModel("https://cdn-icons-png.flaticon.com/128/2471/2471113.png", R.drawable.baseline_arrow_forward_ios_24,"Pemeliharaan","Layanan perbaikan rumah, mulai dari survei hingga pengerjaan"));
        homeServiceModels.add(new HomeServiceModel("https://cdn-icons-png.flaticon.com/128/2823/2823550.png", R.drawable.baseline_arrow_forward_ios_24,"Pembangunan","Tukang harian maupun jangka panjang untuk segala kebutuhan proyek, mulai dari pembangunan hingga renovasi"));
        homeServiceModels.add(new HomeServiceModel("https://cdn-icons-png.flaticon.com/128/7654/7654950.png", R.drawable.baseline_arrow_forward_ios_24,"Material","Layanan untuk pemesanan bahan yang dibutuhkan"));

        return homeServiceModels;
    }
}
