package com.example.pakinta.fragment;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.pakinta.R;
import com.example.pakinta.adapter.RiwayatAdapter;
import com.example.pakinta.datasource.RiwayatDataSource;
import com.example.pakinta.fragment.order.DalamProsesFragment;
import com.example.pakinta.fragment.order.TerjadwalFragment;
import com.example.pakinta.model.RiwayatModel;

import java.util.List;

public class OrderFragment extends Fragment implements View.OnClickListener {
    private RecyclerView recyclerView;
    private TextView tv_riwayat, tv_dalam_proses, tv_terjadwal;
    private RelativeLayout topBar;
    private LinearLayout tabLayout;
    private CardView cv_payments;
    private RecyclerView rv_riwayat;
    private ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order, container, false);

        topBar = view.findViewById(R.id.topBar);
        tabLayout = view.findViewById(R.id.tabLayout);
        cv_payments = view.findViewById(R.id.cv_payments);
        rv_riwayat = view.findViewById(R.id.rv_riwayat);
        progressBar = view.findViewById(R.id.progressBar);

        topBar.setVisibility(View.INVISIBLE);
        tabLayout.setVisibility(View.INVISIBLE);
        cv_payments.setVisibility(View.INVISIBLE);
        rv_riwayat.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                topBar.setVisibility(View.VISIBLE);
                tabLayout.setVisibility(View.VISIBLE);
                cv_payments.setVisibility(View.VISIBLE);
                rv_riwayat.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.INVISIBLE);
            }
        },750);

        recyclerView = view.findViewById(R.id.rv_riwayat);

        RiwayatDataSource dataSource = new RiwayatDataSource();

        List<RiwayatModel> riwayatModels = dataSource.getRiwayatModels();

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        RiwayatAdapter riwayatAdapter = new RiwayatAdapter(riwayatModels);
        recyclerView.setAdapter(riwayatAdapter);

        tv_riwayat = view.findViewById(R.id.tv_riwayat);
        tv_dalam_proses = view.findViewById(R.id.tv_dalam_proses);
        tv_terjadwal = view.findViewById(R.id.tv_terjadwal);

        tv_riwayat.setOnClickListener(this);
        tv_dalam_proses.setOnClickListener(this);
        tv_terjadwal.setOnClickListener(this);

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


