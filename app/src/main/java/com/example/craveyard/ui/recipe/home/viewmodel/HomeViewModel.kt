package com.example.craveyard.ui.recipe.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.craveyard.ui.recipe.home.repo.HomeRepository
import com.example.craveyard.data.model.Meal
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