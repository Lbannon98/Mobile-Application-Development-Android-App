package com.example.mealplanner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

public class Adapter extends ArrayAdapter<String> {

    String[] meal_names;
    int[] meal_images;
    Context myContext;

    public Adapter(@NonNull Context context, String[] search_result_title, int[] search_result_images) {
        super(context, R.layout.search_result_item);
        this.meal_names = search_result_title;
        this.meal_images = search_result_images;
        this.myContext = context;
    }

    @Override
    public int getCount() {
        return meal_names.length;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder myViewHolder = new ViewHolder();

        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) myContext.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.search_result_item, parent, false);
            myViewHolder.meals = (ImageView) convertView.findViewById(R.id.card_view_images);
            myViewHolder.titles = (TextView) convertView.findViewById(R.id.meal_name);
            convertView.setTag(myViewHolder);

        } else {
            myViewHolder = (ViewHolder) convertView.getTag();
        }

        myViewHolder.meals.setImageResource(meal_images[position]);
        myViewHolder.titles.setText(meal_names[position]);

        return convertView;
    }

    static class ViewHolder {
        ImageView meals;
        TextView titles;
    }

}