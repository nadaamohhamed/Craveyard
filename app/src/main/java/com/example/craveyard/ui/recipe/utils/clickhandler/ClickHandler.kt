package com.example.craveyard.ui.recipe.utils.clickhandler

import com.example.craveyard.data.model.meals.Meal

interface ClickHandler {
    fun onMealClick(meal : Meal)
}