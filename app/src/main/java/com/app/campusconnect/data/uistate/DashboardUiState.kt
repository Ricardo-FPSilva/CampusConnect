package com.app.campusconnect.data.uistate

import com.app.campusconnect.data.DataSource
import com.app.campusconnect.data.Event

data class DashboardUiState(
    val eventList: List<Event> = DataSource.loadEvents(),
)
