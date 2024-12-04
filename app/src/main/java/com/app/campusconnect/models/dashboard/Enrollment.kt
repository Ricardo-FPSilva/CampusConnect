package com.app.campusconnect.models.dashboard

import com.google.gson.annotations.SerializedName

data class Enrollment(
    @SerializedName("id")
    val id: Int,
    @SerializedName("event")
    val event: Event,
    @SerializedName("enrollment_date")
    val enrollmentDate: String
)
