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
import kotlinx.coroutines.withContext

class FavViewModel(private val favRepo: FavRepoInterface):ViewModel() {

    private val _fav_meals = MutableLiveData<List<FavMeal>>()
    val favMeals: LiveData<List<FavMeal>> = _fav_meals

    private val _meal = MutableLiveData<FavMeal>()
    val meal: LiveData<FavMeal> = _meal

    fun insertFavMeal(favMeal: FavMeal){
        viewModelScope.launch {
            favRepo.insertFavMeal(favMeal)
        }
    }

    fun deleteFavMeal(favMeal: FavMeal){
        viewModelScope.launch {
            favRepo.deleteFavMeal(favMeal)
            Log.d("asd","removed")

        }

    }

    fun getFavMeals(email:String) {
        viewModelScope.launch(Dispatchers.Default){
            _fav_meals.postValue(favRepo.getFavMeals(email))
        }
    }

suspend fun getMeal(email: String, id:String): FavMeal {
    val favMeal= withContext(Dispatchers.Default){

        val meal =favRepo.getMeal(email,id)
        meal
    }
    Log.d("asd","${favMeal.mealId}")
    return favMeal

}

    suspend fun isFavorite(favMeal: Meal, email: String): Boolean {
        val x= withContext(Dispatchers.Default) {
            val meals = favRepo.getFavMeals(email)
            meals.any { it.mealId == favMeal.idMeal }
        }
        return x
    }




}