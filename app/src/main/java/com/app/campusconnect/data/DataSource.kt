package com.app.campusconnect.data

import com.app.campusconnect.R

object DataSource {
    fun loadEvents(): List<Event> {
        return listOf(
            Event(R.drawable.campus_connect_logo, R.string.app_name),
            Event(R.drawable.campus_connect_logo, R.string.app_name),
            Event(R.drawable.campus_connect_logo, R.string.app_name),
            Event(R.drawable.campus_connect_logo, R.string.app_name),
            Event(R.drawable.campus_connect_logo, R.string.app_name),
            Event(R.drawable.campus_connect_logo, R.string.app_name),
            Event(R.drawable.campus_connect_logo, R.string.app_name),
            Event(R.drawable.campus_connect_logo, R.string.app_name),
            Event(R.drawable.campus_connect_logo, R.string.app_name),
            Event(R.drawable.campus_connect_logo, R.string.app_name),
        )
    }
}