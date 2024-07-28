package com.example.craveyard.recipe.search.repo

import RemoteDataSource
import android.util.Log
import com.example.craveyard.recipe.model.Meal
import com.example.craveyard.recipe.model.Meals
import com.example.craveyard.recipe.model.Recipe

class SearchRepositoryImplementation(private val remoteDataSource: RemoteDataSource) : SearchRepository {
    override suspend fun search(query: String): Meals {
        Log.d("asd","4")
        return remoteDataSource.search(query)

    }
}