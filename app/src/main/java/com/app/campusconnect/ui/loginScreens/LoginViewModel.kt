package com.app.campusconnect.ui.loginScreens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.app.campusconnect.data.LoginUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class LoginViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    var matricula: String by mutableStateOf("")
        private set

    var emailCode: String by mutableStateOf("")
        private set

    var confirmaSenha: String by mutableStateOf("")
        private set

    var newPassword : String by mutableStateOf("")
        private set

    var confirmNewPassword : String by mutableStateOf("")
        private set


    fun updateMatricula(value: String) {
        matricula = value
    }

    fun updateEmailCode(value: String) {
        emailCode = value
    }

    fun updateConfirmaSenha(value: String) {
        confirmaSenha = value
    }

    fun updateNewPassword(value: String) {
        newPassword = value
    }

    fun updateConfirmNewPassword(value: String) {
        confirmNewPassword = value
    }




}