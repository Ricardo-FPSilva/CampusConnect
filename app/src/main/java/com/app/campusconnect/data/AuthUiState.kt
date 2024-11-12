package com.app.campusconnect.data

data class AuthUiState(

    val matricula: String = "",

    val senha: String = "",

    val email: String = "",

    val emailCode: String = "",

    val nome: String = "",

    val confirmaSenha: String = "",

    val newPassword: String = "",

    val confirmNewPassword: String = "",

    val curso: String = "",

    val matriculaError: Int? = null,

    val senhaError: Int? = null,

    val isUserLoggedIn: Boolean = false,

    val isLoading: Boolean = false,

    val isSuccess: Boolean = false,

    val isError: Boolean = false,

    val errorMessage: Int? = null,

    val showPassword: Boolean = false,

    val showConfirmPassword: Boolean = false,

    val showTermsAndConditions: Boolean = false,

    val showPrivacyPolicy: Boolean = false,

    val showForgotPassword: Boolean = false,
)

