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

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private MainAdapter adapter;

    public ArrayList<Item> mainItems;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);

        mainItems = new ArrayList<>();

        mainItems.add(new Item(R.drawable.tomato_vegetable_braised_chicken, "Tomato Vegetable Braised Chicken"));
        mainItems.add(new Item(R.drawable.lamb_bean_stew, "Rosemary Lamb Steaks with Quick Bean Stew"));
        mainItems.add(new Item(R.drawable.general_tsos_tofu_stir_fry, "General Tso's Tofu Stir Fry"));
        mainItems.add(new Item(R.drawable.bali_chicken_curry, "Indonesian Chicken Curry"));
        mainItems.add(new Item(R.drawable.crockpot_sun_dried_tomato_penne_alla_vodka, "Crockpot Sun-Dried Tomato Penne Alla Vodka"));
        mainItems.add(new Item(R.drawable.pepperoni_pizza_pasta, "Pepperoni Pizza Pasta"));
        mainItems.add(new Item(R.drawable.fried_chicken_sandwhich, "Fried Chicken Sandwhich"));
        mainItems.add(new Item(R.drawable.cocktails_meatballs, "Cocktail Meatballs"));
        mainItems.add(new Item(R.drawable.butter_tomato_pasta, "Brown Butter Confit Tomato Pasta"));
        mainItems.add(new Item(R.drawable.breakfast_burrito, "Breakfast Burritos"));
        mainItems.add(new Item(R.drawable.spaghetti_bolognese, "Spaghetti Bolognese"));
        mainItems.add(new Item(R.drawable.vietnamese_turmeric_dill_fish, "Vietnamese Turmeric Dill Fish"));

        buildRecyclerView();

        BottomNavigationView bottomNavigation = findViewById(R.id.my_navigation_items);

        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch(menuItem.getItemId()) {

                    case R.id.nav_add:

                        Intent addIntent = new Intent(MainActivity.this, AddActivity.class);
                        addIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(addIntent);

                        break;

                    case R.id.nav_favourites:

                        Intent favouritesIntent = new Intent(MainActivity.this, FavouritesActivity.class);
                        favouritesIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(favouritesIntent);

                        break;

                }

                return false;
            }
        });

    }

    public void buildRecyclerView() {
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(false);
        layoutManager = new LinearLayoutManager(MainActivity.this);

        adapter = new MainAdapter(mainItems);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new MainAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                mainItems.get(position);

                Intent mealDetailsIntent = new Intent(MainActivity.this, MealDetailActivity.class);
                mealDetailsIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(mealDetailsIntent);
            }
        });
    }
}
