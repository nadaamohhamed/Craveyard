package com.example.craveyard.ui.recipe.favorite.repo

import com.example.craveyard.data.model.meals.Meal

interface FavoriteRepository {

    fun getAllFavorites(): MutableList<Meal>

    fun addToFavorite(meal: Meal)

    fun removeFromFavorite(meal: Meal)

    fun isFavorite(meal: Meal): Boolean

}