package com.app.campusconnect.domain.dashboard

import com.app.campusconnect.models.dashboard.Enrollment
import com.app.campusconnect.models.dashboard.Event

interface DashboardRepository {
    suspend fun getEvents(): List<Event>
    suspend fun getEventsEnrolled(): List<Enrollment>
    suspend fun subscribeEvent(id: Int): Unit
    suspend fun unsubscribeEvent(id: Int): Unit
}

