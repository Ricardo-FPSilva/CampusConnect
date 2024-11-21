package com.app.campusconnect.data

import android.util.Log
import com.app.campusconnect.data.repository.DashboardRepository
import com.app.campusconnect.data.repository.NetworkDashboardRepository
import com.app.campusconnect.network.DashboardApiService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface AppContainer {
//    val authRepository: AuthRepository
    val dashboardRepository: DashboardRepository
}

class DefaultAppContainer : AppContainer {

    private val baseUrl = "http://192.168.10.12:8080"

    private val gson: Gson = GsonBuilder()
        .setLenient()
        .create()


    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(gson))
        .baseUrl(baseUrl)
        .build()

    private val retrofitService: DashboardApiService by lazy {
        retrofit.create(DashboardApiService::class.java)
    }

    override val dashboardRepository: DashboardRepository by lazy {
        NetworkDashboardRepository(retrofitService)
    }

//    private val authRetrofitService: AuthApiService by lazy {
//        retrofit.create(AuthApiService::class.java)
//    }
//
//    override val authRepository: AuthRepository by lazy {
//        NetworkAuthRepository(authRetrofitService)
//    }


}