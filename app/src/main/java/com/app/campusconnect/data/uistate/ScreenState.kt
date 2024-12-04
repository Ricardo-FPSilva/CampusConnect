package com.app.campusconnect.data.uistate

import com.app.campusconnect.data.uistate.common.UiState

data class ScreenState<out T>(
    val formState: T,
    val uiState: UiState
)
