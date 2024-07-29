package com.example.craveyard.recipe.search.repo

import RetrofitHelper
import com.example.craveyard.recipe.model.Meals

object SearchAPIClient : RemoteDataSearch {
    override suspend fun search(query: String): Meals {
        return RetrofitHelper.retrofitService.search(query)
    }

}