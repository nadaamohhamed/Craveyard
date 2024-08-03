package com.example.craveyard.ui.recipe.category.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.craveyard.data.model.meals.Meal
import com.example.craveyard.ui.recipe.category.repo.CategoryRepository
import kotlinx.coroutines.launch

class CategoryViewModel(private val categoryRepository: CategoryRepository) : ViewModel() {

    private val _meals = MutableLiveData<MutableList<Meal>>()
    val meals: LiveData<MutableList<Meal>> get() = _meals

    fun getMealsByCategory(category: String) {
        viewModelScope.launch {
            val response = categoryRepository.getMealsByCategory(category)
            val myMeals=ArrayList<Meal>()

            for (meal in response.meals){
                val myMeal = categoryRepository.searchById(meal.idMeal).meals[0]
                myMeals.add(myMeal)
            }
            _meals.postValue(myMeals)
        }
    }
}