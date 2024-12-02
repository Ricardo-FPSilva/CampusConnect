package com.app.campusconnect.network.models

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("token")
    val token : String
)
