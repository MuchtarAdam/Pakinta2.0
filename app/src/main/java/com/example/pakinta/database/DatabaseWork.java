package com.example.pakinta.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseWork extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Database Work";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "data work";
    private static final String COLUMN_CITY = "city";
    private static final String COLUMN_DETAIL_LOCATION = "detail_location";
    private static final String COLUMN_PROJECT_NAME = "project_name";
    private static final String COLUMN_CATEGORY = "category";
    private static final String COLUMN_BUILDING_TYPE = "building_type";

    private static final String COLUMN_START_DATE = "start_date";
    private static final String COLUMN_FINISH_DATE = "finish_date";
    private static final String COLUMN_START_TIME = "start_time";
    private static final String COLUMN_FINISH_TIME = "finish_time";
    private static final String COLUMN_WORK_DAYS = "work_days";
    private static final String COLUMN_AMOUNT_DAYS = "amount_days";

    private static final String COLUMN_TOTAL_DAYS = "total_days";
    private static final String COLUMN_TOTAL_WORKERS = "total_workers";
    private static final String COLUMN_TOTAL_ESTIMATE = "total_estimate";

    public DatabaseWork(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public DatabaseWork(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTableQuery = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_CITY + " TEXT, " +
                COLUMN_DETAIL_LOCATION + " TEXT, " +
                COLUMN_PROJECT_NAME + " TEXT, " +
                COLUMN_CATEGORY + " TEXT, " +
                COLUMN_BUILDING_TYPE + " TEXT, " +
                COLUMN_START_DATE + " TEXT, " +
                COLUMN_FINISH_DATE + " TEXT, " +
                COLUMN_START_TIME + " TEXT, " +
                COLUMN_FINISH_TIME + " TEXT, " +
                COLUMN_WORK_DAYS + " TEXT, " +
                COLUMN_AMOUNT_DAYS + " TEXT, " +
                COLUMN_TOTAL_DAYS + " TEXT, " +
                COLUMN_TOTAL_WORKERS + " TEXT, " +
                COLUMN_TOTAL_ESTIMATE + " TEXT)";

        sqLiteDatabase.execSQL(createTableQuery);
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
