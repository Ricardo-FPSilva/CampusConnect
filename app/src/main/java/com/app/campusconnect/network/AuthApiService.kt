package com.app.campusconnect.network

import retrofit2.http.POST

interface AuthApiService {
    @POST("auth/login")
    suspend fun getLogin(registration: String, password: String): User
}