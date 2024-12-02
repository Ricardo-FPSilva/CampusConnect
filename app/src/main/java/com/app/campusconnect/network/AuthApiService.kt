package com.app.campusconnect.network

import com.app.campusconnect.network.models.LoginRequest
import com.app.campusconnect.network.models.LoginResponse
import com.app.campusconnect.network.models.User
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApiService {
    @POST("auth/login")
    suspend fun getLogin(@Body loginRequest: LoginRequest): LoginResponse
}