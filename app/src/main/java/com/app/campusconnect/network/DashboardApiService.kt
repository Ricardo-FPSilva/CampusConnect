package com.app.campusconnect.network

import retrofit2.http.GET

interface DashboardApiService {
    @GET("api/events")
    suspend fun getEvents(): List<Event>
}