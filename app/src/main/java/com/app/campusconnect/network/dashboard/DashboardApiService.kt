package com.app.campusconnect.network.dashboard

import com.app.campusconnect.models.dashboard.Enrollment
import com.app.campusconnect.models.dashboard.Event
import com.app.campusconnect.models.dashboard.EventCreated
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface DashboardApiService {
    @GET("api/events")
    suspend fun getEvents(): List<Event>

    @GET("api/events/enroll")
    suspend fun  getEventsEnrolled(): List<Enrollment>

    @GET("api/events")
    suspend fun createEvent(eventCreated: EventCreated)

    @POST("api/events/{id}/enroll")
    suspend fun subscribeEvent(@Path("id") id: Int)

    @DELETE("api/events/{id}/unroll")
    suspend fun unsubscribeEvent(@Path("id") id: Int)

}