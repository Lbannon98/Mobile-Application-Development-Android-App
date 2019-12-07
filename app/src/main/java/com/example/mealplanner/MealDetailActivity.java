package com.example.mealplanner;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.squareup.picasso.Picasso;

public class MealDetailActivity extends AppCompatActivity {

    private Toolbar toolbar;
//    private TextView link_description;
    private TextView meal_link;
    private TextView meal_name;

//    String[] search_result_urls = new String[] {
//            "http://www.donalskehan.com/recipes/tomato-vegetable-braised-chicken/",
//            "http://www.donalskehan.com/recipes/rosemary-lamb-steaks-with-quick-bean-stew/",
//            "http://www.donalskehan.com/recipes/general-tsos-tofu-stir-fry/",
//            "http://www.donalskehan.com/recipes/indonesian-chicken-curry/",
//            "http://www.donalskehan.com/recipes/crockpot-sun-dried-tomato-penne-alla-vodka/",
//            "http://www.donalskehan.com/recipes/pepperoni-pizza-pasta/",
//            "http://www.donalskehan.com/recipes/fried-chicken-sandwich/",
//            "http://www.donalskehan.com/recipes/cocktail-meatballs/",
//            "http://www.donalskehan.com/recipes/brown-butter-confit-tomato-pasta/",
//            "http://www.donalskehan.com/recipes/breakfast-burritos/",
//            "http://www.donalskehan.com/recipes/spaghetti-bolognese/",
//            "http://www.donalskehan.com/recipes/vietnamese-turmeric-dill-fish/"
//    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_detail);

        toolbar = (Toolbar) findViewById(R.id.meal_details_toolbar);
        setSupportActionBar(toolbar);

//        link_description  = (TextView) findViewById(R.id.meal_link);
        meal_link  = (TextView) findViewById(R.id.meal_link);
        meal_name  = (TextView) findViewById(R.id.meal_name_detail);

        setUp();

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

    public void setUp() {

        Intent intent = getIntent();

        String meal = intent.getStringExtra("meal");
        String link = intent.getStringExtra("link");
//        String image = intent.getStringExtra("image");

        ImageView imageView = (ImageView) findViewById(R.id.meal_image);
        String imageUrl = String.valueOf(intent.getStringExtra("image"));
//
        Log.d("LOG_TAG", imageUrl);
        Picasso.get().load(imageUrl).into(imageView);


//        ImageView imageView = (ImageView) findViewById(R.id.meal_image);
//        String imageUrl = String.valueOf(intent.getStringExtra("image"));
//
//        Log.d("LOG_TAG", imageUrl);
//        Picasso.get().load(imageUrl).into(imageView);

        meal_link.setText(link);
        meal_name.setText(meal);

        Toast.makeText(MealDetailActivity.this, "" + meal, Toast.LENGTH_LONG).show();


//        ImageView imageView = (ImageView) findViewById(R.id.imageView);
//        strImage= String.valueOf(intent.getStringExtra("Image"));
//        Log.d("LOG_TAG" strImage);
//        Picasso.with(this)
//                .load(strImage)
//                .into(imageView);
//
//        String meal = getIntent().getStringExtra("meal");
//        int image = getIntent().getIntExtra("image");

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