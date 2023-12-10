package com.example.pakinta.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
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
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.pakinta.R;
import com.example.pakinta.parcelabel.DetailWaktuKerjaData;
import com.example.pakinta.parcelabel.PembangunanData;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class DetailWaktuKerjaActivity extends AppCompatActivity {
    public static final String EXTRA_PEMBANGUNAN = "extra_pembangunan";
    public static final String EXTRA_DETAIL_WAKTU_BACK = "extra_detail_waktu";
    private Dialog dialog;
    private ImageView btn_back_to_pembangunan;
    private CheckBox checkboxSenin, checkboxSelasa, checkboxRabu, checkboxKamis, checkboxJumat, checkboxSabtu, checkboxMinggu;
    private EditText et_start_date, et_finish_date, et_start_time, et_finish_time;
    private AutoCompleteTextView auto_complete_txt_work_day;
    private Button btn_lanjut_to_pick_worker;
    public TextView tv_amount_day;
    private int differenceInDays = 0;
    private int selectedDaysCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_waktu_kerja);

        et_start_date = findViewById(R.id.et_start_date);
        et_finish_date = findViewById(R.id.et_finish_date);

        et_start_time = findViewById(R.id.et_start_time);
        et_finish_time = findViewById(R.id.et_finish_time);

        auto_complete_txt_work_day = findViewById(R.id.auto_complete_txt_work_day);

        btn_lanjut_to_pick_worker = findViewById(R.id.btn_lanjut_to_pick_worker);
        btn_back_to_pembangunan = findViewById(R.id.btn_back_to_pembangunan);

        tv_amount_day = findViewById(R.id.tv_amount_day);

        PembangunanData pembangunanDataBack = getIntent().getParcelableExtra(EXTRA_PEMBANGUNAN);

        DetailWaktuKerjaData detailWaktuKerjaDataBack = getIntent().getParcelableExtra(EXTRA_DETAIL_WAKTU_BACK);

        if (detailWaktuKerjaDataBack != null){
            et_start_date.setText(detailWaktuKerjaDataBack.getStartDate());
            et_finish_date.setText(detailWaktuKerjaDataBack.getFinishDate());
            et_start_time.setText(detailWaktuKerjaDataBack.getStartTime());
            et_finish_time.setText(detailWaktuKerjaDataBack.getFinishTime());
            auto_complete_txt_work_day.setText(detailWaktuKerjaDataBack.getWorkDays());
            tv_amount_day.setText(detailWaktuKerjaDataBack.getAmountDays());
        }

        auto_complete_txt_work_day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogDay();
            }
        });

        et_start_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                DatePickerDialog.OnDateSetListener date = (view1, year, monthOfYear, dayOfMonth) -> {
                    calendar.set(Calendar.YEAR, year);
                    calendar.set(Calendar.MONTH, monthOfYear);
                    calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    String strFormatDefault = "d MMMM yyyy";
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(strFormatDefault, Locale.getDefault());
                    et_start_date.setText(simpleDateFormat.format(calendar.getTime()));


                };
                new DatePickerDialog(DetailWaktuKerjaActivity.this, date,
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        et_finish_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                DatePickerDialog.OnDateSetListener date = (view1, year, monthOfYear, dayOfMonth) -> {
                    calendar.set(Calendar.YEAR, year);
                    calendar.set(Calendar.MONTH, monthOfYear);
                    calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    String strFormatDefault = "d MMMM yyyy";
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(strFormatDefault, Locale.getDefault());
                    et_finish_date.setText(simpleDateFormat.format(calendar.getTime()));

                };
                new DatePickerDialog(DetailWaktuKerjaActivity.this, date,
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }

        });

        et_start_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);

                // Create a time picker dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(DetailWaktuKerjaActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                String selectedTime = String.format("%02d:%02d", hourOfDay, minute);
                                et_start_time.setText(selectedTime);
                            }
                        }, hour, minute, true); // Set is24HourView to true

                // Show the time picker dialog
                timePickerDialog.show();
            }
        });

        et_finish_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);

                // Create a time picker dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(DetailWaktuKerjaActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                String selectedTime = String.format("%02d:%02d", hourOfDay, minute);
                                et_finish_time.setText(selectedTime);
                            }
                        }, hour, minute, true); // Set is24HourView to true

                // Show the time picker dialog
                timePickerDialog.show();
            }
        });

        btn_back_to_pembangunan.setOnClickListener(view -> {

            Intent intent = new Intent(DetailWaktuKerjaActivity.this, PembangunanActivity.class);
            intent.putExtra(PembangunanActivity.EXTRA_PEMBANGUNAN_BACK, pembangunanDataBack);
            startActivity(intent);

        });

        btn_lanjut_to_pick_worker.setOnClickListener(view -> {

            String start_date = et_start_date.getText().toString().trim();
            String finish_date = et_finish_date.getText().toString().trim();
            String start_time = et_start_time.getText().toString().trim();
            String finish_time = et_finish_time.getText().toString().trim();
            String days_work = auto_complete_txt_work_day.getText().toString().trim();
            String amountDays = tv_amount_day.getText().toString().trim();

            if (TextUtils.isEmpty(auto_complete_txt_work_day.getText().toString()) ||
                    TextUtils.isEmpty(et_start_date.getText().toString()) ||
                    TextUtils.isEmpty(et_finish_date.getText().toString()) ||
                    TextUtils.isEmpty(et_start_time.getText().toString()) ||
                    TextUtils.isEmpty(et_finish_time.getText().toString())) {
                btn_lanjut_to_pick_worker.setError("Please fill in all the fields");
                Toast.makeText(DetailWaktuKerjaActivity.this, "Please fill in all the fields", Toast.LENGTH_SHORT).show();
            } else {

                DetailWaktuKerjaData detailWaktuKerjaData_intent = new DetailWaktuKerjaData();
                detailWaktuKerjaData_intent.setStartDate(start_date);
                detailWaktuKerjaData_intent.setFinishDate(finish_date);
                detailWaktuKerjaData_intent.setStartTime(start_time);
                detailWaktuKerjaData_intent.setFinishTime(finish_time);
                detailWaktuKerjaData_intent.setWorkDays(days_work);
                detailWaktuKerjaData_intent.setAmountDays(amountDays);

                Intent intent = new Intent(DetailWaktuKerjaActivity.this, PickWorkerActivity.class);
                intent.putExtra(PickWorkerActivity.EXTRA_DETAIL_WAKTU, detailWaktuKerjaData_intent);
                intent.putExtra(PembayaranActivity.EXTRA_FINAL_DETAIL_WAKTU, detailWaktuKerjaData_intent);

                SharedPreferences sharedPreferences = getSharedPreferences("detail_waktu_shared_preferences", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("start_date", start_date);
                editor.putString("finish_date", finish_date);
                editor.putString("start_time", start_time);
                editor.putString("finish_time", finish_time);
                editor.putString("days_work", days_work);
                editor.putString("amount_days", amountDays);

                editor.apply();
                startActivity(intent);
            }
        });
    }
    private void showDialogDay() {
        dialog = new Dialog(DetailWaktuKerjaActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.bottomsheet_layout_day);

        Button btn_pilih = dialog.findViewById(R.id.btn_pilih);

        checkboxSenin = dialog.findViewById(R.id.checkboxSenin);
        checkboxSelasa = dialog.findViewById(R.id.checkboxSelasa);
        checkboxRabu = dialog.findViewById(R.id.checkboxRabu);
        checkboxKamis = dialog.findViewById(R.id.checkboxKamis);
        checkboxJumat = dialog.findViewById(R.id.checkboxJumat);
        checkboxSabtu = dialog.findViewById(R.id.checkboxSabtu);
        checkboxMinggu = dialog.findViewById(R.id.checkboxMinggu);

        LinearLayout seninLayout = dialog.findViewById(R.id.layoutSenin);
        LinearLayout selasaLayout = dialog.findViewById(R.id.layoutSelasa);
        LinearLayout rabuLayout = dialog.findViewById(R.id.layoutRabu);
        LinearLayout kamisLayout = dialog.findViewById(R.id.layoutKamis);
        LinearLayout jumatLayout = dialog.findViewById(R.id.layoutJumat);
        LinearLayout sabtuLayout = dialog.findViewById(R.id.layoutSabtu);
        LinearLayout mingguLayout = dialog.findViewById(R.id.layoutMinggu);

        StringBuilder selectedDaysBuilder = new StringBuilder();

        seninLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String selectedText = "Senin";
                checkboxSenin.setChecked(true);
                updateSelectedDays(selectedText, selectedDaysBuilder, checkboxSenin.isChecked());

            }
        });

        selasaLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String selectedText = "Selasa";
                checkboxSelasa.setChecked(true);
                updateSelectedDays(selectedText, selectedDaysBuilder, checkboxSelasa.isChecked());
            }
        });

        rabuLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String selectedText = "Rabu";
                checkboxRabu.setChecked(true);
                updateSelectedDays(selectedText, selectedDaysBuilder, checkboxRabu.isChecked());
            }
        });

        kamisLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String selectedText = "Kamis";
                checkboxKamis.setChecked(true);
                updateSelectedDays(selectedText, selectedDaysBuilder, checkboxKamis.isChecked());
            }
        });

        jumatLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String selectedText = "Jumat";
                checkboxJumat.setChecked(true);
                updateSelectedDays(selectedText, selectedDaysBuilder, checkboxJumat.isChecked());
            }
        });

        sabtuLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String selectedText = "Sabtu";
                checkboxSabtu.setChecked(true);
                updateSelectedDays(selectedText, selectedDaysBuilder, checkboxSabtu.isChecked());
            }
        });

        mingguLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String selectedText = "Minggu";
                checkboxMinggu.setChecked(true);
                updateSelectedDays(selectedText, selectedDaysBuilder, checkboxMinggu.isChecked());
            }
        });

        auto_complete_txt_work_day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringBuilder selectedDaysBuilder = new StringBuilder(); // Reset the selectedDaysBuilder
                showDialogDay();
            }
        });

        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);

        btn_pilih.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                auto_complete_txt_work_day.setText(selectedDaysBuilder.toString());
                dialog.dismiss();

                String startDateString = et_start_date.getText().toString();
                String finishDateString = et_finish_date.getText().toString();

                SimpleDateFormat dateFormat = new SimpleDateFormat("d MMMM yyyy", Locale.getDefault());
                try {
                    Calendar startCalendar = Calendar.getInstance();
                    startCalendar.setTime(dateFormat.parse(startDateString));

                    Calendar finishCalendar = Calendar.getInstance();
                    finishCalendar.setTime(dateFormat.parse(finishDateString));

                    long differenceInMillis = finishCalendar.getTimeInMillis() - startCalendar.getTimeInMillis();
                    long differenceInDays = TimeUnit.DAYS.convert(differenceInMillis, TimeUnit.MILLISECONDS);
                    differenceInDays += 1;

                    int result = (int) ((differenceInDays / 7 * selectedDaysCount)  + 1);
                    tv_amount_day.setText(String.valueOf(result));


                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void updateSelectedDays(String selectedText, StringBuilder selectedDaysBuilder, boolean isChecked) {
        String separator = ", ";
        if (isChecked) {
            // Day is not already selected, add it to selectedDaysBuilder
            if (selectedDaysBuilder.length() > 0) {
                // Check if the selectedText already exists in the builder
                if (selectedDaysBuilder.indexOf(selectedText) != -1) {
                    return; // Day already exists, do not add it again
                }
                selectedDaysBuilder.append(separator);
            }
            selectedDaysBuilder.append(selectedText);
        } else {
            // Day is already selected, remove it from selectedDaysBuilder
            int index = selectedDaysBuilder.indexOf(selectedText);
            if (index != -1) {
                selectedDaysBuilder.delete(index, index + selectedText.length());
                // Remove separator if it's the last day in the list
                if (selectedDaysBuilder.length() >= separator.length()) {
                    int separatorIndex = selectedDaysBuilder.lastIndexOf(separator);
                    if (separatorIndex == selectedDaysBuilder.length() - separator.length()) {
                        selectedDaysBuilder.delete(separatorIndex, separatorIndex + separator.length());
                    }
                }
            }
        }
        // Count the number of commas in selectedDaysBuilder
        selectedDaysCount = 1;
        for (int i = 0; i < selectedDaysBuilder.length(); i++) {
            if (selectedDaysBuilder.charAt(i) == ',') {
                selectedDaysCount++;
            }
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, PembangunanActivity.class);
        startActivity(intent);

        finish();
    }
}