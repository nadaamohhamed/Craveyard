package com.example.craveyard.network

import android.util.Log
import com.example.craveyard.recipe.model.Meal
import com.example.craveyard.recipe.model.Meals
import kotlin.random.Random


object APIClient : RemoteDataSource {

    override suspend fun search(query: String): Meals {
        return RetrofitHelper.retrofitService.getMealsWithChar(query)
    }

    override suspend fun getAllMeals(): Meals {
        val mealsRandomChar = (Random.nextInt(26) + 'a'.code).toChar().toString()
//        Log.d("TAG", "getAllMeals: $mealsRandomChar")
        return RetrofitHelper.retrofitService.getMealsWithChar(mealsRandomChar)
    }

    override suspend fun getRandomMeal(): Meals {
        return RetrofitHelper.retrofitService.getRandomMeal()
    }


}