package com.example.restaurantaplication.server;

import com.example.restaurantaplication.models.Restaurant;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RestaurantService {
    @GET("list")
    Call<List<Restaurant>> getRestaurants();
}
