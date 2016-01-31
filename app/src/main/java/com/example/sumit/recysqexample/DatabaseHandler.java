package com.example.sumit.recysqexample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sumit on 26-01-2016.
 */
public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "moviefav";
    private static final String TABLE_NAME = "movie";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "moviename";
    private static final String COLUMN_ADULT = "adult";
    private static final String COLUMN_OVERVIEW = "overview";
    private static final String COLUMN_LANGUAGE = "language";
    private static final String COLUMN_DATE = "date";
    private static final String COLUMN_POPULARITY = "popularity";
    private static final String COLUMN_RATING = "rating";
    private static final String COLUMN_IMAGE = "";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_NAME + " TEXT," + COLUMN_DATE + " TEXT,"
                + COLUMN_RATING + " TEXT)";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP IF TABLE EXISTS " + TABLE_NAME);
    }

    public void insertData(String title, String year, String rating) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, title);
        // values.put(COLUMN_ADULT, movieApi.getAdult());
        //values.put(COLUMN_OVERVIEW, movieApi.getOverview());
        //values.put(COLUMN_LANGUAGE, movieApi.getLanguage());
        values.put(COLUMN_DATE, year);
        //values.put(COLUMN_POPULARITY, movieApi.getPopularity());
        values.put(COLUMN_RATING, rating);
        //values.put(COLUMN_IMAGE, movieApi.getImage());
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public void insertImage(byte[] data) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_IMAGE, data);
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public List<Bitmap> getImage() {
        SQLiteDatabase db = this.getReadableDatabase();
        byte[] data;
        Bitmap bitmap;
        List<Bitmap> listbitmap = new ArrayList<>();
        String query = "SELECT " + COLUMN_IMAGE + " FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                data = cursor.getBlob(0);
                bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                listbitmap.add(bitmap);
            } while (cursor.moveToNext());

        }
        return listbitmap;
    }

    public List<MovieApi> getAllData() {
        List<MovieApi> list = new ArrayList<>();
        MovieApi api = new MovieApi();
        Bitmap bitmap;
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                //cursor.getString(0);
                api.setTitle(cursor.getString(1));
                api.setReleaseYear(Integer.parseInt(cursor.getString(2)));
                api.setRating(Float.parseFloat(cursor.getString(3)));
               /* byte[] data = cursor.getBlob(4);
                bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                ByteArrayOutputStream baos=new  ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
                byte [] b=baos.toByteArray();
                String temp= Base64.encodeToString(b, Base64.DEFAULT);
                api.setImage(temp);*/
                list.add(api);

            } while (cursor.moveToNext());
        }
        return list;

    }
}
