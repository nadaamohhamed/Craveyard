package com.example.craveyard.ui.auth.register.events

import com.example.craveyard.data.model.auth.User

sealed class RegisterViewEvents {

    data class navigatToHome(val user: User): RegisterViewEvents()
}