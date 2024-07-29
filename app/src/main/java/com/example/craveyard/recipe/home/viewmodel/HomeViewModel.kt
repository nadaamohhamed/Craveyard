package com.example.craveyard.recipe.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.craveyard.recipe.home.repo.HomeRepository
import com.example.craveyard.recipe.model.Meal
import com.example.craveyard.recipe.model.Meals
import com.example.craveyard.recipe.search.repo.SearchRepository
import kotlinx.coroutines.launch

class HomeViewModel(private val homeRepository: HomeRepository) : ViewModel() {

    private val _recipes = MutableLiveData<List<Meal>>()
    val recipes: LiveData<List<Meal>> = _recipes

    private val _randomMeal = MutableLiveData<List<Meal>>()
    val randomMeal: LiveData<List<Meal>> = _randomMeal

    fun getAllMeals() {
        viewModelScope.launch {
            val response = homeRepository.getAllMeals()
            _recipes.postValue(response.meals)
        }
    }

    fun getRandomMeal() {
        viewModelScope.launch {
            val response = homeRepository.getRandomMeal()
            _randomMeal.postValue(response.meals)
        }
    }

}