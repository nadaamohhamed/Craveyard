package com.example.craveyard.recipe.search.repo

import com.example.craveyard.recipe.model.Meals

class SearchRepositoryImplementation(private val remoteDataSource: RemoteDataSearch) : SearchRepository {
    override suspend fun search(query: String): Meals {
        return remoteDataSource.search(query)
    }
}