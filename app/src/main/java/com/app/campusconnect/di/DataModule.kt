package com.app.campusconnect.di

import android.content.Context
import com.app.campusconnect.data.datastore.DataStoreManager
import com.app.campusconnect.data.repository.AuthRepository
import com.app.campusconnect.data.repository.DashboardRepository
import com.app.campusconnect.data.repository.NetworkAuthRepository
import com.app.campusconnect.data.repository.NetworkDashboardRepository
import com.app.campusconnect.network.AuthApiService
import com.app.campusconnect.network.AuthInterceptor
import com.app.campusconnect.network.DashboardApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideRetrofit(authInterceptor: AuthInterceptor): Retrofit {
        val client = OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl("http://192.168.10.2:8080")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideAuthApiService(retrofit: Retrofit): AuthApiService {
        return retrofit.create(AuthApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideDashboardApiService(retrofit: Retrofit): DashboardApiService {
        return retrofit.create(DashboardApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideAuthRepository(authApiService: AuthApiService): AuthRepository {
        return NetworkAuthRepository(authApiService)
    }

    @Provides
    @Singleton
    fun provideDashboardRepository(dashboardApiService: DashboardApiService): DashboardRepository {
        return NetworkDashboardRepository(dashboardApiService)
    }

    @Provides
    @Singleton
    fun provideDataStoreManager(@ApplicationContext context: Context): DataStoreManager {
        return DataStoreManager(context)
    }


}