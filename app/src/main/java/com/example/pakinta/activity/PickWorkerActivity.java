package com.example.pakinta.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pakinta.R;
import com.example.pakinta.adapter.PickWorkerAdapter;
import com.example.pakinta.datasource.PickWorkerDataSource;
import com.example.pakinta.model.PickWorkerModel;
import com.example.pakinta.parcelabel.DetailWaktuKerjaData;
import com.example.pakinta.parcelabel.PickWorkerData;
import java.util.List;

public class PickWorkerActivity extends AppCompatActivity implements PickWorkerAdapter.AmountChangeListener {
    public static final String EXTRA_DETAIL_WAKTU = "extra_detail_waktu";
    public static final String EXTRA_PICK_WORKER_BACK = "pick_worker_back";
    private ImageView btn_back_detail_waktu_pekerja;
    private RecyclerView recyclerView;
    private TextView tv_total_day, tv_amount_worker_result, tv_total_price;
    private List<PickWorkerModel> pickWorkerModels;
    private PickWorkerAdapter pickWorkerAdapter;
//    public int price_per_day_perbaikan = 100000;
    public int price_per_day_renovasi_ringan = 1500000;
    public int price_per_day_renovasi_berat = 3000000;
    public int price_per_day_pembangunan = 5500000;
    public int price_survey = 50000;
    private int totalAmount;
    private int totalPrice;
    private Button btn_lanjut_to_pembayaran;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_worker);

        recyclerView = findViewById(R.id.rv_pilih_pekerja);
        btn_back_detail_waktu_pekerja = findViewById(R.id.btn_back_detail_waktu_pekerja);

        tv_total_day = findViewById(R.id.tv_total_day);
        tv_amount_worker_result = findViewById(R.id.tv_amount_worker_result);
        tv_total_price = findViewById(R.id.tv_total_price);

        SharedPreferences sharedPreferences = getSharedPreferences("pembangunan_shared_preferences", Context.MODE_PRIVATE);
        DetailWaktuKerjaData detailWaktuKerjaData = getIntent().getParcelableExtra(EXTRA_DETAIL_WAKTU);
        PickWorkerData pickWorkerDataBack = getIntent().getParcelableExtra(EXTRA_PICK_WORKER_BACK);

        if (pickWorkerDataBack != null) {
            tv_amount_worker_result.setText(pickWorkerDataBack.getTotalWorkers());
            tv_total_day.setText(pickWorkerDataBack.getTotalDays());
            tv_total_price.setText(pickWorkerDataBack.getTotalEstimate());
        } else {
            String amountDays = detailWaktuKerjaData.getAmountDays();
            tv_total_day.setText(amountDays);

            String category = sharedPreferences.getString("category", "");
            int basePrice = price_survey;

            if (category.equals("Renovasi")) {
                if (Integer.parseInt(amountDays) <= 7) {
                    basePrice += price_per_day_renovasi_ringan;
                } else {
                    basePrice += price_per_day_renovasi_berat;
                }
            } else if (category.equals("Pembangunan")) {
                basePrice += price_per_day_pembangunan;
            }

            tv_total_price.setText(String.valueOf(basePrice));
        }

        btn_lanjut_to_pembayaran = findViewById(R.id.btn_lanjut_to_pembayaran);

        btn_back_detail_waktu_pekerja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PickWorkerActivity.this, DetailWaktuKerjaActivity.class);
                intent.putExtra(DetailWaktuKerjaActivity.EXTRA_DETAIL_WAKTU_BACK, detailWaktuKerjaData);
                startActivity(intent);
            }
        });

        PickWorkerDataSource dataSource = new PickWorkerDataSource();
        pickWorkerModels = dataSource.getPickWorkerModels();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        pickWorkerAdapter = new PickWorkerAdapter(pickWorkerModels);
        pickWorkerAdapter.setAmountChangeListener(this);
        recyclerView.setAdapter(pickWorkerAdapter);

        btn_lanjut_to_pembayaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                totalAmount = calculateTotalAmount();
                totalPrice = calculateTotalPrice();

                String amountDays = detailWaktuKerjaData.getAmountDays();
                String category = sharedPreferences.getString("category","");

                if ((category.equals("Renovasi") && Integer.parseInt(amountDays) <= 7 && totalAmount < 3) ||
                        (category.equals("Renovasi") && Integer.parseInt(amountDays) > 7 && totalAmount < 5) ||
                        (category.equals("Pembangunan") && totalAmount < 10)) {
                    btn_lanjut_to_pembayaran.setError("Please pick workers");
                    Toast.makeText(PickWorkerActivity.this, "Please pick workers", Toast.LENGTH_SHORT).show();
                } else {
                    String amount_workers = String.valueOf(totalAmount);
                    String amount_days = tv_total_day.getText().toString().trim();
                    String total_estimate = tv_total_price.getText().toString().trim();

                    PickWorkerData pickWorkerData_intent = new PickWorkerData();
                    pickWorkerData_intent.setTotalWorkers(amount_workers);
                    pickWorkerData_intent.setTotalDays(amount_days);

                    pickWorkerData_intent.setTotalEstimate(total_estimate);

                    Intent intent = new Intent(PickWorkerActivity.this, PembayaranActivity.class);
                    intent.putExtra(PembayaranActivity.EXTRA_PICK_WORKER, pickWorkerData_intent);

                    SharedPreferences sharedPreferences = getSharedPreferences("pick_worker_shared_preferences", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor1 = sharedPreferences.edit();
                    editor1.putString("amount_workers", amount_workers);
                    editor1.putString("amount_days", amount_days);
                    editor1.putString("total_estimate", total_estimate);


                    editor1.apply();
                    startActivity(intent);
                }
            }
        });
    }

    private int calculateTotalAmount() {
        int totalAmount = 0;
        for (PickWorkerModel model : pickWorkerModels) {
            totalAmount += model.getAmountWorker();
        }
        return totalAmount;
    }

    @Override
    public void onAmountChanged(int position, int newAmount) {
        SharedPreferences sharedPreferencesPembangunan = getSharedPreferences("pembangunan_shared_preferences", Context.MODE_PRIVATE);
        DetailWaktuKerjaData detailWaktuKerjaData = getIntent().getParcelableExtra(EXTRA_DETAIL_WAKTU);

        String category = sharedPreferencesPembangunan.getString("category", "");
        String amountDays = detailWaktuKerjaData.getAmountDays();

        pickWorkerModels.get(position).setAmountWorker(newAmount);
        pickWorkerAdapter.notifyDataSetChanged();

        int totalAmount = calculateTotalAmount();
        int totalPrice = calculateTotalPrice();

        int basePrice = price_survey;
        if (category.equals("Renovasi")) {
            if (Integer.parseInt(amountDays) <= 7) {
                basePrice += price_per_day_renovasi_ringan;
            } else {
                basePrice += price_per_day_renovasi_berat;
            }
        } else if (category.equals("Pembangunan")) {
            basePrice += price_per_day_pembangunan;
        }

        int totalEstimate = totalPrice * Integer.parseInt(amountDays) + basePrice;

        tv_total_price.setText(String.valueOf(totalEstimate));
        tv_amount_worker_result.setText(String.valueOf(totalAmount));
    }


    private int calculatePrice(int amount, int position) {
        int price;
        if (position == 7) {
            price = amount * 165000;
        } else if (position == 8) {
            price = amount * 174500;
        } else if (position == 9) {
            price = amount * 185000;
        } else if (position == 10) {
            price = amount * 182400;
        } else if (position == 11) {
            price = amount * 168500;
        } else {
            price = amount * 160000;
        }
        return price;
    }

    private int calculateTotalPrice() {
        int totalPrice = 0;
        for (PickWorkerModel model : pickWorkerModels) {
            int amount = model.getAmountWorker();
            int position = pickWorkerModels.indexOf(model);
            int price = calculatePrice(amount , position);
            totalPrice += price;
        }
        return totalPrice;
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, DetailWaktuKerjaActivity.class);
        startActivity(intent);
        finish();
    }
}
