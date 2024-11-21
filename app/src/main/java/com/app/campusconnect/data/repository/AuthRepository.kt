package com.app.campusconnect.data.repository

import com.app.campusconnect.network.AuthApiService
import com.app.campusconnect.network.User


interface AuthRepository {
    suspend fun getLogin(registration: String, password: String): User
}

class NetworkAuthRepository(
    private val authApiService: AuthApiService
) : AuthRepository {
    override suspend fun getLogin(registration: String, password: String): User = authApiService.getLogin(registration, password)
}