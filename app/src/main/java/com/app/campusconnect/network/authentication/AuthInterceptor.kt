package com.app.campusconnect.network.authentication

import com.app.campusconnect.data.datastore.DataStoreManager
import com.app.campusconnect.util.Constants
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val dataStoreManager: DataStoreManager
) : Interceptor {

    private val urlCompleta = "${Constants.URL_BASE}/api"

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val url = request.url.toString()

        if (url.startsWith(urlCompleta)) {
            val token = runBlocking { dataStoreManager.getToken().first() }
            val newRequest = request.newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .build()
            return chain.proceed(newRequest)
        }

        return chain.proceed(request)
    }
}