package com.app.campusconnect.data.repository.authentication

import com.app.campusconnect.network.authentication.AuthApiService
import com.app.campusconnect.network.authentication.models.LoginRequest
import com.app.campusconnect.network.authentication.models.LoginResponse

class NetworkAuthRepository(
    private val authApiService: AuthApiService
) : AuthRepository {
    override suspend fun login(loginRequest: LoginRequest): LoginResponse {
        return authApiService.login(loginRequest)
    }
}