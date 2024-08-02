package com.example.craveyard.ui.recipe.home.repo

import RemoteDataSource
import com.example.craveyard.data.model.Meals


class HomeRepository (private val remoteDataSource: RemoteDataSource) {
    suspend fun getAllMeals(): Meals = remoteDataSource.getAllMeals()

    suspend fun getRandomMeal(): Meals = remoteDataSource.getRandomMeal()

    suspend fun getCategories() = remoteDataSource.getCategories()

}