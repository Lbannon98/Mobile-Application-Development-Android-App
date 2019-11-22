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

    ArrayList<FavouritesItem> favouritesItems;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView image;
        TextView meal;

        public ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.fav_meal_image);
            meal = itemView.findViewById(R.id.fav_meal_name);
        }
    }

    public FavouritesAdapter(ArrayList<FavouritesItem> favouritesItems) {
        this.favouritesItems = favouritesItems;
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

        FavouritesItem currentItem = favouritesItems.get(position);

        holder.image.setImageResource(currentItem.getImage());
        holder.meal.setText(currentItem.getName());

    }

    @Override
    public int getItemCount() {
        return favouritesItems.size();
    }
}
