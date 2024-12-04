package com.app.campusconnect.models.dashboard

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName(value = "id")
    val id: Int,
    @SerializedName(value = "name")
    val name: String,
    @SerializedName(value = "email")
    val email: String,
    @SerializedName(value = "registrationNumber")
    val registrationNumber: String?,
    @SerializedName(value = "role")
    val role: String,
)
