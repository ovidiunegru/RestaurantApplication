package com.example.restaurantaplication.restaurantActivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;

import com.example.restaurantaplication.adapters.ImageAdapter;
import com.example.restaurantaplication.models.Photo;
import com.example.restaurantaplication.models.Restaurant;
import com.example.restaurantaplication.R;

import java.util.List;

import static com.example.restaurantaplication.restaurantActivities.RestaurantsActivity.RESTAURANT_BUNDLE;

public class RestaurantDetailsActivity extends AppCompatActivity {

    private Restaurant restaurant;
    private List<Photo> photos;
    private AppCompatTextView title;
    private AppCompatTextView description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_details);

        RecyclerView recyclerView = findViewById(R.id.recycler_view_image);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false));
        recyclerView.setHasFixedSize(true);

        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            restaurant=(Restaurant) bundle.getSerializable(RESTAURANT_BUNDLE);
        }

        title=findViewById(R.id.restaurant_details_title);
        description=findViewById(R.id.restaurant_details_description);
        description.setMovementMethod(new ScrollingMovementMethod());

        title.setText(restaurant.getName());
        description.setText(restaurant.getDescription());
        photos=restaurant.getPhotos();

        ImageAdapter imageAdapter = new ImageAdapter(photos,getBaseContext());
        recyclerView.setAdapter(imageAdapter);
    }

}