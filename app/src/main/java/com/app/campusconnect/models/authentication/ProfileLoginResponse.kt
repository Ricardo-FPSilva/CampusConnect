package com.app.campusconnect.models.authentication

import com.google.gson.annotations.SerializedName

data class ProfileLoginResponse(
    @SerializedName("name")
    val name: String,
    @SerializedName("role")
    val role: String
)
