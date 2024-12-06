package com.app.campusconnect.models.dashboard


import com.app.campusconnect.models.common.User
import com.google.gson.annotations.SerializedName

data class Event (
    @SerializedName(value = "id")
    val id: Int,
    @SerializedName(value = "title")
    val title: String,
    @SerializedName(value = "description")
    val description: String,
    @SerializedName(value = "location")
    val location: String,
    @SerializedName(value = "eventDate")
    val eventDate: String,
    @SerializedName(value = "createdBy")
    val createdBy: User,
)
