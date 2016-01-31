package com.example.sumit.recysqexample;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Main2Activity extends Activity {
    RecyclerView recyclerView;
    List<MovieApi> arrayList = new ArrayList<>();
    private static final String URL = "http://api.androidhive.info/json/movies.json";
    List<MovieModel> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        recyclerView = (RecyclerView) findViewById(R.id.reyclerView1);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        getData();
        //final MovieModelAdapter adapter = new MovieModelAdapter(this, arrayList);
        //recyclerView.setAdapter(adapter);
        //getData();
        /*JsonArrayRequest arrayRequest = new JsonArrayRequest(URL, new com.android.volley.Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        MovieApi movieApi = new MovieApi();
                        JSONObject object = response.getJSONObject(i);
                        movieApi.setTitle(object.getString("title"));
                        movieApi.setRating(Float.parseFloat(object.getString("rating")));
                        movieApi.setReleaseYear(Integer.parseInt(object.getString("releaseYear")));
                        movieApi.setImage(object.getString("image"));
                        arrayList.add(movieApi);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                adapter.notifyDataSetChanged();
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        VolleySingleton.getmInstance().addToRequestQueue(arrayRequest);*/

    }

    private void getData() {
        //final Gson gson = new GsonBuilder()

               // .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.androidhive.info/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MovieService service = retrofit.create(MovieService.class);
        Call<List<MovieModel>> modelCall = service.getDealModel();
        modelCall.enqueue(new Callback<List<MovieModel>>() {
            @Override
            public void onResponse(Response<List<MovieModel>> response) {
                if (response.isSuccess()) {

                    list = response.body();
                    MovieModelAdapter adapter = new MovieModelAdapter(Main2Activity.this, list);
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }
}
