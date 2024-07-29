package com.example.craveyard.network

import com.example.craveyard.recipe.model.Meals

interface RemoteDataSource {

    suspend fun search(query: String): Meals

    suspend fun getAllMeals(): Meals

    suspend fun getRandomMeal() : Meals

}