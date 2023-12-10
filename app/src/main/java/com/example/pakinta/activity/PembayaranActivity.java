package com.example.pakinta.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.pakinta.R;
import com.example.pakinta.parcelabel.DetailWaktuKerjaData;
import com.example.pakinta.parcelabel.PembangunanData;
import com.example.pakinta.parcelabel.PickWorkerData;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class PembayaranActivity extends AppCompatActivity {
    public static final String EXTRA_PICK_WORKER = "extra_pick_worker";
    public static final String EXTRA_FINAL_PEMBANGUNAN = "extra_final_pembangunan";
    public static final String EXTRA_FINAL_DETAIL_WAKTU = "extra_final_detail_waktu";
    private ImageView btn_back_pick_worker;
    private TextView tv_description_price1,tv_description_price2, tv_description_work, tv_description_work_result,tv_total_price_final;
    private ScrollView sv_container_up;
    private LinearLayout bottom_result_bar, ll_topbar_pembayaran;
    private RelativeLayout rv_container_second;
    private Button btn_payment_done, btn_back_to_home;
    private ProgressBar progressBar;
    private ImageView image3, line_pilih_pekerja;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pembayaran);

        btn_back_pick_worker = findViewById(R.id.btn_back_pick_worker);
        SharedPreferences sharedPreferences1 = getSharedPreferences("pembangunan_shared_preferences", Context.MODE_PRIVATE);

        String city = sharedPreferences1.getString("city", "");
        String detailLocation = sharedPreferences1.getString("detailLocation", "");
        String projectName = sharedPreferences1.getString("projectName", "");
        String category = sharedPreferences1.getString("category", "");
        String buildingType = sharedPreferences1.getString("buildingType", "");
        String note = sharedPreferences1.getString("note", "");

        SharedPreferences sharedPreferences2 = getSharedPreferences("detail_waktu_shared_preferences", Context.MODE_PRIVATE);

        String startDate = sharedPreferences2.getString("start_date", "");
        String finishDate = sharedPreferences2.getString("finish_date", "");
        String startTime = sharedPreferences2.getString("start_time", "");
        String finishTime = sharedPreferences2.getString("finish_time", "");
        String workDays = sharedPreferences2.getString("days_work", "");

        PickWorkerData pickWorkerData1 = getIntent().getParcelableExtra(EXTRA_PICK_WORKER);
        String totalWorkers = pickWorkerData1.getTotalWorkers();
        String totalDays = pickWorkerData1.getTotalDays();
        String totalEstimate = pickWorkerData1.getTotalEstimate();

        PickWorkerData pickWorkerData = getIntent().getParcelableExtra(EXTRA_PICK_WORKER);

        tv_description_work = findViewById(R.id.tv_description_work);

                String description_work = "Kota/Kabupaten" +  "\n"
                                + "Detail Lokasi" + "\n"
                                + "Nama Proyek" + "\n"
                                + "Jenis Pekerjaan" + "\n"
                                + "Jenis Bangunan" + "\n"
                                + "Catatan" + "\n"
                                + "Tanggal Mulai" + "\n"
                                + "Tanggal Selesai" + "\n"
                                + "Jam Mulai" + "\n"
                                + "Jam Selesai" + "\n"
                                + "Total Pekerja" + "\n"
                                + "Total Hari" + "\n"
                                + "Hari Kerja";

        tv_description_work.setText(description_work);

        float lineSpacingExtra = 8.0f; // Adjust this value as needed
        float lineSpacingMultiplier = 1.3f; // Adjust this value as needed
        tv_description_work.setLineSpacing(lineSpacingExtra, lineSpacingMultiplier);

        tv_description_work_result = findViewById(R.id.tv_description_work_result);

        String description_work_final = city +  "\n" +
                                        detailLocation + "\n" +
                                        projectName + "\n" +
                                        category + "\n" +
                                        buildingType + "\n" +
                                        note + "\n" +
                                        startDate + "\n" +
                                        finishDate + "\n" +
                                        startTime + "\n" +
                                        finishTime + "\n" +
                                        totalWorkers+ "\n" +
                                        totalDays + "\n" +
                                        workDays;

        tv_description_work_result.setText(description_work_final);

        float lineSpacingExtra2 = 8.0f; // Adjust this value as needed
        float lineSpacingMultiplier2 = 1.3f; // Adjust this value as needed
        tv_description_work_result.setLineSpacing(lineSpacingExtra2, lineSpacingMultiplier2);

        tv_total_price_final = findViewById(R.id.tv_total_price_final);


        double totalEstimateValue = Double.parseDouble(totalEstimate);
        NumberFormat numberFormat = NumberFormat.getInstance(new Locale("en", "US"));
        String formattedTotalEstimate = numberFormat.format(totalEstimateValue);
        tv_total_price_final.setText(formattedTotalEstimate);



        btn_back_pick_worker.setOnClickListener(view -> {

            Intent intent = new Intent(PembayaranActivity.this, PickWorkerActivity.class);
            intent.putExtra(PickWorkerActivity.EXTRA_PICK_WORKER_BACK, pickWorkerData);
            startActivity(intent);
        });

        tv_description_price1 = findViewById(R.id.tv_description_price1);
        tv_description_price2 = findViewById(R.id.tv_description_price2);

        String description_work_final_price =
                "Kategori Pekerjaan: " + category
                + "\n" +
                "Total Hari x Total Pekerja x Harga Pertukang: " + "\n" + "Biaya Survey: " ;
        
        tv_description_price1.setText(description_work_final_price);

        float lineSpacingExtra3 = 8.0f; // Adjust this value as needed
        float lineSpacingMultiplier3 = 1.3f; // Adjust this value as needed
        tv_description_price1.setLineSpacing(lineSpacingExtra3, lineSpacingMultiplier3);

        int survayorCost = 50000;

        if (category.equals("Renovasi")) {
            if (Integer.parseInt(String.valueOf(totalDays)) <= 7) {
                int renovationCost1 = 1500000;

                int totalWorkerxDaysCost = Integer.parseInt(String.valueOf(totalEstimate)) - Integer.parseInt(String.valueOf(renovationCost1)) - survayorCost;

                tv_description_price2.setText(String.valueOf(renovationCost1 + "\n" + totalWorkerxDaysCost + "\n" + survayorCost));

                float lineSpacingExtra4 = 8.0f; // Adjust this value as needed
                float lineSpacingMultiplier4 = 1.3f; // Adjust this value as needed
                tv_description_price2.setLineSpacing(lineSpacingExtra4, lineSpacingMultiplier4);

            }else {
                int renovationCost2 = 3000000;

                int totalWorkerxDaysCost = Integer.parseInt(String.valueOf(totalEstimate)) - Integer.parseInt(String.valueOf(renovationCost2)) - survayorCost;

                tv_description_price2.setText(String.valueOf(renovationCost2 + "\n" + totalWorkerxDaysCost + "\n" + survayorCost));
                float lineSpacingExtra4 = 8.0f; // Adjust this value as needed
                float lineSpacingMultiplier4 = 1.3f; // Adjust this value as needed
                tv_description_price2.setLineSpacing(lineSpacingExtra4, lineSpacingMultiplier4);

            }
        }else if (category.equals("Pembangunan")) {
            int buildCost = 5500000;

            int totalWorkerxDaysCost = Integer.parseInt(String.valueOf(totalEstimate)) - Integer.parseInt(String.valueOf(buildCost)) - survayorCost;

            tv_description_price2.setText(String.valueOf(buildCost + "\n" + totalWorkerxDaysCost + "\n" + survayorCost));
            float lineSpacingExtra4 = 8.0f; // Adjust this value as needed
            float lineSpacingMultiplier4 = 1.3f; // Adjust this value as needed
            tv_description_price2.setLineSpacing(lineSpacingExtra4, lineSpacingMultiplier4);
        }


        tv_description_work_result.setText(description_work_final);

        float lineSpacingExtra4 = 8.0f; // Adjust this value as needed
        float lineSpacingMultiplier4 = 1.3f; // Adjust this value as needed
        tv_description_price1.setLineSpacing(lineSpacingExtra4, lineSpacingMultiplier4);


        ll_topbar_pembayaran = findViewById(R.id.ll_topbar_pembayaran);
        line_pilih_pekerja= findViewById(R.id.line_pilih_pekerja);

        sv_container_up = findViewById(R.id.sv_container_up);
        bottom_result_bar = findViewById(R.id.bottom_result_bar);
        rv_container_second = findViewById(R.id.rv_container_second);

        btn_payment_done = findViewById(R.id.btn_payment_done);
        progressBar = findViewById(R.id.progressBar);
        image3 = findViewById(R.id.image3);
        String imageUrl = "https://cdn-icons-png.flaticon.com/128/4490/4490381.png";

        Glide.with(this)
                .load(imageUrl)
                .apply(new RequestOptions().override(300, 300)) // Set the desired image size
                .into(image3);

        btn_payment_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sv_container_up.setVisibility(View.INVISIBLE);
                bottom_result_bar.setVisibility(View.INVISIBLE);
                ll_topbar_pembayaran.setVisibility(View.INVISIBLE);
                line_pilih_pekerja.setVisibility(View.INVISIBLE);
                progressBar.setVisibility(View.VISIBLE);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ll_topbar_pembayaran.setVisibility(View.INVISIBLE);
                        line_pilih_pekerja.setVisibility(View.INVISIBLE);
                        sv_container_up.setVisibility(View.INVISIBLE);
                        bottom_result_bar.setVisibility(View.INVISIBLE);
                        progressBar.setVisibility(View.INVISIBLE);
                        rv_container_second.setVisibility(View.VISIBLE);
                    }
                }, 1500);
            }
        });

        btn_back_to_home = findViewById(R.id.btn_back_to_home);
        btn_back_to_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PembayaranActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}