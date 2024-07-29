package com.example.craveyard.recipe.clickhandler

import com.example.craveyard.recipe.model.Meal

interface ClickHandler {
    fun onMealClick(meal :Meal)
}