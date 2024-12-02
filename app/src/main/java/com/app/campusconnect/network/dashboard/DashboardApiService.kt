package com.app.campusconnect.network.dashboard

import com.app.campusconnect.network.dashboard.models.Enrollment
import com.app.campusconnect.network.dashboard.models.Event
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface DashboardApiService {
    @GET("api/events")
    suspend fun getEvents(): List<Event>

    @GET("api/events/enroll")
    suspend fun  getEventsEnrolled(): List<Enrollment>

    @POST("api/events/{id}/enroll")
    suspend fun eventRegistration(@Path("id") id: Int)
}