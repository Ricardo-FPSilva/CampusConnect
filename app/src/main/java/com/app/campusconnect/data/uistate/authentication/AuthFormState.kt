package com.app.campusconnect.data.uistate.authentication

data class AuthFormState(
    val email: String = "",
    val emailCode: String = "",

    val name: String = "",
    val role: String = "",

    val login: String = "",
    val password: String = "",

    val newPassword: String = "",
    val confirmPassword: String = "",

    val isUserLoggedIn: Boolean = false
)