package com.example.craveyard.data.model.auth

import com.example.craveyard.data.model.meals.Meal
import java.io.Serializable


data class User(
    val uid:String?=null,
    val userName:String?=null,
    val email:String?=null,
    var favorites:MutableList<Meal>?=null,
):Serializable