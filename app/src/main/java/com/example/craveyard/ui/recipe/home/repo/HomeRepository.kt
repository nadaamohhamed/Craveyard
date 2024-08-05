package com.example.craveyard.ui.recipe.home.repo

import com.example.craveyard.data.model.category.Categories
import com.example.craveyard.data.model.meals.Meals

interface HomeRepository {

    suspend fun getAllMeals() : Meals

    suspend fun getRandomMeal(): Meals

    suspend fun getCategories() : Categories

}