package com.example.craveyard.ui.recipe.favorite.viewmodel

import androidx.lifecycle.ViewModelProvider
import com.example.craveyard.ui.recipe.favorite.repo.FavoriteRepository

class FavoriteViewModelFactory (private val favoriteRepository: FavoriteRepository) : ViewModelProvider.Factory  {

    override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavoriteViewModel::class.java)) {
            return FavoriteViewModel(favoriteRepository) as T
        }
        throw IllegalArgumentException("Unknown Favorite ViewModel class")
    }
}