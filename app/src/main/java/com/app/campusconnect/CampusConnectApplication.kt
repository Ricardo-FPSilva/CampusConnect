package com.app.campusconnect

import android.app.Application
import com.app.campusconnect.data.AppContainer

class CampusConnectApplication : Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = AppContainer()
    }
}