package com.example.craveyard.recipe.search.repo

import com.example.craveyard.recipe.model.Meals
import com.example.craveyard.network.RemoteDataSource

class SearchRepositoryImplementation(private val remoteDataSource: RemoteDataSource) : SearchRepository {
    override suspend fun search(query: String): Meals {
        return remoteDataSource.search(query)
    }
}