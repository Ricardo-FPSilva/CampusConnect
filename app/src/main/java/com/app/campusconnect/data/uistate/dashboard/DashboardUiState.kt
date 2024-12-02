package com.app.campusconnect.data.uistate.dashboard

sealed interface DashboardUiState {
    data object Loading : DashboardUiState
    data class Success(val data: Any? = null) : DashboardUiState
    data class Error(val message: String) : DashboardUiState
}