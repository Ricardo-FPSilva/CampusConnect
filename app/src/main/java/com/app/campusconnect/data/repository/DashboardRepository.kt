package com.app.campusconnect.data.repository

import com.app.campusconnect.network.DashboardApiService
import com.app.campusconnect.network.models.Event

interface DashboardRepository {
    suspend fun getEvents(): List<Event>
}

class NetworkDashboardRepository(
    private val dashboardApiService: DashboardApiService
) : DashboardRepository {
    override suspend fun getEvents(): List<Event> = dashboardApiService.getEvents()
}