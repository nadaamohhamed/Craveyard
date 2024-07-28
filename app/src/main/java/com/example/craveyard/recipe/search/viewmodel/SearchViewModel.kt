package com.example.craveyard.recipe.search.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.craveyard.recipe.model.Meal
import com.example.craveyard.recipe.model.Meals
import com.example.craveyard.recipe.model.Recipe
import com.example.craveyard.recipe.search.repo.SearchRepository
import kotlinx.coroutines.launch

class SearchViewModel(private val searchRepository: SearchRepository) : ViewModel() {

    private val _recipes = MutableLiveData<Meals>()
    val recipes: LiveData<Meals> = _recipes

    fun search(query: String) {
        viewModelScope.launch {
            val response = searchRepository.search(query)
            _recipes.value = response
        }
    }

}