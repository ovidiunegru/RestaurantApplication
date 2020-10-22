package com.example.restaurantaplication.restaurants;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.restaurantaplication.ItemAdapter;
import com.example.restaurantaplication.Model.Item;
import com.example.restaurantaplication.R;
import com.example.restaurantaplication.RecyclerViewClickInterface;

import java.util.ArrayList;
import java.util.Collections;

public class RestaurantsActivity extends AppCompatActivity implements RecyclerViewClickInterface {

    static final String RESTAURANT_BUNDLE="restaurant";
    ArrayList<Item> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view);


        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL, false));
        recyclerView.setHasFixedSize(true);

        ItemAdapter adapter = new ItemAdapter(getMockItems(), getBaseContext(),this) ;
        recyclerView.setAdapter(adapter);



    }

    private ArrayList<Item> getMockItems() {
        ArrayList<String> picturesAddress1= new ArrayList<>();
        ArrayList<String> picturesAddress= new ArrayList<>();

        picturesAddress.add("https://media-cdn.tripadvisor.com/media/photo-l/19/5f/35/54/salmon-teriyaki.jpg");
        picturesAddress.add("https://media-cdn.tripadvisor.com/media/photo-l/19/70/c6/40/moroccan-breakfast.jpg");
        picturesAddress.add("https://media-cdn.tripadvisor.com/media/photo-l/19/5f/34/b7/tomatoes.jpg");
        picturesAddress.add("https://media-cdn.tripadvisor.com/media/photo-l/19/5f/34/f6/chicken-fajitas.jpg");
        picturesAddress.add("https://media-cdn.tripadvisor.com/media/photo-l/19/5f/35/49/arroz-gambas.jpg");
        items.add(new Item(picturesAddress,"Companero","Compañero is the new Street Tapas brought to you by Nikolaus Greig and utilising his vast experience in food & wine. After many years working in successful restaurants he has taken his cooking skills and knowledge a step further. Compañero is a street food concept that will exhibit fine Spanish Tapas in Londons best locations."));

        picturesAddress1.add("https://media-cdn.tripadvisor.com/media/photo-l/19/eb/ce/ab/brunch-at-chez-antoinette.jpg");
        picturesAddress1.add("https://media-cdn.tripadvisor.com/media/photo-l/17/14/46/32/salmon-tartine.jpg");
        items.add(new Item(picturesAddress1, "Chez Antoinette Victoria","Chez Antoinette is a French Bistro. You can visit us from Breakfast, Brunch and all day long either for just a coffee, lunch, afternoon tea, a glass of wine or a dinner. We serve simple food yet authentic and tasteful French food. If you are just passing by we have a great deli for your lunch take away"));

        return items;
    }



    @Override
    public void onItemClick(int position) {
        Item item = items.get(position);
        Intent intent= new Intent(getApplicationContext(), RestaurantDetailsActivity.class);
        intent.putExtra(RESTAURANT_BUNDLE, item);
        startActivity(intent);
    }

    @Override
    public void onLongItemClick(int position) {

    }
}