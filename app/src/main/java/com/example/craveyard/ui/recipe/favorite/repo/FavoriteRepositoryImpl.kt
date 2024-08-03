package com.example.craveyard.ui.recipe.favorite.repo

import com.example.craveyard.ui.auth.login.repo.UserRepository
import com.example.craveyard.data.model.meals.Meal

class FavoriteRepositoryImpl(private val userRepository: UserRepository) : FavoriteRepository {

    override fun getAllFavorites(): MutableList<Meal> {
        return userRepository.getCurrentUser().favorites ?: mutableListOf()
    }

    override fun addToFavorite(meal: Meal) {
        val updatedFavorites = userRepository.getCurrentUser().favorites?.toMutableList() ?: mutableListOf()

        if(!updatedFavorites.contains(meal)){
            updatedFavorites.add(meal)
        }

        // update user with new favorites
        val user = userRepository.getCurrentUser()
        user.favorites = updatedFavorites
        userRepository.updateUser(user)
    }

    override fun removeFromFavorite(meal: Meal) {
        val updatedFavorites = userRepository.getCurrentUser().favorites?.toMutableList() ?: mutableListOf()

        if(updatedFavorites.contains(meal)){
            updatedFavorites.remove(meal)
        }

        // update user with new favorites
        val user = userRepository.getCurrentUser()
        user.favorites = updatedFavorites
        userRepository.updateUser(user)
    }

    override fun isFavorite(meal: Meal): Boolean {
        return userRepository.getCurrentUser().favorites?.contains(meal) ?: false
    }
}