package com.app.campusconnect.ui.dashboard

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class DashboardViewModel: ViewModel() {

    private val _uiState = MutableStateFlow(DashboardViewModel())
    val uiState: StateFlow<DashboardViewModel> = _uiState.asStateFlow()



}