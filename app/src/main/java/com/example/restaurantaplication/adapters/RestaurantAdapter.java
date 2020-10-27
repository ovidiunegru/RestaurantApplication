package com.example.restaurantaplication.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.restaurantaplication.R;
import com.example.restaurantaplication.models.Restaurant;
import com.example.restaurantaplication.interfaces.RecyclerViewClickInterface;

import java.util.List;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.ItemViewHolder> {

    private List<Restaurant> restaurantsList;
    private Context context;
    private RecyclerViewClickInterface recyclerViewClickInterface;

    public RestaurantAdapter(List<Restaurant> restaurantsList, Context context, RecyclerViewClickInterface recyclerViewClickInterface) {
        this.restaurantsList = restaurantsList;
        this.context = context;
        this.recyclerViewClickInterface=recyclerViewClickInterface;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_restaurant, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        final Restaurant restaurant = restaurantsList.get(position);
        Glide.with(context).load(restaurant.getImagePath()).into(holder.image);
        holder.title.setText(restaurant.getName());
        holder.subTitle.setText(restaurant.getDescription());
    }

    @Override
    public int getItemCount() {
        return restaurantsList.size();
    }


     class ItemViewHolder extends RecyclerView.ViewHolder {
        private AppCompatImageView image;
        private AppCompatTextView title;
        private AppCompatTextView subTitle;


        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            image=itemView.findViewById(R.id.list_item_icon);
            title=itemView.findViewById(R.id.list_item_title);
            subTitle=itemView.findViewById(R.id.list_item_subtitle);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    recyclerViewClickInterface.onItemClick(getAdapterPosition());
                }
            });
        }

    }


}
