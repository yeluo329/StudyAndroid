package com.example.wanandroid;

import android.icu.text.IDNA;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Api {
    //GET 请求
    @GET("api/rand.music")
    Call<Data<MusicInfo>> getJsonData(@Query("sort") String sort, @Query("format") String format);


    //post 请求
    @FormUrlEncoded
    @POST("api/comments.163")
    Call<Object> postDataCall(@Field("format") String format);
}
