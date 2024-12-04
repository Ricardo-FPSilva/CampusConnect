package com.app.campusconnect.domain.authentication

import com.app.campusconnect.models.authentication.LoginRequest
import com.app.campusconnect.models.authentication.LoginResponse


interface AuthRepository {
    suspend fun login(loginRequest: LoginRequest): LoginResponse
}

