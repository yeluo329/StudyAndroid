package com.example.wanandroid;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface BirthdayAnimalService {

    @GET("fapig/zodiac/query")
    Call<Info> getInformation(@Query("keyword") String keyword, @Query("key") String key);


    @GET
    Call<Info> getInformation1(@Url String nameUrl, @Query("keyword") String keyword, @Query("key") String key);


    @FormUrlEncoded
    @POST("fapig/zodiac/query")
    Call<Info> getData(@Field("keyword") String keyword, @Field("key") String key);
}
