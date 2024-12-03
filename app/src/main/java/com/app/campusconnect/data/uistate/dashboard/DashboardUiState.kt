package com.app.campusconnect.data.uistate.dashboard

import com.app.campusconnect.data.uistate.models.ErrorType

sealed interface DashboardUiState {
    data object Loading : DashboardUiState
    data class Success(val data: Any? = null) : DashboardUiState
    data class Error(val type: ErrorType, val message: String) : DashboardUiState
}