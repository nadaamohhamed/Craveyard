<<<<<<<< Updated upstream:app/src/main/java/com/example/craveyard/utilities/network/APIService.kt
package com.example.craveyard.utilities.network
========
package com.example.craveyard.data.network
>>>>>>>> Stashed changes:app/src/main/java/com/example/craveyard/data/network/APIService.kt

import com.example.craveyard.data.model.Meals
import retrofit2.http.GET
import retrofit2.http.Query


interface APIService {

    @GET("api/json/v1/1/search.php")
    suspend fun getMealsWithChar(
        @Query("s") query: String
    ): Meals

    @GET("api/json/v1/1/random.php")
    suspend fun getRandomMeal(): Meals


}