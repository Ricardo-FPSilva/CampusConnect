package com.app.campusconnect.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Event(
    @DrawableRes val imageResourceId: Int,
    @StringRes val title: Int,
)
