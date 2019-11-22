package com.example.mealplanner;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class FavouritesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

//    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favourites_activity);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.toolbar_favourites);


        ArrayList<FavouritesItem> favouriteItems = new ArrayList<>();

        favouriteItems.add(new FavouritesItem(R.drawable.bali_chicken_curry, "Indonesian Chicken Curry"));
        favouriteItems.add(new FavouritesItem(R.drawable.lamb_bean_stew, "Rosemary Lamb Steaks with Quick Bean Stew"));
        favouriteItems.add(new FavouritesItem(R.drawable.crockpot_sun_dried_tomato_penne_alla_vodka, "Crockpot Sun-Dried Tomato Penne Alla Vodka"));

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(false);
        layoutManager = new LinearLayoutManager(FavouritesActivity.this);

        adapter = new FavouritesAdapter(favouriteItems);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

//        layoutManager = new LinearLayoutManager(FavouritesActivity.this);
//
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        recyclerView.setNestedScrollingEnabled(false);
//        recyclerView.setAdapter(adapter);

        BottomNavigationView bottomNavigation = findViewById(R.id.my_navigation_items);

        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch(menuItem.getItemId()) {
                    case R.id.nav_home:

                        Intent homeIntent = new Intent(FavouritesActivity.this, MainActivity.class);
                        homeIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(homeIntent);

                        break;

                    case R.id.nav_add:

                        Intent addIntent = new Intent(FavouritesActivity.this, AddActivity.class);
                        addIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(addIntent);

                        break;

                }

                return false;
            }
        });


    }
}
