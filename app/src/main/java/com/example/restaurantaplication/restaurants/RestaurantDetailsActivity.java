package com.example.restaurantaplication.restaurants;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.restaurantaplication.Model.Item;
import com.example.restaurantaplication.R;

import java.util.ArrayList;

import static com.example.restaurantaplication.restaurants.RestaurantsActivity.RESTAURANT_BUNDLE;

public class RestaurantDetailsActivity extends AppCompatActivity {

    private Item item;
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
            item=(Item)bundle.getSerializable(RESTAURANT_BUNDLE);
        }

        title=findViewById(R.id.restaurant_details_title);
        description=findViewById(R.id.restaurant_details_description);

        title.setText(item.getTitle());
        description.setText(item.getSubtitle());

        ImageAdapter imageAdapter = new ImageAdapter(item,getBaseContext());
        recyclerView.setAdapter(imageAdapter);
    }

}