package com.example.mealplanner;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FavouritesAdapter extends RecyclerView.Adapter<FavouritesAdapter.ViewHolder> {

    ArrayList<Item> items;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView image;
        TextView meal;

        public ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.fav_meal_image);
            meal = itemView.findViewById(R.id.fav_meal_name);
        }
    }

    public FavouritesAdapter(ArrayList<Item> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.favourites_item, parent, false);

        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Item currentItem = items.get(position);

//        holder.image.setImageResource(currentItem.getImage());
        holder.meal.setText(currentItem.getName());

//        holder.p

    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
