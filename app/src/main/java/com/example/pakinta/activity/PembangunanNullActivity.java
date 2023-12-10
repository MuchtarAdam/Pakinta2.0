package com.example.pakinta.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.pakinta.R;

public class PembangunanNullActivity extends AppCompatActivity {

    private ImageView image;
    private Button btn_buat_pekerjaan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pembangunan_null);

        image = findViewById(R.id.image);

        btn_buat_pekerjaan = findViewById(R.id.btn_buat_pekerjaan);

        String imageUrl = "https://cdn-icons-png.flaticon.com/128/10365/10365951.png";

        Glide.with(this)
                .load(imageUrl)
                .apply(new RequestOptions().override(300, 300)) // Set the desired image size
                .into(image);

        btn_buat_pekerjaan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PembangunanNullActivity.this, PembangunanActivity.class);
                startActivity(intent);
            }
        });
    }
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

        finish();
    }
}