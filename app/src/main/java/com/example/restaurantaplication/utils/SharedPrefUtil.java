package com.example.restaurantaplication.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.restaurantaplication.models.Restaurant;

public final class SharedPrefUtil {
    private final SharedPreferences sharedPreferences;
    private final Restaurant restaurant;
    private String restaurantCode="";

    public SharedPrefUtil(SharedPreferences sharedPreferences, Restaurant restaurant) {
        this.sharedPreferences = sharedPreferences;
        this.restaurant = restaurant;
    }

    public boolean getPrefRestaurant(){
        restaurantCode=restaurant.getLatitude().toString()+restaurant.getLongitude().toString();
        return sharedPreferences.getBoolean(restaurantCode,false);
    }

    public void putPrefRestaurant(){
        restaurantCode=restaurant.getLatitude().toString()+restaurant.getLongitude().toString();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(restaurantCode, true);
        Log.d("TAG","Preference is saved as true" );
        editor.apply();
    }

    public void putNotPrefRestaurant(){
        restaurantCode=restaurant.getLatitude().toString()+restaurant.getLongitude().toString();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(restaurantCode, false);
        Log.d("TAG","Preference is saved as false" );
        editor.apply();
    }

}
