package com.example.elina.weatherapp;

import com.example.elina.weatherapp.pojoClasses.Cities;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OpenWeatherMapApi {
    @GET("data/2.5/find?")
    Call<Cities> getData(@Query("latitude") double latitude, @Query("longitude") double longitude,
                         @Query("cnt") int count, @Query("APPID") String KEY);
}
