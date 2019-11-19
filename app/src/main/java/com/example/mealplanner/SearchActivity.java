package com.example.mealplanner;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {

    Toolbar toolbar;
//    ArrayAdapter adapter;
//    ListView listView;
//    TextView emptyView;

    public static final String API_KEY = "1";
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private List<Meal> meals = new ArrayList<>();
    private Adapter adapt;
    private String TAG = SearchActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_activity);

        toolbar = (Toolbar) findViewById(R.id.searchToolbar);
        setSupportActionBar(toolbar);

//        listView = (ListView) findViewById(R.id.list);
//        emptyView = (TextView) findViewById(R.id.emptyView);

        setSupportActionBar(toolbar);

//        adapter = new ArrayAdapter(SearchActivity.this,
//                android.R.layout.simple_list_item_1,
//                getResources().getStringArray(R.array.months));

//        listView.setAdapter(adapter);

//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView adapterView, View view, int i, long l) {
//                Toast.makeText(SearchActivity.this, adapterView.getItemAtPosition(i).toString(), Toast.LENGTH_SHORT).show();
//            }
//        });

//        listView.setEmptyView(emptyView);

        BottomNavigationView bottomNavigation = findViewById(R.id.bottom_navigation);

        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch(menuItem.getItemId()) {

                    case R.id.nav_favourites:

                        Intent favouritesIntent = new Intent(SearchActivity.this, FavouritesActivity.class);
                        favouritesIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(favouritesIntent);

                        break;

                    case R.id.nav_meal_plan:

                        Intent mealPlanIntent = new Intent(SearchActivity.this, MealPlanActivity.class);
                        mealPlanIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(mealPlanIntent);

                        break;

                }

                return false;
            }
        });

        recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(SearchActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(false);

        LoadJSON();
    }

    public void LoadJSON() {
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        Call<MealData> call;
        call = apiInterface.getMeal(API_KEY);

        call.enqueue(new Callback<MealData>() {
            @Override
            public void onResponse(Call<MealData> call, Response<MealData> response) {
                if(response.isSuccessful() && response.body().getMeal() != null) {

                    if(!meals.isEmpty()) {
                        meals.clear();
                    }

                    meals = response.body().getMeal();
                    System.out.println(meals);
                    adapt = new Adapter(meals, SearchActivity.this);
                    recyclerView.setAdapter(adapt);
                    adapt.notifyDataSetChanged();

                    System.out.println(response.body());

                    Toast.makeText(SearchActivity.this, "It worked!!", Toast.LENGTH_LONG).show();

                } else {

                    Toast.makeText(SearchActivity.this, "Not working!!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<MealData> call, Throwable t) {
                System.out.println("CAUSE:" +t.getCause());
                System.out.println("ERROR:" +t.getMessage());
            }
        });
    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.search_bar_menu, menu);
//        MenuItem mSearch = menu.findItem(R.id.action_search);
//        SearchView mSearchView = (SearchView) mSearch.getActionView();
//        mSearchView.setQueryHint("Search");
//        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//            @Override
//            public boolean onQueryTextChange(String newText) {
////                adapter.getFilter().filter(newText);
//                return false;
//            }
//        });
//        return super.onCreateOptionsMenu(menu);
//    }

}