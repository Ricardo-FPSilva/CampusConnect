package com.app.campusconnect.data

data class CampusConnectUiState(
    val eventList: List<Event> = DataSource.loadEvents(),
)
