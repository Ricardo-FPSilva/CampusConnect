package com.app.campusconnect.network.authentication

import com.app.campusconnect.data.datastore.DataStoreManager
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

private const val API_BASE_URL = "http://192.168.10.10:8080/api"

class AuthInterceptor @Inject constructor(
    private val dataStoreManager: DataStoreManager
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val url = request.url.toString()

        if (url.startsWith(API_BASE_URL)) {
            val token = runBlocking { dataStoreManager.getToken().first() }
            val newRequest = request.newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .build()
            return chain.proceed(newRequest)
        }

        return chain.proceed(request)
    }
}