package com.example.craveyard.utils.clickhandler

import com.example.craveyard.data.model.meal.Meal

interface ClickHandler {
    fun onMealClick(meal : Meal)
}