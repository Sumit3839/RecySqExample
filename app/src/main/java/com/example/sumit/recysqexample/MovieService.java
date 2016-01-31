package com.example.sumit.recysqexample;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Sumit on 29-01-2016.
 */
public interface MovieService {
    @GET("/json/movies.json")
    Call<List<MovieModel>> getDealModel();

}
