package com.example.craveyard.recipe.search.repo

import com.example.craveyard.recipe.model.Meal
import com.example.craveyard.recipe.model.Meals
import com.example.craveyard.recipe.model.Recipe

interface SearchRepository {

    suspend fun search(query: String): List<Meal>

}