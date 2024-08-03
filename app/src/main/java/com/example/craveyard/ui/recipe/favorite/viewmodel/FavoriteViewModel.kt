package com.example.craveyard.ui.recipe.favorite.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.craveyard.data.model.meals.Meal
import com.example.craveyard.ui.recipe.favorite.repo.FavoriteRepository

class FavoriteViewModel (private val repository: FavoriteRepository) : ViewModel(){

    private val _favorites = MutableLiveData<MutableList<Meal>>()
    val favorites: LiveData<MutableList<Meal>> get() = _favorites

    fun getAllFavorites() {
        val favoriteList = repository.getAllFavorites()
        _favorites.postValue(favoriteList)
    }

    fun removeFavorite(meal: Meal) {
        repository.removeFromFavorite(meal)
    }

    fun addFavorite(meal: Meal) {
        repository.addToFavorite(meal)
    }

    fun isFavorite(meal: Meal): Boolean {
        return repository.isFavorite(meal)
    }
}