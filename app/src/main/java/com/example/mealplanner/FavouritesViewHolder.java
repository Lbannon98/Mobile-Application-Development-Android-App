package com.example.mealplanner;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

//This holds the content needed to display on the Favourites screen.

public class FavouritesViewHolder extends RecyclerView.ViewHolder {

    public ImageView fav_meal_image;
    public TextView fav_meal_name;

    public FavouritesViewHolder(View itemView) {

        super(itemView);

        fav_meal_name = itemView.findViewById(R.id.fav_meal_name);
        fav_meal_image = itemView.findViewById(R.id.fav_meal_image);

    }
}
