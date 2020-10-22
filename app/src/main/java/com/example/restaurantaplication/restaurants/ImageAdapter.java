package com.example.restaurantaplication.restaurants;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.restaurantaplication.Model.Item;
import com.example.restaurantaplication.R;

import java.util.ArrayList;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {

    private Item items;
    private Context context;

    public ImageAdapter(Item items, Context context) {
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image_restaurant,parent,false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {

        final String image = items.getIcon(position);

        Glide.with(context).load(image).into(holder.imageItem);
    }

    @Override
    public int getItemCount() {
        return items.getIconsSize();
    }

    class ImageViewHolder extends RecyclerView.ViewHolder {

        private AppCompatImageView imageItem;



        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);

            imageItem=itemView.findViewById(R.id.restaurant_details_image);

        }


    }
}
