package com.app.campusconnect.ui.authentication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.campusconnect.data.AuthUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(AuthUiState())
    val uiState: StateFlow<AuthUiState> = _uiState.asStateFlow()

    private val _isUserLoggedIn = MutableStateFlow(false)
    val isUserLoggedIn: StateFlow<Boolean> = _isUserLoggedIn.asStateFlow()

    fun updateLoginStatus(isLoggedIn: Boolean){
        viewModelScope.launch {
            _isUserLoggedIn.emit(isLoggedIn)
        }
    }

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