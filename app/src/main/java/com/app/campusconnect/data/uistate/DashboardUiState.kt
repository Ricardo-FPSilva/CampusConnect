package com.app.campusconnect.data.uistate

import com.app.campusconnect.network.Event

sealed interface DashboardUiState {
    data object Loading : DashboardUiState
    data class Success(
        val eventList: List<Event>,
    ) : DashboardUiState
    data class Error(
        val message: String
    ) : DashboardUiState
}