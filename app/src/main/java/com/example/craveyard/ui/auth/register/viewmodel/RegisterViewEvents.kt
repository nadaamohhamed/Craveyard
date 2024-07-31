package com.example.craveyard.authentication.fragments

import com.example.chat.database.User

sealed class RegisterViewEvents {

    data class navigatToHome(val user: User):RegisterViewEvents()
}