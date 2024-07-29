package com.example.craveyard.ui.recipe.home.repo


<<<<<<< Updated upstream:app/src/main/java/com/example/craveyard/recipe/home/repo/HomeRepository.kt
import com.example.craveyard.utilities.network.RemoteDataSource
import com.example.craveyard.recipe.model.Meal
import com.example.craveyard.recipe.model.Meals
=======
import com.example.craveyard.data.network.RemoteDataSource
import com.example.craveyard.data.model.Meals
>>>>>>> Stashed changes:app/src/main/java/com/example/craveyard/ui/home/repo/HomeRepository.kt


class HomeRepository (private val remoteDataSource: RemoteDataSource) {
    suspend fun getAllMeals(): Meals = remoteDataSource.getAllMeals()

    suspend fun getRandomMeal(): Meals = remoteDataSource.getRandomMeal()

}