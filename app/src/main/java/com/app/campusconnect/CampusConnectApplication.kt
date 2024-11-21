package com.app.campusconnect

import android.app.Application
import com.app.campusconnect.data.AppContainer
import com.app.campusconnect.data.DefaultAppContainer

class CampusConnectApplication : Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}