<<<<<<<< Updated upstream:app/src/main/java/com/example/craveyard/utilities/network/APIClient.kt
package com.example.craveyard.utilities.network
========
package com.example.craveyard.data.network
>>>>>>>> Stashed changes:app/src/main/java/com/example/craveyard/data/network/APIClient.kt

import com.example.craveyard.data.model.Meals
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