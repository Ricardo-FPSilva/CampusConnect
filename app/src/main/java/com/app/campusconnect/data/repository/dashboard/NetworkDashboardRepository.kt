package com.app.campusconnect.data.repository.dashboard

import com.app.campusconnect.domain.dashboard.DashboardRepository
import com.app.campusconnect.network.dashboard.DashboardApiService
import com.app.campusconnect.models.dashboard.Enrollment
import com.app.campusconnect.models.dashboard.Event
import com.app.campusconnect.models.dashboard.EventCreated

class NetworkDashboardRepository(
    private val dashboardApiService: DashboardApiService
) : DashboardRepository {
    override suspend fun getEvents(): List<Event> = dashboardApiService.getEvents()
    override suspend fun getEventsEnrolled(): List<Enrollment> = dashboardApiService.getEventsEnrolled()
    override suspend fun createEvent(eventCreated: EventCreated) = dashboardApiService.createEvent(eventCreated)
    override suspend fun subscribeEvent(id: Int) = dashboardApiService.subscribeEvent(id)
    override suspend fun unsubscribeEvent(id: Int) = dashboardApiService.unsubscribeEvent(id)

}