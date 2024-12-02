package com.app.campusconnect.network.authentication

import com.app.campusconnect.data.datastore.DataStoreManager
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val dataStoreManager: DataStoreManager
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val url = request.url.toString()

        // Verifica se a URL começa com a URL base do DashboardApiService
        if (url.startsWith("http://192.168.10.6:8080/api")) {
            val token = runBlocking { dataStoreManager.getToken().first() }

            val newRequest = request.newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .build()
            return chain.proceed(newRequest)
        }

        // Se não for uma requisição para o DashboardApiService, continua sem o token
        return chain.proceed(request)
    }
}