package com.example.craveyard.ui.recipe.search.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.craveyard.data.model.meal.Meal
import com.example.craveyard.ui.recipe.search.repo.SearchRepository
import kotlinx.coroutines.launch

class SearchViewModel(private val searchRepository: SearchRepository) : ViewModel() {

    private val _recipes = MutableLiveData<List<Meal>>()
    val recipes: LiveData<List<Meal>> = _recipes

    fun search(query: String) {
        viewModelScope.launch {
            val response = searchRepository.search(query)
            _recipes.postValue(response.meals)
        }
    }

}