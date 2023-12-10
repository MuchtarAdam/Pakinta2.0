package com.example.pakinta.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.pakinta.R;
import com.example.pakinta.activity.NotificationActivity;
import com.example.pakinta.adapter.HomeServiceAdapter;
import com.example.pakinta.datasource.HomeServiceDataSource;
import com.example.pakinta.model.HomeServiceModel;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private ImageSlider imageSlider;
    private RecyclerView recyclerView;
    private ImageView notification_button, paykinta;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        paykinta = view.findViewById(R.id.paykinta);

        String imageUrl = "https://cdn-icons-png.flaticon.com/256/8305/8305556.png";

        Glide.with(this)
                .load(imageUrl)
                .apply(new RequestOptions().override(90, 90)) // Set the desired image size
                .into(paykinta);

        imageSlider = view.findViewById(R.id.imageSlider);

        recyclerView = view.findViewById(R.id.rv_home_service);

        notification_button = view.findViewById(R.id.notification_button);

        notification_button.setOnClickListener(view1 -> {
            Intent intent = new Intent(getActivity(), NotificationActivity.class);
            startActivity(intent);
        });

        ArrayList<SlideModel> slideModels = new ArrayList<>();

        slideModels.add(new SlideModel("https://i.pinimg.com/564x/c6/02/f3/c602f381ae36579c1389008d1e6078c4.jpg", ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel("https://i.pinimg.com/564x/86/f5/ee/86f5ee8b7f5c36b0c4c3e9cbe32083e7.jpg", ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel("https://i.pinimg.com/564x/4d/ab/fa/4dabfa9569c2b7de50393340c9a98b6a.jpg", ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel("https://i.pinimg.com/564x/ce/fa/b9/cefab969e2454d363cc609abae645c4d.jpg", ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel("https://i.pinimg.com/564x/7a/23/73/7a23731724faf2d422c9ef1677cc9291.jpg", ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel("https://i.pinimg.com/564x/a2/fa/69/a2fa69538f8fca3e833e5bf71cda02c6.jpg", ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel("https://i.pinimg.com/564x/ae/4e/53/ae4e53d58178c517e13c6798c025fa52.jpg", ScaleTypes.CENTER_CROP));

        imageSlider.setImageList(slideModels, ScaleTypes.CENTER_CROP);


        HomeServiceDataSource dataSource = new HomeServiceDataSource();
        List<HomeServiceModel> homeServiceModels = dataSource.getHomeServiceModels();

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        HomeServiceAdapter homeServiceAdapter = new HomeServiceAdapter(homeServiceModels);
        recyclerView.setAdapter(homeServiceAdapter);

        recyclerView.setNestedScrollingEnabled(false);

        return view;
    }
}
