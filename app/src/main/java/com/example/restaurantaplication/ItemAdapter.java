package com.example.restaurantaplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.restaurantaplication.Model.Item;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

    private ArrayList<Item> items;
    private Context context;
    private RecyclerViewClickInterface recyclerViewClickInterface;

    public ItemAdapter(ArrayList<Item> items, Context context, RecyclerViewClickInterface recyclerViewClickInterface) {
        this.items = items;
        this.context = context;
        this.recyclerViewClickInterface=recyclerViewClickInterface;
        //notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_restaurant, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        final Item item = items.get(position);

        //holder.image.setImageDrawable(ContextCompat.getDrawable(context,item.getIcon()));
        Glide.with(context).load(item.getIcon(0)).into(holder.image);
        holder.title.setText(item.getTitle());
        holder.subTitle.setText(item.getSubtitle());


    }

    @Override
    public int getItemCount() {
        return items.size();
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
