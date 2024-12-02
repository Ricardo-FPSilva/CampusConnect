package com.app.campusconnect.data.repository.dashboard

import com.app.campusconnect.network.dashboard.DashboardApiService
import com.app.campusconnect.network.dashboard.models.Enrollment
import com.app.campusconnect.network.dashboard.models.Event

class NetworkDashboardRepository(
    private val dashboardApiService: DashboardApiService
) : DashboardRepository {
    override suspend fun getEvents(): List<Event> = dashboardApiService.getEvents()
    override suspend fun getEventsEnrolled(): List<Enrollment> = dashboardApiService.getEventsEnrolled()
    override suspend fun eventRegistration(id: Int) = dashboardApiService.eventRegistration(id)
}