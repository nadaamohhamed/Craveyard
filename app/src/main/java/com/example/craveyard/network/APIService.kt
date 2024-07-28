package com.example.craveyard.network

import com.example.craveyard.recipe.model.Recipe
import retrofit2.http.GET

interface APIService {


    @GET("search.php")
    fun search(): Recipe

}