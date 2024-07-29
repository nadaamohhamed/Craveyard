package com.example.craveyard.recipe.search.repo

import com.example.craveyard.recipe.model.Meals

interface RemoteDataSearch {
    suspend fun search(query: String): Meals
}