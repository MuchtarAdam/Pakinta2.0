package com.example.pakinta.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.SearchView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.pakinta.R;
import com.example.pakinta.parcelabel.PembangunanData;

public class PembangunanActivity extends AppCompatActivity {
    private ScrollView sv_container_first;
    private RelativeLayout rv_container_second;
    public static final String EXTRA_PEMBANGUNAN_BACK = "extra_pembangunan_back";
    private ImageView btn_back_pembangunan_null;
    private AutoCompleteTextView autoCompleteTextView_category, autoCompleteTextView_city, autoCompleteTextView_jenis_bangunan;
    private Button btn_lanjut_to_detail, btn_lanjut_to_detail_2;
    private EditText et_detail_location, et_name_project,et_note;
    private ImageView image1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pembangunan);

        btn_back_pembangunan_null = findViewById(R.id.btn_back_pembangunan_null);

        sv_container_first = findViewById(R.id.sv_container_first);
        rv_container_second = findViewById(R.id.rv_container_second);

        autoCompleteTextView_category = findViewById(R.id.auto_complete_txt);
        autoCompleteTextView_city = findViewById(R.id.auto_complete_txt_city);
        autoCompleteTextView_jenis_bangunan = findViewById(R.id.auto_complete_txt_jenis_bangunan);

        et_detail_location = findViewById(R.id.et_detail_location);
        et_name_project = findViewById(R.id.et_name_project);
        et_note = findViewById(R.id.et_note);

        image1 = findViewById(R.id.image1);
        String imageUrl = "https://cdn-icons-png.flaticon.com/128/1686/1686925.png";

        Glide.with(this)
                .load(imageUrl)
                .apply(new RequestOptions().override(300, 300)) // Set the desired image size
                .into(image1);

        btn_lanjut_to_detail = findViewById(R.id.btn_lanjut_to_detail);

        btn_lanjut_to_detail_2 = findViewById(R.id.btn_lanjut_to_detail_2);

        PembangunanData pembangunanDataBack = getIntent().getParcelableExtra(EXTRA_PEMBANGUNAN_BACK);

        if (pembangunanDataBack != null) {
            autoCompleteTextView_city.setText(pembangunanDataBack.getCity());
            et_detail_location.setText(pembangunanDataBack.getDetailLocation());
            et_name_project.setText(pembangunanDataBack.getProjectName());
            autoCompleteTextView_category.setText(pembangunanDataBack.getCategory());
            autoCompleteTextView_jenis_bangunan.setText(pembangunanDataBack.getBuildingType());
            et_note.setText(pembangunanDataBack.getNote());
        }

        btn_back_pembangunan_null.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PembangunanActivity.this, PembangunanNullActivity.class);
                startActivity(intent);
            }
        });

        autoCompleteTextView_city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogCity();
            }
        });

        autoCompleteTextView_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogCategory();
            }
        });

        autoCompleteTextView_jenis_bangunan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogBuildingType();
            }
        });

        btn_lanjut_to_detail.setOnClickListener(view -> {
            sv_container_first.setVisibility(View.INVISIBLE);
            rv_container_second.setVisibility(View.VISIBLE);
        });

        btn_lanjut_to_detail_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String city = autoCompleteTextView_city.getText().toString().trim();
                String detailLocation = et_detail_location.getText().toString().trim();
                String projectName = et_name_project.getText().toString().trim();
                String category = autoCompleteTextView_category.getText().toString().trim();
                String buildingType = autoCompleteTextView_jenis_bangunan.getText().toString().trim();
                String note = et_note.getText().toString().trim();

                if (TextUtils.isEmpty(autoCompleteTextView_city.getText().toString()) ||
                        TextUtils.isEmpty(autoCompleteTextView_category.getText().toString()) ||
                        TextUtils.isEmpty(et_detail_location.getText().toString()) ||
                        TextUtils.isEmpty(et_name_project.getText().toString())) {
                    btn_lanjut_to_detail.setError("Please fill in all the fields");
                    Toast.makeText(PembangunanActivity.this, "Please fill in all the fields", Toast.LENGTH_SHORT).show();
                } else {
                    PembangunanData pembangunanData_intent = new PembangunanData();
                    pembangunanData_intent.setCity(city);
                    pembangunanData_intent.setDetailLocation(detailLocation);
                    pembangunanData_intent.setProjectName(projectName);
                    pembangunanData_intent.setCategory(category);
                    pembangunanData_intent.setBuildingType(buildingType);
                    pembangunanData_intent.setNote(note);

                    Intent intent = new Intent(PembangunanActivity.this, DetailWaktuKerjaActivity.class);
                    intent.putExtra(DetailWaktuKerjaActivity.EXTRA_PEMBANGUNAN, pembangunanData_intent);
                    intent.putExtra(PembayaranActivity.EXTRA_FINAL_PEMBANGUNAN, pembangunanData_intent);

                    SharedPreferences sharedPreferences = getSharedPreferences("pembangunan_shared_preferences", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    // Menyimpan data ke dalam SharedPreferences
                    editor.putString("city", city);
                    editor.putString("detailLocation", detailLocation);
                    editor.putString("projectName", projectName);
                    editor.putString("category", category);
                    editor.putString("buildingType", buildingType);
                    editor.putString("note", note);

                    editor.apply();
                    startActivity(intent);
                }
            }
        });

    }

    private void showDialogBuildingType() {
        final Dialog dialog = new Dialog(PembangunanActivity.this); // Use PembangunanActivity.this as the Context
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.bottomsheet_layout_jenis_bangunan);

        LinearLayout hotelLayout = dialog.findViewById(R.id.layoutHotel);
        LinearLayout infrastrukturLayout = dialog.findViewById(R.id.layoutInfrastrukturPublik);
        LinearLayout kafeLayout = dialog.findViewById(R.id.layoutKafe);
        LinearLayout kostLayout = dialog.findViewById(R.id.layoutKost);
        LinearLayout mallLayout = dialog.findViewById(R.id.layoutMall);
        LinearLayout perkantoranLayout = dialog.findViewById(R.id.layoutPerkantoran);
        LinearLayout restoranLayout = dialog.findViewById(R.id.layoutRestoran);
        LinearLayout rumahLayout = dialog.findViewById(R.id.layoutRumah);
        LinearLayout rukoLayout = dialog.findViewById(R.id.layoutRuko);
        LinearLayout sekolahLayout = dialog.findViewById(R.id.layoutSekolah);
        LinearLayout tempatIbadahLayout = dialog.findViewById(R.id.layoutTempatIbadah);


        hotelLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                String selectedText = "Hotel";
                autoCompleteTextView_jenis_bangunan.setText(selectedText);
                Toast.makeText(PembangunanActivity.this, selectedText + " is Selected", Toast.LENGTH_SHORT).show();
            }
        });
        infrastrukturLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                String selectedText = "Infrastruktur Publik";
                autoCompleteTextView_jenis_bangunan.setText(selectedText);
                Toast.makeText(PembangunanActivity.this, selectedText + " is Selected", Toast.LENGTH_SHORT).show();
            }
        });
        kafeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                String selectedText = "Kafe";
                autoCompleteTextView_jenis_bangunan.setText(selectedText);
                Toast.makeText(PembangunanActivity.this, selectedText + " is Selected", Toast.LENGTH_SHORT).show();
            }
        });
        kostLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                String selectedText = "Kost";
                autoCompleteTextView_jenis_bangunan.setText(selectedText);
                Toast.makeText(PembangunanActivity.this, selectedText + " is Selected", Toast.LENGTH_SHORT).show();
            }
        });
        mallLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                String selectedText = "Mall";
                autoCompleteTextView_jenis_bangunan.setText(selectedText);
                Toast.makeText(PembangunanActivity.this, selectedText + " is Selected", Toast.LENGTH_SHORT).show();
            }
        });
        perkantoranLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                String selectedText = "Perkantoran";
                autoCompleteTextView_jenis_bangunan.setText(selectedText);
                Toast.makeText(PembangunanActivity.this, selectedText + " is Selected", Toast.LENGTH_SHORT).show();
            }
        });
        restoranLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                String selectedText = "Restoran";
                autoCompleteTextView_jenis_bangunan.setText(selectedText);
                Toast.makeText(PembangunanActivity.this, selectedText + " is Selected", Toast.LENGTH_SHORT).show();
            }
        });
        rumahLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                String selectedText = "Rumah";
                autoCompleteTextView_jenis_bangunan.setText(selectedText);
                Toast.makeText(PembangunanActivity.this, selectedText + " is Selected", Toast.LENGTH_SHORT).show();
            }
        });
        rukoLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                String selectedText = "Ruko";
                autoCompleteTextView_jenis_bangunan.setText(selectedText);
                Toast.makeText(PembangunanActivity.this, selectedText + " is Selected", Toast.LENGTH_SHORT).show();
            }
        });
        sekolahLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                String selectedText = "Sekolah";
                autoCompleteTextView_jenis_bangunan.setText(selectedText);
                Toast.makeText(PembangunanActivity.this, selectedText + " is Selected", Toast.LENGTH_SHORT).show();
            }
        });
        tempatIbadahLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                String selectedText = "Tempat Ibadah";
                autoCompleteTextView_jenis_bangunan.setText(selectedText);
                Toast.makeText(PembangunanActivity.this, selectedText + " is Selected", Toast.LENGTH_SHORT).show();
            }
        });

        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);
    }

    private void showDialogCity() {
        final Dialog dialog = new Dialog(PembangunanActivity.this); // Use PembangunanActivity.this as the Context
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.bottomsheet_layout_city);

        LinearLayout pangkepLayout = dialog.findViewById(R.id.layoutPangkep);
        LinearLayout marosLayout = dialog.findViewById(R.id.layoutMaros);
        LinearLayout makassarLayout = dialog.findViewById(R.id.layoutMakassar);
        LinearLayout gowaLayout = dialog.findViewById(R.id.layoutGowa);
        LinearLayout takalarLayout = dialog.findViewById(R.id.layoutTakalar);

        pangkepLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                String selectedText = "Pangkep";
                autoCompleteTextView_city.setText(selectedText);
                Toast.makeText(PembangunanActivity.this, selectedText + " is Selected", Toast.LENGTH_SHORT).show();
            }
        });

        marosLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                String selectedText = "Maros";
                autoCompleteTextView_city.setText(selectedText);
                Toast.makeText(PembangunanActivity.this, selectedText + " is Selected", Toast.LENGTH_SHORT).show();
            }
        });
        makassarLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                String selectedText = "Makassar";
                autoCompleteTextView_city.setText(selectedText);
                Toast.makeText(PembangunanActivity.this, selectedText + " is Selected", Toast.LENGTH_SHORT).show();
            }
        });

        gowaLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                String selectedText = "Gowa";
                autoCompleteTextView_city.setText(selectedText);
                Toast.makeText(PembangunanActivity.this, selectedText + " is Selected", Toast.LENGTH_SHORT).show();
            }
        });
        takalarLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                String selectedText = "Takalar";
                autoCompleteTextView_city.setText(selectedText);
                Toast.makeText(PembangunanActivity.this, selectedText + " is Selected", Toast.LENGTH_SHORT).show();
            }
        });

        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);
    }

    private void showDialogCategory() {
        final Dialog dialog = new Dialog(PembangunanActivity.this); // Use PembangunanActivity.this as the Context
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.bottomsheet_layout_kategori);

        LinearLayout pembangunanLayout = dialog.findViewById(R.id.layoutPembangunan);
        LinearLayout renovasiLayout = dialog.findViewById(R.id.layoutRenovasi);

        pembangunanLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                String selectedText = "Pembangunan";
                autoCompleteTextView_category.setText(selectedText);
                Toast.makeText(PembangunanActivity.this, selectedText + " is Selected", Toast.LENGTH_SHORT).show();
            }
        });

        renovasiLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                String selectedText = "Renovasi";
                autoCompleteTextView_category.setText(selectedText);
                Toast.makeText(PembangunanActivity.this, selectedText + " is Selected", Toast.LENGTH_SHORT).show();
            }
        });

        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, PembangunanNullActivity.class);
        startActivity(intent);

        finish();
    }

}