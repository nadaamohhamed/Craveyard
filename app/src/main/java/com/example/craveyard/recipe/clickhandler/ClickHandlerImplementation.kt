package com.example.craveyard.recipe.clickhandler

import com.example.craveyard.recipe.model.Meal
import com.example.craveyard.recipe.search.clickhandler.ClickHandler
import com.example.craveyard.recipe.search.view.SearchFragment

class ClickHandlerImplementation : ClickHandler {
    override fun onMealClick(meal: Meal) {
//        SearchFragmentDirections.actionSearchFragmentToRecipeFragment(meal).also {
//            SearchFragment.findNavController().navigate(it)
//        }
    }
}