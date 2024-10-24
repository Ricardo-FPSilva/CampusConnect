package com.app.campusconnect.data

data class LoginUiState(

    val matricula: String = "",

    val senha: String = "",

    val email: String = "ricardo.silvamain@gmail.com",

    val emailCode: String = "",

    val nome: String = "Ricardo",

    val confirmaSenha: String = "",

    val newPassword: String = "",

    val confirmNewPassword: String = "",

    val curso: String = "Ciência da Computação",

    val matriculaError: Int? = null,

    val senhaError: Int? = null,

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

