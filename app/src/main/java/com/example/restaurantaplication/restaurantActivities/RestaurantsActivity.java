package com.example.restaurantaplication.restaurantActivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.example.restaurantaplication.models.Photo;
import com.example.restaurantaplication.models.Restaurant;
import com.example.restaurantaplication.R;
import com.example.restaurantaplication.adapters.RestaurantAdapter;
import com.example.restaurantaplication.interfaces.RecyclerViewClickInterface;
import com.example.restaurantaplication.server.ServerProvider;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RestaurantsActivity extends AppCompatActivity implements RecyclerViewClickInterface {

    static final String RESTAURANT_BUNDLE="restaurant";
    private List<Restaurant> restaurants = new ArrayList<>();
    private List<Restaurant> restaurantArrayList = new ArrayList<>();
    private RestaurantAdapter restaurantAdapter;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view);
        initViews();
        recyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL, false));
        recyclerView.setHasFixedSize(true);
        restaurantAdapter=new RestaurantAdapter(restaurantArrayList,getBaseContext(),this);
        getRestaurants();
        recyclerView.setAdapter(restaurantAdapter);
    }

    private void initViews(){
        progressBar = findViewById(R.id.progressBar);
        recyclerView = findViewById(R.id.recycler_view);
    }

    private void getRestaurants(){
        ServerProvider.createRestaurantService().getRestaurants().enqueue(new Callback<List<Restaurant>>() {
            @Override
            public void onResponse(Call<List<Restaurant>> call, Response<List<Restaurant>> response) {
                if(response.isSuccessful()) {
                    restaurants = response.body();
                    for (Restaurant res :
                            restaurants) {
                        Restaurant newRestaurant = new Restaurant();
                        newRestaurant.setName(res.getName());
                        newRestaurant.setDescription(res.getDescription());
                        newRestaurant.setImagePath(res.getImagePath());
                        newRestaurant.setLatitude(res.getLatitude());
                        newRestaurant.setLongitude(res.getLongitude());
                        List<Photo> photos = new ArrayList<>();
                        for (Photo photo :
                                res.getPhotos()) {
                            photos.add(photo);
                        }
                        newRestaurant.setPhotos(photos);
                        restaurantArrayList.add(newRestaurant);
                    }
                    recyclerView.setAdapter(restaurantAdapter);
                    restaurantAdapter.notifyDataSetChanged();
                    progressBar.setVisibility(View.GONE);
                }

            }

            @Override
            public void onFailure(Call<List<Restaurant>> call, Throwable t) {
                Log.e("TAG", "WRONG response: ");
            }
        });

    }


    @Override
    public void onItemClick(int position) {
        Restaurant restaurant = restaurantArrayList.get(position);
        Intent intent= new Intent(getApplicationContext(), RestaurantDetailsActivity.class);
        intent.putExtra(RESTAURANT_BUNDLE, restaurant);
        startActivity(intent);
    }
}