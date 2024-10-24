package com.app.campusconnect.ui

import androidx.lifecycle.ViewModel
import com.app.campusconnect.data.CampusConnectUiState
import com.app.campusconnect.data.DataSource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CampusConnectViewModel: ViewModel() {

    private val _uiState = MutableStateFlow(CampusConnectViewModel())
    val uiState: StateFlow<CampusConnectViewModel> = _uiState.asStateFlow()



}