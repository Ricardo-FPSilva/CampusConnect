package com.app.campusconnect.data.uistate.common
import com.app.campusconnect.models.common.ErrorType

sealed interface UiState {
    data object Loading : UiState
    data object Success : UiState
    data class Error(val type: ErrorType, val message: String) : UiState
}