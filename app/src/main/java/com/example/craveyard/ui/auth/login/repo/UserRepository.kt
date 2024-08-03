package com.example.craveyard.ui.auth.login.repo

import com.example.craveyard.data.model.auth.User
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

object UserRepository{

    private lateinit var loggedInUser: User

    fun setUser(user: User) {
        loggedInUser = user
    }

    fun getCurrentUser(): User {
        return loggedInUser
    }

    fun updateUser(user: User) {
        val db = Firebase.firestore
        db.collection("users").document(user.uid!!).set(user)
    }
}