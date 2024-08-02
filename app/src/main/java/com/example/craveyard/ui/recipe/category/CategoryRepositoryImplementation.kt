package com.example.craveyard.ui.recipe.category

import RemoteDataSource
import com.example.craveyard.data.model.Meals

class CategoryRepositoryImplementation(private val remoteDataSource: RemoteDataSource) :
    CategoryRepository {
    override suspend fun getMealsByCategory(query: String): Meals {
        return remoteDataSource.getMealsByCategory(query)
    }

    override suspend fun searchById(id: String): Meals {
       return remoteDataSource.searchById(id)
    }
}