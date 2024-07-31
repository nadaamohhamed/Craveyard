package com.example.craveyard.authentication.fragments

import com.example.craveyard.data.db.User

sealed class RegisterViewEvents {

    data class navigatToHome(val user: User):RegisterViewEvents()
}