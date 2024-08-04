package com.example.craveyard.ui.recipe.favorite.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.craveyard.data.model.entity.FavMeal
import com.example.craveyard.data.model.meals.Meal
import com.example.craveyard.ui.recipe.favorite.repo.FavRepoInterface
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class FavViewModel(private val favRepo: FavRepoInterface):ViewModel() {

    private val _fav_meals = MutableLiveData<List<FavMeal>>()
    val favMeals: LiveData<List<FavMeal>> = _fav_meals


    fun insertFavMeal(favMeal: FavMeal){
        viewModelScope.launch {
            favRepo.insertFavMeal(favMeal)
        }
    }

    fun deleteFavMeal(favMeal: FavMeal){
        viewModelScope.launch {
            favRepo.deleteFavMeal(favMeal)

        }

    }

    fun getFavMeals(email:String) {
        viewModelScope.launch(Dispatchers.Default){
            _fav_meals.postValue(favRepo.getFavMeals(email))
        }
    }

    fun isFavourite(favMeal:Meal,email:String):Boolean {
        var x=false
        viewModelScope.async {
            x=favRepo.isFavorite(favMeal,Firebase.auth.currentUser!!.email!!)

        }
       return x
    }
}