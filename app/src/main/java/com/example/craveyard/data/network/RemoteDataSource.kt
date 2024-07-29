<<<<<<<< Updated upstream:app/src/main/java/com/example/craveyard/utilities/network/RemoteDataSource.kt
package com.example.craveyard.utilities.network
========
package com.example.craveyard.data.network
>>>>>>>> Stashed changes:app/src/main/java/com/example/craveyard/data/network/RemoteDataSource.kt

import com.example.craveyard.data.model.Meals

interface RemoteDataSource {

    suspend fun search(query: String): Meals

    suspend fun getAllMeals(): Meals

    suspend fun getRandomMeal() : Meals

}