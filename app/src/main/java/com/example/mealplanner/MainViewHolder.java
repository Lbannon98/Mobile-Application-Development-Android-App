package com.example.mealplanner;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class MainViewHolder extends RecyclerView.ViewHolder {

    public ImageView image;
    public TextView meal;

    public MainViewHolder(View itemView) {

        super(itemView);

        meal = itemView.findViewById(R.id.meal_name);
        image = itemView.findViewById(R.id.card_view_images);

    }
}
