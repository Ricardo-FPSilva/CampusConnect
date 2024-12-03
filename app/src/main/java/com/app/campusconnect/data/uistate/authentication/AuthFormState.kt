package com.app.campusconnect.data.uistate.authentication

data class AuthFormState(
    val registrationNumber: String = "",
    val email: String = "ricardo.felipe3000@gmail.com",
    val emailCode: String = "",
    val name: String = "",
    val course: String = "",

    val password: String = "",
    val newPassword: String = "",
    val confirmPassword: String = "", // Renomeado

    val isUserLoggedIn: Boolean = false
)