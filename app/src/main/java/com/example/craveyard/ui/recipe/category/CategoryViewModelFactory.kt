package com.example.craveyard.ui.recipe.category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class CategoryViewModelFactory(private val categoryRepository: CategoryRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CategoryViewModel::class.java)) {
            return CategoryViewModel(categoryRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}