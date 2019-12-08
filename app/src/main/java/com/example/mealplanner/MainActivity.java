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
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    private FirebaseRecyclerOptions<Item> options;
    private FirebaseRecyclerAdapter<Item, MainViewHolder> adapter;

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

        firebaseConfig();

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
        recyclerView = findViewById(R.id.recycler_view_main);
        recyclerView.setHasFixedSize(false);
        layoutManager = new LinearLayoutManager(MainActivity.this);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }

    public void firebaseConfig() {

        options = new FirebaseRecyclerOptions.Builder<Item>().setQuery(databaseReference, Item.class).build();

        adapter = new FirebaseRecyclerAdapter<Item, MainViewHolder>(options) {

            @Override
            protected void onBindViewHolder(@NonNull MainViewHolder holder, int position, @NonNull final Item model) {

                holder.meal.setText(model.getName());
                Picasso.get().load(model.getImage()).into(holder.image);

//                holder.image.setImageResource(Integer.parseInt(model.getImage()));

//                holder.image.setImageResource(model.getImage());

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, MealDetailActivity.class);
                        intent.putExtra("meal", model.getName());
                        intent.putExtra("link", model.getLink());
//                        intent.putExtra("image", model.getImage());
//                        intent.putExtra("image", model.getImage());
                        startActivity(intent);
                    }
                });

            }

            @NonNull
            @Override
            public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return new MainViewHolder(LayoutInflater.from(MainActivity.this).inflate(R.layout.main_item, parent, false));
            }
        };
    }

}
