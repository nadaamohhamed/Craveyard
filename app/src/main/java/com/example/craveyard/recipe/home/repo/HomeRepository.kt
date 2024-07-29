package com.example.craveyard.recipe.home.repo


import com.example.craveyard.network.RemoteDataSource
import com.example.craveyard.recipe.model.Meal
import com.example.craveyard.recipe.model.Meals


class HomeRepository (private val remoteDataSource: RemoteDataSource) {
    suspend fun getAllMeals(): Meals = remoteDataSource.getAllMeals()

    suspend fun getRandomMeal(): Meals = remoteDataSource.getRandomMeal()

}