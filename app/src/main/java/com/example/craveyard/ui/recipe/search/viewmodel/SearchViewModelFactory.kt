package com.example.craveyard.ui.recipe.search.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.craveyard.ui.recipe.search.repo.SearchRepository

class SearchViewModelFactory(private val searchRepository: SearchRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchViewModel::class.java)) {
            return SearchViewModel(searchRepository) as T
        }
        throw IllegalArgumentException("Unknown Search ViewModel class")
    }
}