package com.app.campusconnect.models.authentication

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("token")
    val token : String
)
