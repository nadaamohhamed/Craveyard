package com.example.craveyard.ui.recipe.search.repo

import RemoteDataSource
import com.example.craveyard.data.model.Meals

class SearchRepositoryImplementation(private val remoteDataSource: RemoteDataSource) :
    SearchRepository {
    override suspend fun search(query: String): Meals {
        return remoteDataSource.search(query)
    }
}