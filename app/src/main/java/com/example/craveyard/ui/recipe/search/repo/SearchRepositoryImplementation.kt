package com.example.craveyard.ui.recipe.search.repo

import com.example.craveyard.data.model.Meals
import com.example.craveyard.data.network.RemoteDataSource

class SearchRepositoryImplementation(private val remoteDataSource: RemoteDataSource) :
    SearchRepository {
    override suspend fun search(query: String): Meals {
        return remoteDataSource.search(query)
    }
}