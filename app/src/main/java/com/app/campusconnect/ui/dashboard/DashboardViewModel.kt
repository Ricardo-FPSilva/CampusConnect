package com.app.campusconnect.ui.dashboard

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.campusconnect.data.repository.dashboard.DashboardRepository
import com.app.campusconnect.data.uistate.dashboard.DashboardFormState
import com.app.campusconnect.data.uistate.dashboard.DashboardUiState
import com.app.campusconnect.data.uistate.models.ErrorType
import com.app.campusconnect.network.dashboard.models.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val dashboardRepository: DashboardRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow<DashboardUiState>(DashboardUiState.Loading)
    val uiState: StateFlow<DashboardUiState> = _uiState.asStateFlow()

    private val _dashboardFormState = MutableStateFlow(DashboardFormState())
    val dashboardFormState: StateFlow<DashboardFormState> = _dashboardFormState.asStateFlow()

    init {
        getEventsEnrolled()
        getEventsList()
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

    fun eventUnregistration(id: Int) {
        viewModelScope.launch {
            _uiState.value = DashboardUiState.Loading
            _uiState.value = submitEventUnregistration(id)
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
        return runCatching {
            val events = dashboardRepository.getEvents()
            _dashboardFormState.update { it.copy(eventList = events) }
            DashboardUiState.Success()
        }.getOrElse { e ->
            Log.e("DashboardViewModel", "Error fetching events", e)
            when (e) {
                is IOException -> DashboardUiState.Error(ErrorType.NETWORK, "Erro de rede. Verifique sua conexão.")
                is HttpException -> {
                    when (e.code()) {
                        404 -> DashboardUiState.Error(ErrorType.SERVER, "Eventos não encontrados.")
                        else -> DashboardUiState.Error(ErrorType.SERVER, "Erro do servidor (${e.code()}).")
                    }
                }
                else -> DashboardUiState.Error(ErrorType.UNKNOWN, "Erro desconhecido.")
            }

        }
    }

    private suspend fun fetchEventsEnrolled(): DashboardUiState {
        return runCatching {
            val events = dashboardRepository.getEventsEnrolled()
            _dashboardFormState.update { it.copy(eventListEnrolled = events) }
            DashboardUiState.Success()
        }.getOrElse { e ->
            Log.e("DashboardViewModel", "Error fetching enrolled events", e)
            when (e) {
                is IOException -> DashboardUiState.Error(ErrorType.NETWORK, "Erro de rede. Verifique sua conexão.")
                else -> DashboardUiState.Error(ErrorType.UNKNOWN, "Erro ao buscar eventos inscritos.")
            }
        }
    }

    private suspend fun submitEventRegistration(id: Int): DashboardUiState {
        return runCatching {
            dashboardRepository.eventRegistration(id = id)
            getEventsEnrolled() // Atualiza a lista de eventos inscritos após o registro
            DashboardUiState.Success()
        }.getOrElse { e ->
            Log.e("DashboardViewModel", "Error registering for event", e)
            when (e) {
                is IOException -> DashboardUiState.Error(ErrorType.NETWORK, "Erro de rede. Verifique sua conexão.")
                else -> DashboardUiState.Error(ErrorType.UNKNOWN, "Erro ao se registrar no evento.")
            }
        }
    }

    private suspend fun submitEventUnregistration(id: Int): DashboardUiState {
        return runCatching {
            dashboardRepository.eventUnregistration(id = id)
            getEventsEnrolled() // Atualiza a lista de eventos inscritos após o registro
            DashboardUiState.Success()
        }.getOrElse { e ->
            Log.e("DashboardViewModel", "Error registering for event", e)
            when (e) {
                is IOException -> DashboardUiState.Error(ErrorType.NETWORK, "Erro de rede. Verifique sua conexão.")
                else -> DashboardUiState.Error(ErrorType.UNKNOWN, "Erro ao se registrar no evento.")
            }
        }
    }

}
