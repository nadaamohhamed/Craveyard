package com.example.craveyard.ui.recipe.utils.clickhandler

import com.example.craveyard.data.model.meals.Meal
import com.example.craveyard.data.model.category.Category

interface ClickHandler {
    fun onMealClick(meal : Meal)
    fun onCategoryClick(category: Category)

}