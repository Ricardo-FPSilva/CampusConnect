package com.app.campusconnect.data

import com.app.campusconnect.data.repository.AuthRepository
import com.app.campusconnect.data.repository.NetworkAuthRepository
import com.app.campusconnect.network.CampusConnectApiService
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface AppContainer {
    val authRepository: AuthRepository
}

class DefaultAppContainer : AppContainer {

    private val baseUrl = "https://api.example.com/"

    private val gson = GsonBuilder()
        .setLenient()
        .create()

    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    private val retrofitService: CampusConnectApiService by lazy {
        retrofit.create(CampusConnectApiService::class.java)
    }

    override val authRepository: AuthRepository by lazy {
        NetworkAuthRepository(retrofitService)
    }

}