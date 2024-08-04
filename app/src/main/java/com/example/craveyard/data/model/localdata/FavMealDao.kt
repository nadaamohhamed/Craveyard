package com.example.craveyard.data.model.localdata

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.craveyard.data.model.entity.FavMeal

@Dao
interface FavMealDao {

    @Insert
    suspend fun insertFavMeal(favMeal: FavMeal)

    @Delete
    suspend fun deleteFavMeal(favMeal: FavMeal)

    @Query("SELECT * FROM favmeal WHERE userEmail ==:email")
    fun getFavMeals(email: String):List<FavMeal>

}