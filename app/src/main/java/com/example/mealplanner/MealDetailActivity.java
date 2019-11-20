package com.example.mealplanner;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MealDetailActivity extends AppCompatActivity {

    Toolbar toolbar;
    TextView link_description;
    TextView meal_link;

    String[] search_result_urls = new String[] {
            "http://www.donalskehan.com/recipes/spaghetti-bolognese/", "http://www.donalskehan.com/recipes/fried-chicken-sandwich/",
            "http://www.donalskehan.com/recipes/breakfast-burritos/", "http://www.donalskehan.com/recipes/indonesian-chicken-curry/",
            "http://www.donalskehan.com/recipes/brown-butter-confit-tomato-pasta/", "http://www.donalskehan.com/recipes/crockpot-sun-dried-tomato-penne-alla-vodka/",
            "http://www.donalskehan.com/recipes/rosemary-lamb-steaks-with-quick-bean-stew/", "http://www.donalskehan.com/recipes/cocktail-meatballs/"
    };

//    ImageView meal_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_detail);

        toolbar = (Toolbar) findViewById(R.id.meal_details_toolbar);
        setSupportActionBar(toolbar);
//        toolbar.setTitle(R.string.add_to_favourites);

        link_description  = (TextView) findViewById(R.id.meal_link);
        meal_link  = (TextView) findViewById(R.id.meal_link);


//        meal_image = (ImageView) findViewById(R.id.meal_image);

//        Intent i = getIntent();
//        String title = i.getStringExtra("search_result_title");
//        Image image = i.getIntExtra("search_result_images");

//        Bundle bundle = getIntent().getExtras();
////        if(bundle != null) {
////            meal_link.setText(bundle.getString("search_result_title"));
//////            toolbar.setTitle(bundle.getString("search_result_title"));
//            meal_image.setImageResource(bundle.getInt("search_result_images"));
////        }


        BottomNavigationView bottomNavigation = findViewById(R.id.bottom_navigation);

        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch(menuItem.getItemId()) {
                    case R.id.nav_home:

                        Intent homeIntent = new Intent(MealDetailActivity.this, MainActivity.class);
                        homeIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(homeIntent);

                        break;

                    case R.id.nav_search:

                        Intent searchIntent = new Intent(MealDetailActivity.this, AddActivity.class);
                        searchIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(searchIntent);

                        break;

                    case R.id.nav_meal_plan:

                        Intent mealPlanIntent = new Intent(MealDetailActivity.this, MealPlanActivity.class);
                        mealPlanIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(mealPlanIntent);

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
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//
//        if((item.getItemId() == R.id.add_to_favourites)) {
//
//            //Include code that will add to database
//
//            Toast.makeText(getApplicationContext(), "Added to Database", Toast.LENGTH_LONG).show();
//
//        }
//        return super.onOptionsItemSelected(item);
//    }
}