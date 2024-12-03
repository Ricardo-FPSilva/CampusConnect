package com.app.campusconnect.ui.dashboard.models

import androidx.annotation.StringRes
import com.app.campusconnect.R

enum class DashboardScreen (@StringRes val title: Int){
    Home(title = R.string.home),
    EventDetails(title = R.string.event_details),
    EventCreation(title = R.string.event_creation),
    MyEvents(title = R.string.my_events),
    Search(title = R.string.search)
}