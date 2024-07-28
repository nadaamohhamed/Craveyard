package com.example.craveyard.network

import com.example.craveyard.recipe.model.Recipe
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {


    @GET("search.php")
    fun search(
        @Query("s") query: String
    ): List<Recipe>

}