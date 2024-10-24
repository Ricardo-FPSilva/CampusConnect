package com.app.campusconnect.ui

import androidx.lifecycle.ViewModel
import com.app.campusconnect.data.LoginUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LoginViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    fun setMatriculate(matriculate: String) {
        _uiState.update { currentState ->
            currentState.copy(
                matricula = matriculate
            )
        }
    }
    fun setEmailCode(emailCode: String) {
        _uiState.update { currentState ->
            currentState.copy(
                emailCode = emailCode
            )
        }
    }
    fun setNewPassword(newPassword: String) {
        _uiState.update { currentState ->
            currentState.copy(
                newPassword = newPassword
            )
        }
    }
    fun setConfirmNewPassword(confirmNewPassword: String) {
        _uiState.update { currentState ->
            currentState.copy(
                confirmNewPassword = confirmNewPassword
            )
        }
    }
    fun setPassword(password: String) {
        _uiState.update { currentState ->
            currentState.copy(
                senha = password
            )
        }
    }

}