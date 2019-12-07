package com.example.mealplanner;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
//    private MainAdapter adapter;

    public ArrayList<Item> mainItems;
    private FirebaseRecyclerOptions<Item> options;
    private FirebaseRecyclerAdapter<Item, ItemViewHolder>  adapter;

    public DatabaseReference databaseReference;

    protected void onStart() {
        super.onStart();

        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("id");
        databaseReference.keepSynced(true);

        mainItems = new ArrayList<Item>();

        firebaseConfig();

        buildRecyclerView();

//        mainItems.add(new Item(R.drawable.tomato_vegetable_braised_chicken, "Tomato Vegetable Braised Chicken", null));
//        mainItems.add(new Item(R.drawable.lamb_bean_stew, "Rosemary Lamb Steaks with Quick Bean Stew", null));
//        mainItems.add(new Item(R.drawable.general_tsos_tofu_stir_fry, "General Tso's Tofu Stir Fry", null));
//        mainItems.add(new Item(R.drawable.bali_chicken_curry, "Indonesian Chicken Curry", null));
//        mainItems.add(new Item(R.drawable.crockpot_sun_dried_tomato_penne_alla_vodka, "Crockpot Sun-Dried Tomato Penne Alla Vodka", null));
//        mainItems.add(new Item(R.drawable.pepperoni_pizza_pasta, "Pepperoni Pizza Pasta", null));
//        mainItems.add(new Item(R.drawable.fried_chicken_sandwhich, "Fried Chicken Sandwhich", null));
//        mainItems.add(new Item(R.drawable.cocktails_meatballs, "Cocktail Meatballs", null));
//        mainItems.add(new Item(R.drawable.butter_tomato_pasta, "Brown Butter Confit Tomato Pasta", null));
//        mainItems.add(new Item(R.drawable.breakfast_burrito, "Breakfast Burritos", null));
//        mainItems.add(new Item(R.drawable.spaghetti_bolognese, "Spaghetti Bolognese", null));
//        mainItems.add(new Item(R.drawable.vietnamese_turmeric_dill_fish, "Vietnamese Turmeric Dill Fish", null));


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

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }

    public void firebaseConfig() {

        options = new FirebaseRecyclerOptions.Builder<Item>().setQuery(databaseReference, Item.class).build();

        adapter = new FirebaseRecyclerAdapter<Item, ItemViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull ItemViewHolder holder, int position, @NonNull final Item model) {

                holder.meal.setText(model.getName());

//                    holder.image.setImageResource(Integer.parseInt(model.getImage()));

//                holder.image.setImageResource(model.getImage());
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, MealDetailActivity.class);
                        intent.putExtra("meal", model.getName());
                        intent.putExtra("link", model.getLink());
//                            intent.putExtra("image", model.getImage());
//                        intent.putExtra("image", model.getImage());
                        startActivity(intent);
                    }
                });

            }

            @NonNull
            @Override
            public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return new ItemViewHolder(LayoutInflater.from(MainActivity.this).inflate(R.layout.main_item, parent, false));

            }
        };
    }

}
