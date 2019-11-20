package com.example.mealplanner;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SearchActivity extends AppCompatActivity {

    Toolbar toolbar;
    ListView listView;

    //Titles
    String[] search_result_title = new String[] {
            "Spaghetti Bolognese", "Fried Chicken Sandwich", "Breakfast Burritos", "Indonesian Chicken Curry",
            "Crockpot Sun-Dried Tomato Penne Alla Vodka", "Brown Butter Confit Tomato Pasta", "Rosemary Lamb Steaks with Quick Bean Stew",
            "Cocktail Meatballs"
    };

    //Images
    int[] search_result_images = new int[] {
        R.drawable.spaghetti_bolognese,
            R.drawable.fried_chicken_sandwhich,
            R.drawable.breakfast_burrito,
            R.drawable.bali_chicken_curry,
            R.drawable.crockpot_sun_dried_tomato_penne_alla_vodka,
            R.drawable.butter_tomato_pasta,
            R.drawable.lamb_bean_stew,
            R.drawable.cocktails_meatballs
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_activity);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.toolbar_search);

        listView = (ListView) findViewById(R.id.listview);
        Adapter myAdapter = new Adapter(SearchActivity.this, search_result_title, search_result_images);
        listView.setAdapter(myAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(SearchActivity.this, MealDetailActivity.class);
                intent.putExtra("search_result_title", search_result_title[position]);
                intent.putExtra("search_result_images", search_result_images[position]);
                startActivity(intent);
            }
        });

        setSupportActionBar(toolbar);

        BottomNavigationView bottomNavigation = findViewById(R.id.bottom_navigation);

        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch(menuItem.getItemId()) {
                    case R.id.nav_home:

                        Intent homeIntent = new Intent(SearchActivity.this, MainActivity.class);
                        homeIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(homeIntent);

                        break;


                    case R.id.nav_favourites:

                        Intent favouritesIntent = new Intent(SearchActivity.this, FavouritesActivity.class);
                        favouritesIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(favouritesIntent);

                        break;

                    case R.id.nav_meal_plan:

                        Intent mealPlanIntent = new Intent(SearchActivity.this, MealPlanActivity.class);
                        mealPlanIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(mealPlanIntent);

                        break;

                }

                return false;
            }
        });

    }

}