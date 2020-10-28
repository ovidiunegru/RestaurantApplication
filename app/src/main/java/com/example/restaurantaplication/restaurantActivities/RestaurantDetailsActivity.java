package com.example.restaurantaplication.restaurantActivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;

import com.example.restaurantaplication.adapters.ImageAdapter;
import com.example.restaurantaplication.models.Photo;
import com.example.restaurantaplication.models.Restaurant;
import com.example.restaurantaplication.R;
import com.example.restaurantaplication.utils.SharedPrefUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import static com.example.restaurantaplication.restaurantActivities.RestaurantsActivity.RESTAURANT_BUNDLE;

public class RestaurantDetailsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private Restaurant restaurant;
    private List<Photo> photos;
    private AppCompatTextView title;
    private AppCompatTextView description;
    private MapView mapView;
    private Toolbar toolbar;
    private SharedPreferences sharedPreferences;
    private SharedPrefUtil sharedPrefUtil;
    private boolean isFav=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_details);

        sharedPreferences = getApplicationContext().getSharedPreferences("PREFERENCES",MODE_PRIVATE);


        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            restaurant=(Restaurant) bundle.getSerializable(RESTAURANT_BUNDLE);
        }

        sharedPrefUtil = new SharedPrefUtil(sharedPreferences,restaurant);
        isFav=sharedPrefUtil.getPrefRestaurant();
        initViews();
        initToolbar();
        mapView.onCreate(savedInstanceState);
        mapView.onResume();
        mapView.getMapAsync(this);
    }

    private void initViews(){
        title=findViewById(R.id.restaurant_details_title);
        description=findViewById(R.id.restaurant_details_description);
        description.setMovementMethod(new ScrollingMovementMethod());
        mapView=findViewById(R.id.mapView);
        toolbar=findViewById(R.id.toolbar_restaurant_details);

        toolbar.setTitle(restaurant.getName());
        title.setText(restaurant.getName());
        description.setText(restaurant.getDescription());
        photos=restaurant.getPhotos();

        RecyclerView recyclerView = findViewById(R.id.recycler_view_image);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false));
        recyclerView.setHasFixedSize(true);
        ImageAdapter imageAdapter = new ImageAdapter(photos,getBaseContext());
        recyclerView.setAdapter(imageAdapter);
    }

    private void initToolbar(){
        getSupportActionBar();
        toolbar.setTitle(restaurant.getName());
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        if(isFav){
            menu.findItem(R.id.action_favourite).setIcon(R.drawable.ic_action_fav);
        }
        if(!isFav)
            menu.findItem(R.id.action_favourite).setIcon(R.drawable.ic_action_not_fav);
        return  true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == R.id.action_favourite){
            if(isFav){
                item.setIcon(R.drawable.ic_action_not_fav);
                sharedPrefUtil.putNotPrefRestaurant();
                isFav=false;
            }else{
                item.setIcon(R.drawable.ic_action_fav);
                sharedPrefUtil.putPrefRestaurant();
                isFav=true;
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng coordinates = new LatLng(restaurant.getLatitude(),restaurant.getLongitude());
        googleMap.addMarker(new MarkerOptions().position(coordinates));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(coordinates,15));
    }
}