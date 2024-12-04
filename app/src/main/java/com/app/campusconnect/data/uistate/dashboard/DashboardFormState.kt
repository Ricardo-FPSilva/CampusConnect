package com.app.campusconnect.data.uistate.dashboard

import com.app.campusconnect.models.dashboard.Enrollment
import com.app.campusconnect.models.dashboard.Event

data class DashboardFormState(
    val eventsList: List<Event> = emptyList(),
    val eventsListEnrolled: List<Enrollment> = emptyList(),

    val searchTerm: String = "",
    val eventsListFiltered: List<Event> = emptyList(),

    val isSelectedMyEvents: Boolean = true,
    val selectedEvent: Event? = null
)