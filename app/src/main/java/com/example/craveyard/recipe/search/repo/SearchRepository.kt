package com.example.craveyard.recipe.search.repo


import com.example.craveyard.recipe.model.Meals

interface SearchRepository {

    suspend fun search(query: String): Meals

}