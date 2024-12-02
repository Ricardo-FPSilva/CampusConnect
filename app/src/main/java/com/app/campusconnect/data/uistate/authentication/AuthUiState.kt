package com.app.campusconnect.data.uistate.authentication

sealed interface AuthUiState {
    data object Loading : AuthUiState
    data class Success(val data: Any? = null) : AuthUiState // Simplificado
    data class Error(val message: String) : AuthUiState
}