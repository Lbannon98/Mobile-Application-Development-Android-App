package com.example.mealplanner;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class FavouritesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favourites_activity);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        BottomNavigationView bottomNavigation = findViewById(R.id.bottom_navigation);

        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch(menuItem.getItemId()) {
                    case R.id.nav_search:

                        Intent searchIntent = new Intent(FavouritesActivity.this, SearchActivity.class);
                        searchIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(searchIntent);

                        break;

                    case R.id.nav_meal_plan:

                        Intent mealPlanIntent = new Intent(FavouritesActivity.this, MealPlanActivity.class);
                        mealPlanIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(mealPlanIntent);

                        break;

                }

                return false;
            }
        });


    }
}

