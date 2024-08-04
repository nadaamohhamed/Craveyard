package com.example.craveyard.ui.recipe.favorite.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.craveyard.ui.recipe.favorite.repo.FavRepoInterface
import com.example.craveyard.ui.recipe.search.viewmodel.SearchViewModel

class FavViewModelFactory(private val favRepo: FavRepoInterface) :ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavViewModel::class.java)) {
            return FavViewModel(favRepo) as T
        }
        throw IllegalArgumentException("Unknown favourite ViewModel class")
    }
}