package com.example.craveyard.ui.recipe.home.repo

import RemoteDataSource
import com.example.craveyard.data.model.Categories
import com.example.craveyard.data.model.meals.Meals


class HomeRepositoryImpl (private val remoteDataSource: RemoteDataSource) : HomeRepository {
    override suspend fun getAllMeals(): Meals = remoteDataSource.getAllMeals()

    override suspend fun getRandomMeal(): Meals = remoteDataSource.getRandomMeal()

    override suspend fun getCategories() : Categories {
       return remoteDataSource.getCategories()
    }

}