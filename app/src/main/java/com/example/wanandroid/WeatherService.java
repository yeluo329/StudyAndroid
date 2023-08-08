package com.example.wanandroid;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherService {
    //RetrofitGet注解
    @GET("simpleWeather/query")
    Call<Weather> getInformation(@Query("city") String city, @Query("key") String key);
}
