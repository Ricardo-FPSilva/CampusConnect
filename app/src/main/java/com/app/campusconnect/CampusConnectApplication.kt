package com.app.campusconnect

import android.app.Application
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class CampusConnectApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}