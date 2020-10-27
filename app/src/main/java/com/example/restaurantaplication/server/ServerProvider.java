package com.example.restaurantaplication.server;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ServerProvider {
    public static final String RESTAURANT_URL = "https://ff80c3b1-3147-41c1-b8d4-f6b0d1500900.mock.pstmn.io/restaurant/";

    public static RestaurantService createRestaurantService(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RESTAURANT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(getOkHttpClient())
                .build();

        return retrofit.create(RestaurantService.class);
    }

    private static OkHttpClient getOkHttpClient() {
        HttpLoggingInterceptor httpLoggingInterceptor = getHttpLoggingInterceptor();

        return new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build();
    }

    private static HttpLoggingInterceptor getHttpLoggingInterceptor(){
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
        return httpLoggingInterceptor;
    }


    private ServerProvider(){
    }

}