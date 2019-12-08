package com.example.mealplanner;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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

public class FavouritesActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private RecyclerView recyclerView;
//    private CardView cardView;
    private RecyclerView.LayoutManager layoutManager;

    private FirebaseRecyclerOptions<Item> options;
    private FirebaseRecyclerAdapter<Item, FavouritesViewHolder> adapter;

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
        setContentView(R.layout.favourites_activity);

        toolbar = (Toolbar) findViewById(R.id.favourites_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(R.string.toolbar_favourites);

//        cardView = (CardView) findViewById(R.id.fav_card_view);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Favourites");
        databaseReference.keepSynced(true);

        firebaseConfig();

        buildRecyclerView();

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

    public void buildRecyclerView() {
        recyclerView = findViewById(R.id.recycler_view_favourites);
        recyclerView.setHasFixedSize(false);
        layoutManager = new LinearLayoutManager(FavouritesActivity.this);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }

    public void firebaseConfig() {

        options = new FirebaseRecyclerOptions.Builder<Item>().setQuery(databaseReference, Item.class).build();

        adapter = new FirebaseRecyclerAdapter<Item, FavouritesViewHolder> (options) {

            @Override
            protected void onBindViewHolder(@NonNull final FavouritesViewHolder holder, int position, @NonNull Item model) {

                holder.fav_meal_name.setText(model.getName());

                holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {

                        Toast.makeText(FavouritesActivity.this, "Long Pressed", Toast.LENGTH_SHORT).show();
                        return false;
                    }
                });

//                holder.itemView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Intent intent = new Intent(FavouritesActivity.this, MealDetailActivity.class);
//                        intent.putExtra("meal", model.getName());
//                        intent.putExtra("link", model.getLink());
////                        intent.putExtra("image", model.getImage());
////                        intent.putExtra("image", model.getImage());
//                        startActivity(intent);
//                    }
//                });

//                Picasso.get().load(model.getImage()).into(holder.fav_meal_image);

////              holder.image.setImageResource(Integer.parseInt(model.getImage()));
//                holder.image.setImageResource(model.getImage());
            }

            @NonNull
            @Override
            public FavouritesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return new FavouritesViewHolder(LayoutInflater.from(FavouritesActivity.this).inflate(R.layout.favourites_item, parent, false));
            }
        };

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.favourites_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if((item.getItemId() == R.id.add_to_favourites)) {
            removeFromFavouites();
        }
        return super.onOptionsItemSelected(item);
    }

    public void removeFromFavouites() {
//        cardView.setOnClickListener((View.OnClickListener) FavouritesActivity.this);

//        cardView.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//
//                return false;
//            }
//        });
    }
}
