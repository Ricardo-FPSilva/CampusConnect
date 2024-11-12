package com.app.campusconnect.data

data class DashboardUiState(
    val eventList: List<Event> = DataSource.loadEvents(),
)
