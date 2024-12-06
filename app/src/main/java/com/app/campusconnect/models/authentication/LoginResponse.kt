package com.app.campusconnect.models.authentication

import com.app.campusconnect.models.common.User
import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("user")
    val user : User,
    @SerializedName("token")
    val token : String
)
