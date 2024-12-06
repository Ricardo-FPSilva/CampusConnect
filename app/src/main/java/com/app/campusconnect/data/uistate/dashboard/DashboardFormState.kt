package com.app.campusconnect.data.uistate.dashboard

import com.app.campusconnect.models.common.User
import com.app.campusconnect.models.dashboard.Enrollment
import com.app.campusconnect.models.dashboard.Event
import com.app.campusconnect.models.dashboard.EventCreated

data class DashboardFormState(
    val eventsList: List<Event> = emptyList(),
    val eventsListEnrolled: List<Enrollment> = emptyList(),

    val user: User? = null,

    val searchTerm: String = "",
    val eventsListFiltered: List<Event> = emptyList(),

    val isSelectedMyEvents: Boolean = true,
    val selectedEvent: Event? = null,
    val isSubscribed: Boolean = false,


    val eventCreated: EventCreated = EventCreated(
        title = "kotlin",
        description = "Evento Kotlin",
        location = "Biblioteca",
        eventDate = "",
    )
)