package com.example.craveyard.ui.recipe.favorite.repo

import com.example.craveyard.data.model.entity.FavMeal
import com.example.craveyard.data.model.meals.Meal

interface FavRepoInterface {

    suspend fun insertFavMeal(favMeal: FavMeal)

    suspend fun deleteFavMeal(favMeal: FavMeal)

    suspend fun getFavMeals(email: String):List<FavMeal>

    suspend fun isFavorite(favMeal: Meal, email: String): Boolean
}