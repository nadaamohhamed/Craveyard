package com.example.craveyard.ui.recipe.category.repo

import com.example.craveyard.data.model.meals.Meals

interface CategoryRepository {

    suspend fun getMealsByCategory(query: String): Meals

    suspend fun searchById(id:String):Meals
}