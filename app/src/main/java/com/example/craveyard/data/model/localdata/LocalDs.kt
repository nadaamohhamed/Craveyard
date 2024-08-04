package com.example.craveyard.data.model.localdata

import android.content.Context
import com.example.craveyard.data.model.entity.FavMeal

class LocalDs (context: Context) :LocalDsInterface {


   private  var db:UserDatabase
    private  var favMealDao:FavMealDao


    init {
        db=UserDatabase.getInstance(context)
        favMealDao=db.getFavMealDao()
    }



    override suspend fun insertFavMeal(favMeal: FavMeal) {
        favMealDao.insertFavMeal(favMeal)
    }

    override suspend fun deleteFavMeal(favMeal: FavMeal) {
        favMealDao.deleteFavMeal(favMeal)
    }

    override suspend fun getFavMeals(email: String): List<FavMeal> {
        return favMealDao.getFavMeals(email)
    }

    override suspend fun getMeal(email: String, id: String): FavMeal {
        return favMealDao.getMeal(email,id)
    }
}