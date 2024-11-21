package com.app.campusconnect.data.uistate

import com.app.campusconnect.network.Event

sealed interface DashboardUiState {
    data object Loading : DashboardUiState
    data class Success(
        val eventList: List<Event>,
        var isSelectedMyEvents: Boolean = true,
    ) : DashboardUiState
    data class Error(
        val message: String
    ) : DashboardUiState
}