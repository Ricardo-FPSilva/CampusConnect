package com.app.campusconnect.data.uistate.dashboard

import com.app.campusconnect.network.dashboard.models.Enrollment
import com.app.campusconnect.network.dashboard.models.Event

data class DashboardFormState(
    val eventList: List<Event> = emptyList(),
    val eventListEnrolled: List<Enrollment> = emptyList(),

    val searchTerm: String = "",
    val eventListFiltered: List<Event> = emptyList(),

    val isSelectedMyEvents: Boolean = true,
    val selectedEvent: Event? = null
)