package com.app.campusconnect.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class CampusConnectViewModel: ViewModel() {

    private val _uiState = MutableStateFlow(CampusConnectViewModel())
    val uiState: StateFlow<CampusConnectViewModel> = _uiState.asStateFlow()



}