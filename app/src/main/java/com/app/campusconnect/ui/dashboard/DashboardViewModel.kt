package com.app.campusconnect.ui.dashboard

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.app.campusconnect.CampusConnectApplication
import com.app.campusconnect.data.repository.DashboardRepository
import com.app.campusconnect.data.uistate.DashboardUiState
import com.app.campusconnect.network.Event
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.HttpException


class DashboardViewModel(
    private val dashboardRepository: DashboardRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<DashboardUiState>(DashboardUiState.Loading)
    val uiState: StateFlow<DashboardUiState> = _uiState.asStateFlow()

    private val _selectedEvent = MutableStateFlow<Event?>(null)
    val selectedEvent: StateFlow<Event?> = _selectedEvent.asStateFlow()

    init {
        getEventsList()
    }

    private suspend fun fetchEvents(): DashboardUiState {
        return try {
            DashboardUiState.Success(dashboardRepository.getEvents())
        } catch (e: IOException) {
            Log.e("DashboardViewModel", "Network error fetching events", e)
            DashboardUiState.Error("Network error")
        } catch (e: HttpException) {
            Log.e("DashboardViewModel", "HTTP error fetching events", e)
            when (e.code()) {
                404 -> DashboardUiState.Error("Events not found")
                else -> DashboardUiState.Error("Server error")
            }
        } catch (e: Exception) {
            Log.e("DashboardViewModel", "Error fetching events", e)
            DashboardUiState.Error("Unknown error")
        }
    }
    internal fun getEventsList() {
        viewModelScope.launch {
            _uiState.value = DashboardUiState.Loading
            _uiState.value = fetchEvents()
        }
    }
    fun setSelectedEvent(event: Event) {
        _selectedEvent.update { event }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as CampusConnectApplication)
                val dashboardRepository = application.container.dashboardRepository
                DashboardViewModel(dashboardRepository = dashboardRepository)
            }
        }
    }
}