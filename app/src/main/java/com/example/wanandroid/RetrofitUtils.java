package com.example.wanandroid;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtils {
    private static Retrofit retrofit;

    private RetrofitUtils() {

    }

    public static <T> T getService(Class<T> serviceClass) {
        if(retrofit == null){
            synchronized (RetrofitUtils.class){
                if(retrofit == null){
                    retrofit = new Retrofit.Builder()
                            .baseUrl("https://api.uomg.com/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                }
            }
        }
        return retrofit.create(serviceClass);
    }
}
