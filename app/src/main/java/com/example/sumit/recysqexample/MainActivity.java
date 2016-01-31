package com.example.sumit.recysqexample;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    HttpURLConnection connection;
    URL url;
    BufferedReader reader;
    List<MovieApi> arrayList;
    DatabaseHandler db;
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.reyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        //recyclerView.set
        new AsyncTaskExample().execute("http://api.androidhive.info/json/movies.json");

    }

    public class AsyncTaskExample extends AsyncTask<String, Void, Integer> {

        @Override
        protected Integer doInBackground(String... params) {
            reader = null;
            connection = null;
            try {
                url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                Integer integer = connection.getResponseCode();
                if (integer == 200) {
                    reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String json = "";
                    StringBuilder builder = new StringBuilder();
                    while ((json = reader.readLine()) != null) {
                        builder.append(json);
                    }
                    String json1 = builder.toString();
                    jsonParse(json1);
                }
                return integer;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Integer movieApis) {
            super.onPostExecute(movieApis);
            db = new DatabaseHandler(MainActivity.this);
            if (movieApis != null) {
                MovieAdapter movieAdapter = new MovieAdapter(MainActivity.this, arrayList);
                recyclerView.setAdapter(movieAdapter);
            } else {
                //List<MovieApi> arraList = db.getAllData();
                List<MovieApi> list;
                SharedPreferences preferences = getSharedPreferences("ListMovie", MODE_PRIVATE);
                String json = preferences.getString("arrayList", null);
                Gson gson = new Gson();
                MovieApi[] movieApis1 = gson.fromJson(json, MovieApi[].class);
                list = Arrays.asList(movieApis1);
                list = new ArrayList<>(list);
                //List<MovieApi> movieApis1 = null;
                //List<MovieApi> movieApis1=new ArrayList<>(set);
                SqlAdapter adapter = new SqlAdapter(MainActivity.this, list);
                recyclerView.setAdapter(adapter);
            }
        }
    }

    public static byte[] getBitmapAsByteArray(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, outputStream);
        return outputStream.toByteArray();
    }

    public void jsonParse(String json) {
        arrayList = new ArrayList<>();
        Gson gson = new Gson();
        try {
            db = new DatabaseHandler(MainActivity.this);

            JSONArray array = new JSONArray(json);
            for (int i = 0; i < array.length(); i++) {
                JSONObject response = array.getJSONObject(i);
                MovieApi movieApi = new MovieApi();
                String title = response.getString("title");
                movieApi.setTitle(title);
                String year = response.getString("releaseYear");
                movieApi.setReleaseYear(Integer.parseInt(year));
                movieApi.setImage(response.getString("image"));

                //byte[] data=getBitmapAsByteArray()
                String rating = response.getString("rating");
                movieApi.setRating(Float.parseFloat(rating));
                arrayList.add(movieApi);
                db.insertData(title, year, rating);

            }
            SharedPreferences preferences = getSharedPreferences("ListMovie", MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            //Set<String> set = new HashSet<>();
            String json1 = gson.toJson(arrayList);

            //set.add(String.valueOf(arrayList));
            editor.putString("arrayList", json1);
            editor.commit();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
