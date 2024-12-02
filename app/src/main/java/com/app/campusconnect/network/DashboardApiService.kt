package com.app.campusconnect.network

import com.app.campusconnect.network.models.Event
import retrofit2.http.GET

interface DashboardApiService {
    @GET("api/events")
    suspend fun getEvents(): List<Event>
}