package com.example.craveyard.data.model.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity()
data class FavMeal (
    val userEmail:String,
    val mealId:String,
    val strMeal: String,
    val strArea: String ,
    val strCategory: String ,
    val strInstructions: String ,
    val strYoutube: String ,
    val MealThumb:String,
    @PrimaryKey(autoGenerate = true)
    val id:Int=0,
)