package com.example.craveyard.ui.recipe.search.repo


import com.example.craveyard.data.model.Meals

interface SearchRepository {

    suspend fun search(query: String): Meals

}