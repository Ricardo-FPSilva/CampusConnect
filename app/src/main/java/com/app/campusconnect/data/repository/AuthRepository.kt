package com.app.campusconnect.data.repository

import com.app.campusconnect.network.AuthApiService
import com.app.campusconnect.network.models.LoginRequest
import com.app.campusconnect.network.models.LoginResponse


interface AuthRepository {
    suspend fun getLogin(loginRequest: LoginRequest): LoginResponse
}

class NetworkAuthRepository(
    private val authApiService: AuthApiService
) : AuthRepository {
    override suspend fun getLogin(loginRequest: LoginRequest): LoginResponse {
        return authApiService.getLogin(loginRequest)
    }
}