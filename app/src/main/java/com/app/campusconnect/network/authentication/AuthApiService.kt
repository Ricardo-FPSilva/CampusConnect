package com.app.campusconnect.network.authentication

import com.app.campusconnect.models.authentication.LoginRequest
import com.app.campusconnect.models.authentication.LoginResponse
import com.app.campusconnect.models.authentication.ProfileLoginResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface AuthApiService {
    @GET("auth/login/{credential}")
    suspend fun getProfileLogin(@Path("credential") login: String): ProfileLoginResponse

    @POST("auth/login")
    suspend fun login(@Body loginRequest: LoginRequest): LoginResponse
}