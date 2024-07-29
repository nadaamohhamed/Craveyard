package com.example.craveyard.recipe.search.clickhandler

import com.example.craveyard.recipe.model.Meal

interface ClickHandler {
    fun onMealClick(meal : Meal)
}