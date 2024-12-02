package com.app.campusconnect.data.uistate.authentication

data class AuthFormState(
    val registrationNumber: String = "",
    val email: String = "arhur.de@gmail.co",
    val emailCode: String = "",
    val name: String = "",
    val course: String = "",

    val password: String = "123456",
    val newPassword: String = "",
    val confirmPassword: String = "", // Renomeado

    val isUserLoggedIn: Boolean = false
)