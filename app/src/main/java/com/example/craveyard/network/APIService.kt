package com.example.craveyard.network

import com.example.craveyard.recipe.model.Meals
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {

    @GET("api/json/v1/1/search.php")
    suspend fun search(
        @Query("s") query: String
    ): Meals

}