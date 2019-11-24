package com.example.mealplanner;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MealDetailActivity extends AppCompatActivity {

    Toolbar toolbar;
    TextView link_description;
    TextView meal_link;

    String[] search_result_urls = new String[] {
            "http://www.donalskehan.com/recipes/tomato-vegetable-braised-chicken/",
            "http://www.donalskehan.com/recipes/rosemary-lamb-steaks-with-quick-bean-stew/",
            "http://www.donalskehan.com/recipes/general-tsos-tofu-stir-fry/",
            "http://www.donalskehan.com/recipes/indonesian-chicken-curry/",
            "http://www.donalskehan.com/recipes/crockpot-sun-dried-tomato-penne-alla-vodka/",
            "http://www.donalskehan.com/recipes/pepperoni-pizza-pasta/",
            "http://www.donalskehan.com/recipes/fried-chicken-sandwich/",
            "http://www.donalskehan.com/recipes/cocktail-meatballs/",
            "http://www.donalskehan.com/recipes/brown-butter-confit-tomato-pasta/",
            "http://www.donalskehan.com/recipes/breakfast-burritos/",
            "http://www.donalskehan.com/recipes/spaghetti-bolognese/",
            "http://www.donalskehan.com/recipes/vietnamese-turmeric-dill-fish/"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_detail);

        toolbar = (Toolbar) findViewById(R.id.meal_details_toolbar);
        setSupportActionBar(toolbar);

        link_description  = (TextView) findViewById(R.id.meal_link);
        meal_link  = (TextView) findViewById(R.id.meal_link);

        BottomNavigationView bottomNavigation = findViewById(R.id.my_navigation_items);

        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch(menuItem.getItemId()) {

                    case R.id.nav_home:

                        Intent homeIntent = new Intent(MealDetailActivity.this, MainActivity.class);
                        homeIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(homeIntent);

                        break;

                    case R.id.nav_add:

                        Intent addIntent = new Intent(MealDetailActivity.this, AddActivity.class);
                        addIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(addIntent);

                        break;

                    case R.id.nav_favourites:

                        Intent favouritesIntent = new Intent(MealDetailActivity.this, FavouritesActivity.class);
                        favouritesIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(favouritesIntent);

                        break;

                }

                return false;
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.meal_details_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if((item.getItemId() == R.id.add_to_favourites)) {

            //Include code that will add to database

            Toast.makeText(getApplicationContext(), "Added to Database", Toast.LENGTH_LONG).show();

        }
        return super.onOptionsItemSelected(item);
    }
}