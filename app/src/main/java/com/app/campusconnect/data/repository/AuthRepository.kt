package com.app.campusconnect.data.repository

import com.app.campusconnect.network.CampusConnectApiService
import com.app.campusconnect.network.User


interface AuthRepository {
    suspend fun getLogin(registration: String, password: String): Result<String>
}

class NetworkAuthRepository(
    private val authApiService: CampusConnectApiService
) : AuthRepository {
    override suspend fun getLogin(registration: String, password: String): User =
}