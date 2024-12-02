package com.app.campusconnect.data.repository.authentication

import com.app.campusconnect.network.authentication.models.LoginRequest
import com.app.campusconnect.network.authentication.models.LoginResponse


interface AuthRepository {
    suspend fun login(loginRequest: LoginRequest): LoginResponse
}

