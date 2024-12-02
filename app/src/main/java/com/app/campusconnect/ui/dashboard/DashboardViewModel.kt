package com.app.campusconnect.ui.dashboard

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.campusconnect.data.datastore.DataStoreManager
import com.app.campusconnect.data.repository.dashboard.DashboardRepository
import com.app.campusconnect.data.uistate.dashboard.DashboardFormState
import com.app.campusconnect.data.uistate.dashboard.DashboardUiState
import com.app.campusconnect.network.dashboard.models.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject
import retrofit2.HttpException

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val dashboardRepository: DashboardRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow<DashboardUiState>(DashboardUiState.Loading)
    val uiState: StateFlow<DashboardUiState> = _uiState.asStateFlow()

    private val _dashboardFormState = MutableStateFlow(DashboardFormState())
    val dashboardFormState: StateFlow<DashboardFormState> = _dashboardFormState.asStateFlow()

    init {
        getEventsList()
        getEventsEnrolled()
    }
    fun getEventsList() {
        viewModelScope.launch {
            _uiState.value = DashboardUiState.Loading
            _uiState.value = fetchEvents()
        }
    }

    fun getEventsEnrolled() {
        viewModelScope.launch {
            _uiState.value = DashboardUiState.Loading
            _uiState.value = fetchEventsEnrolled()
        }
    }
    fun eventRegistration(id: Int) {
        viewModelScope.launch {
            _uiState.value = DashboardUiState.Loading
            _uiState.value = submitEventRegistration(id)
        }
    }
    fun setSelectedEvent(event: Event) {
        _dashboardFormState.update { it.copy(selectedEvent = event) }
    }

    fun updateSelectedMyEvents(isSelected: Boolean) {
        _dashboardFormState.update { it.copy(isSelectedMyEvents = isSelected) }
    }

    fun updateDashboardFormState(updatedState: DashboardFormState) {
        viewModelScope.launch {
            _dashboardFormState.emit(updatedState)
        }
    }
    fun performSearch(searchTerm: String) {
        viewModelScope.launch {
            val filteredEvents = dashboardFormState.value.eventList.filter { event ->
                event.title.contains(searchTerm, ignoreCase = true) ||
                        event.description.contains(searchTerm, ignoreCase = true)
            }
            _dashboardFormState.update { it.copy(eventListFiltered = filteredEvents) }
        }
    }
    private suspend fun fetchEvents(): DashboardUiState {
        return try {
            val events = dashboardRepository.getEvents()
            _dashboardFormState.update { it.copy(eventList = events) }
            DashboardUiState.Success()
        } catch (e: IOException) {
            Log.e("DashboardViewModel", "Network error fetching events", e)
            DashboardUiState.Error("Erro de rede. Verifique sua conexão.")
        } catch (e: HttpException) {
            Log.e("DashboardViewModel", "HTTP error fetching events", e)
            when (e.code()) {
                404 -> DashboardUiState.Error("Eventos não encontrados.")
                else -> DashboardUiState.Error("Erro do servidor.")
            }
        } catch (e: Exception) {
            Log.e("DashboardViewModel", "Error fetching events", e)
            DashboardUiState.Error("Erro desconhecido.")
        }
    }
    private suspend fun fetchEventsEnrolled(): DashboardUiState {
        return try {

            val events = dashboardRepository.getEventsEnrolled()

            _dashboardFormState.update { it.copy(eventListEnrolled = events) }
            DashboardUiState.Success()

        } catch (e: IOException) {
            Log.e("DashboardViewModel", "Network error fetching events", e)

            DashboardUiState.Error("Erro de rede. Verifique sua conexão.")
        } catch (e: Exception){
            Log.e("DashboardViewModel", "Error fetching events", e)

            DashboardUiState.Error("Erro desconhecido.")
        }

    }
    private suspend fun submitEventRegistration(id: Int): DashboardUiState {
        return try {
            dashboardRepository.eventRegistration(id = id)
            getEventsEnrolled()
            DashboardUiState.Success()
        } catch (e: IOException) {
            Log.e("DashboardViewModel", "Network error submitting event registration", e)
            DashboardUiState.Error("Erro de rede. Verifique sua conexão.")
        }
    }
}
