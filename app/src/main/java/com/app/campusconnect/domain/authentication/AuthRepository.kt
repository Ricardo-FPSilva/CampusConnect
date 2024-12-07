package com.app.campusconnect.domain.authentication

import com.app.campusconnect.models.authentication.LoginRequest
import com.app.campusconnect.models.authentication.LoginResponse
import com.app.campusconnect.models.authentication.ProfileLoginResponse


interface AuthRepository {
    suspend fun getProfileLogin(credential: String): ProfileLoginResponse
    suspend fun login(loginRequest: LoginRequest): LoginResponse
}

