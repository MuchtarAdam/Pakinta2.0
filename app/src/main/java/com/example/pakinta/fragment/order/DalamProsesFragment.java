package com.example.pakinta.fragment.order;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.pakinta.R;
import com.example.pakinta.fragment.OrderFragment;

public class DalamProsesFragment extends Fragment implements View.OnClickListener{

    private TextView tv_riwayat, tv_dalam_proses, tv_terjadwal;
    private ImageView image;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dalam_proses, container, false);

        tv_riwayat = view.findViewById(R.id.tv_riwayat);
        tv_dalam_proses = view.findViewById(R.id.tv_dalam_proses);
        tv_terjadwal = view.findViewById(R.id.tv_terjadwal);

        tv_riwayat.setOnClickListener(this);
        tv_dalam_proses.setOnClickListener(this);
        tv_terjadwal.setOnClickListener(this);

        image = view.findViewById(R.id.image);

        String imageUrl = "https://cdn-icons-png.flaticon.com/128/2576/2576506.png";

        Glide.with(this)
                .load(imageUrl)
                .apply(new RequestOptions().override(400, 400)) // Set the desired image size
                .into(image);

        return view;
    }

    @Override
    public void onClick(View view) {
        Fragment fragment = null;

        if (view.getId() == R.id.tv_riwayat) {
            fragment = new OrderFragment();
        } else if (view.getId() == R.id.tv_dalam_proses) {
            fragment = new DalamProsesFragment();
        } else if (view.getId() == R.id.tv_terjadwal) {
            fragment = new TerjadwalFragment();
        }

        if (fragment != null) {
            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
            transaction.replace(R.id.container, fragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }
}