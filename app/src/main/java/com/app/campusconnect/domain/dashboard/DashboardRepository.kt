package com.app.campusconnect.domain.dashboard

import com.app.campusconnect.models.dashboard.Enrollment
import com.app.campusconnect.models.dashboard.Event
import com.app.campusconnect.models.dashboard.EventCreated

interface DashboardRepository {
    suspend fun getEvents(): List<Event>
    suspend fun getEventsEnrolled(): List<Enrollment>
    suspend fun createEvent(eventCreated: EventCreated)
    suspend fun subscribeEvent(id: Int)
    suspend fun unsubscribeEvent(id: Int)
}

