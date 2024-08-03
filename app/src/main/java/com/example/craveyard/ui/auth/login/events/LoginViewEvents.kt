package com.example.craveyard.ui.auth.login.events

import com.example.craveyard.data.model.auth.User

sealed class LoginViewEvents {

    data class navigateToHome(val user: User):LoginViewEvents()

    class navigateToRegister:LoginViewEvents()
}
