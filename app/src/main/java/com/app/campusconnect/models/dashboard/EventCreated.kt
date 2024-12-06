package com.app.campusconnect.models.dashboard

import com.google.gson.annotations.SerializedName

data class EventCreated(
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("location")
    val location: String,
    @SerializedName("eventDate")
    val eventDate: String,
)
