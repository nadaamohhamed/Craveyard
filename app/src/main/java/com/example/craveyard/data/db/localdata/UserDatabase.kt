package com.example.craveyard.data.db.localdata


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.craveyard.data.model.entity.FavMeal


@Database(entities = [FavMeal::class], version = 1)
abstract class UserDatabase : RoomDatabase(){


    abstract fun getFavMealDao(): FavMealDao

    companion object{

        @Volatile
        private  var instance: UserDatabase? =null

        fun getInstance(context: Context): UserDatabase {
            return instance ?: synchronized(this){

                val INSTANCE =Room.databaseBuilder(context, UserDatabase::class.java,"user_db")
                    .build()
                instance = INSTANCE
                return INSTANCE
            }
        }
    }
}