package com.example.mealplanner;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ItemViewHolder extends RecyclerView.ViewHolder {

    private ArrayList<Item> items;
    private MainAdapter.OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(MainAdapter.OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    public ImageView image;
    TextView meal;

    public ItemViewHolder(View itemView, final MainAdapter.OnItemClickListener listener) {
        super(itemView);
        image = itemView.findViewById(R.id.card_view_images);
        meal = itemView.findViewById(R.id.meal_name);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener != null) {
                    int position = getAdapterPosition();

                    if(position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(position);
                    }
                }
            }
        });
    }
}
