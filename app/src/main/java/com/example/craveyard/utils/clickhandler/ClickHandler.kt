package com.example.craveyard.utils.clickhandler

import com.example.craveyard.data.model.Meal

interface ClickHandler {
    fun onMealClick(meal : Meal)
}