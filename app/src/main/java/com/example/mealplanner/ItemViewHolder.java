package com.example.mealplanner;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class ItemViewHolder extends RecyclerView.ViewHolder {

//    private ArrayList<Item> items;
//    private ItemViewHolder.OnItemClickListener onItemClickListener;
//
//    public interface OnItemClickListener {
//        void onItemClick(int position);
//    }

//    public void setOnItemClickListener(ItemViewHolder.OnItemClickListener listener) {
//        this.onItemClickListener = listener;
//    }

    public ImageView image;
    public TextView meal;
    public TextView link;

    public ItemViewHolder(View itemView) {
//        , final ItemViewHolder.OnItemClickListener listener
        super(itemView);
        meal = itemView.findViewById(R.id.meal_name);
//        link = itemView.findViewById(R.id.meal_link);
        image = itemView.findViewById(R.id.card_view_images);

//        itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(listener != null) {
//                    int position = getAdapterPosition();
//
//                    if(position != RecyclerView.NO_POSITION) {
//                        listener.onItemClick(position);
//                    }
//                }
//            }
//        });
    }
}
