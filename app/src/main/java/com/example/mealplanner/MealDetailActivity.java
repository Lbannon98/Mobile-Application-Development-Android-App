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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class MealDetailActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView meal_link;
    private TextView meal_name;
    private ImageView meal_image;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_detail);

        databaseReference = FirebaseDatabase.getInstance().getReference("Favourites");

        toolbar = (Toolbar) findViewById(R.id.meal_details_toolbar);
        setSupportActionBar(toolbar);

        meal_link  = (TextView) findViewById(R.id.meal_link);
        meal_name  = (TextView) findViewById(R.id.meal_name_detail);
        meal_image = (ImageView) findViewById(R.id.meal_image);

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
//        meal_image.setImageResource(imageView);

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
            addToFavouites();
        }
        return super.onOptionsItemSelected(item);
    }

    public void addToFavouites () {
        final String meal = meal_name.getText().toString().trim();

        databaseReference.orderByChild("name").equalTo(meal)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists()) {
                            Toast.makeText(MealDetailActivity.this, "Already in your favourites", Toast.LENGTH_LONG).show();
                        } else {

                            String id = databaseReference.push().getKey();
                            Item item = new Item(null, meal, null);
                            databaseReference.child(id).setValue(item);

                            Toast.makeText(MealDetailActivity.this, "Added to your favourites", Toast.LENGTH_LONG).show();

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(MealDetailActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

    }

}