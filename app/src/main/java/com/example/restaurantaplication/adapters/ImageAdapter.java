package com.example.restaurantaplication.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.restaurantaplication.models.Photo;
import com.example.restaurantaplication.R;

import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {

    private List<Photo> photos;
    private Context context;

    public ImageAdapter(List<Photo> photos, Context context) {
        this.photos = photos;
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
        Photo photo = photos.get(position);
        Glide.with(context).load(photo.getImagePath()).into(holder.imageItem);
    }

    @Override
    public int getItemCount() {
        return photos.size();
    }

    class ImageViewHolder extends RecyclerView.ViewHolder {
        private AppCompatImageView imageItem;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            imageItem=itemView.findViewById(R.id.restaurant_details_image);
        }
    }
}
