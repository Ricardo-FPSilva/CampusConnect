package com.app.campusconnect.network

import retrofit2.http.GET

interface CampusConnectApiService {
    @GET("login")
    suspend fun getLogin(registration: String, password: String): Result<String>

}