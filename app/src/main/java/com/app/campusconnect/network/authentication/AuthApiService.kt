package com.app.campusconnect.network.authentication

import com.app.campusconnect.models.authentication.LoginRequest
import com.app.campusconnect.models.authentication.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApiService {
    @POST("auth/login")
    suspend fun login(@Body loginRequest: LoginRequest): LoginResponse
}