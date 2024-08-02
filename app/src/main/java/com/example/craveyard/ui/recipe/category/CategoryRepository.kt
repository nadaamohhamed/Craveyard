package com.example.craveyard.ui.recipe.category

import com.example.craveyard.data.model.Meals

interface CategoryRepository {

    suspend fun getMealsByCategory(query: String): Meals
}