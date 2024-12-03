package com.app.campusconnect.data.repository.dashboard

import com.app.campusconnect.network.dashboard.models.Enrollment
import com.app.campusconnect.network.dashboard.models.Event

interface DashboardRepository {
    suspend fun getEvents(): List<Event>
    suspend fun getEventsEnrolled(): List<Enrollment>
    suspend fun eventRegistration(id: Int)
    suspend fun eventUnregistration(id: Int)
}

