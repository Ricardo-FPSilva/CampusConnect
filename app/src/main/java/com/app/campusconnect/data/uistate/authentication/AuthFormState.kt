package com.app.campusconnect.data.uistate.authentication

data class AuthFormState(
    val email: String = "ricardo.felipe3000@gmail.com",
    val emailCode: String = "",
    val name: String = "",
    val course: String = "",

    val login: String = "ricardo.felipe3000@gmail.com",
    val password: String = "123456",

    val newPassword: String = "",
    val confirmPassword: String = "", // Renomeado

    val isUserLoggedIn: Boolean = false
)