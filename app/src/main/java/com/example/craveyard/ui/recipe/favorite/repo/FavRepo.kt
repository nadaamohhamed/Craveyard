package com.example.craveyard.ui.recipe.favorite.repo

import android.util.Log
import com.example.craveyard.data.model.entity.FavMeal
import com.example.craveyard.data.model.localdata.LocalDsInterface
import com.example.craveyard.data.model.meals.Meal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext

class FavRepo(private val localDsInterface: LocalDsInterface):FavRepoInterface {


    override suspend fun insertFavMeal(favMeal: FavMeal) {
        localDsInterface.insertFavMeal(favMeal)
    }

    override suspend fun deleteFavMeal(favMeal: FavMeal) {
        localDsInterface.deleteFavMeal(favMeal)
    }

    override suspend fun getFavMeals(email: String): List<FavMeal> {
        return localDsInterface.getFavMeals(email)
    }

    override suspend fun getMeal(email: String, id: String): FavMeal {
        return localDsInterface.getMeal(email,id)
    }


}