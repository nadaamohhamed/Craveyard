package com.example.craveyard.data.db.localdata


import com.example.craveyard.data.model.entity.FavMeal

interface LocalDsInterface {

    suspend fun insertFavMeal(favMeal: FavMeal)

    suspend fun deleteFavMeal(favMeal: FavMeal)

    suspend fun getFavMeals(email: String):List<FavMeal>

    suspend fun getMeal(email: String,id:String):FavMeal
}