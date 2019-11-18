package com.example.mealplanner;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("filter.php")
    Call<MealData> getMeal(
            @Query("i") String ingredient
    );

}
