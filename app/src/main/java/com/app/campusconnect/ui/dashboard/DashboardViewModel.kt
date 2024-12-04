package com.app.campusconnect.ui.dashboard

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.campusconnect.data.uistate.ScreenState
import com.app.campusconnect.data.uistate.common.UiState
import com.app.campusconnect.data.uistate.dashboard.DashboardFormState
import com.app.campusconnect.domain.dashboard.DashboardRepository
import com.app.campusconnect.models.common.ErrorType
import com.app.campusconnect.models.dashboard.Event
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

    private val _dashboardState = MutableStateFlow(
        ScreenState(
            formState = DashboardFormState(),
            uiState = UiState.Loading
        )
    )
    val dashboardState: StateFlow<ScreenState<DashboardFormState>> = _dashboardState.asStateFlow()

    init {
        getEventsEnrolled()
        getEventsList()
    }

    fun getEventsList() {
        viewModelScope.launch {
            _dashboardState.update { it.copy(uiState = UiState.Loading) }
            val result =  fetchEvents()
            _dashboardState.update { it.copy(uiState = result) }
        }
    }

    fun getEventsEnrolled() {
        viewModelScope.launch {
            _dashboardState.update { it.copy(uiState = UiState.Loading) }
            val result = fetchEventsEnrolled()
            _dashboardState.update { it.copy(uiState = result) }
        }
    }

    fun setSelectedEvent(event: Event) {
        _dashboardState.update { it.copy(
            formState = it.formState.copy(
                selectedEvent = event
            )
        ) }
    }

    fun updateSelectedMyEvents(isSelected: Boolean) {
        _dashboardState.update { it.copy(
            formState = it.formState.copy(
                isSelectedMyEvents = isSelected
            )
        ) }
    }
    fun updateDashboardFormState(updatedState: DashboardFormState) {
        _dashboardState.update { it.copy(formState = updatedState) }
    }

    fun performSearch(searchTerm: String) {
        viewModelScope.launch {
            val filteredEvents = _dashboardState.value.formState.eventsList.filter { event ->
                event.title.contains(searchTerm, ignoreCase = true) ||
                        event.description.contains(searchTerm, ignoreCase = true)
            }
            _dashboardState.update { it.copy(
                formState = it.formState.copy(
                    eventsListFiltered = filteredEvents
                )
            ) }
        }
    }

    fun eventRegistration(id: Int) {
        viewModelScope.launch {
            _dashboardState.update { it.copy(uiState = UiState.Loading) }
            _dashboardState.update { it.copy(uiState = submitEventRegistration(id)) }
        }
    }

    fun eventUnregistration(id: Int) {
        viewModelScope.launch {
            _dashboardState.update { it.copy(uiState = UiState.Loading) }
            _dashboardState.update { it.copy(uiState = submitEventUnregistration(id)) }
        }
    }

    private suspend fun fetchEvents(): UiState {
        return runCatching {
            _dashboardState.update { it.copy(
                formState = it.formState.copy(
                    eventsList = dashboardRepository.getEvents()
                )
            ) }
            UiState.Success
        }.getOrElse { e ->
            Log.e("DashboardViewModel", "Error fetching events", e)
            when (e) {
                is IOException -> UiState.Error(
                    ErrorType.NETWORK,
                    "Erro de rede. Verifique sua conexão."
                )
                is HttpException -> {
                    when (e.code()) {
                        404 -> UiState.Error(ErrorType.SERVER, "Eventos não encontrados.")
                        else -> UiState.Error(
                            ErrorType.SERVER,
                            "Erro do servidor (${e.code()})."
                        )
                    }
                }
                else -> UiState.Error(ErrorType.UNKNOWN, "Erro desconhecido.")
            }
        }
    }

    private suspend fun fetchEventsEnrolled(): UiState {
        return runCatching {
            val events = dashboardRepository.getEventsEnrolled()
            _dashboardState.update { it.copy(
                formState = it.formState.copy(
                    eventsListEnrolled = events
                )
            ) }
            UiState.Success
        }.getOrElse { e ->
            Log.e("DashboardViewModel", "Error fetching enrolled events", e)
            when (e) {
                is IOException -> UiState.Error(
                    ErrorType.NETWORK,
                    "Erro de rede. Verifique sua conexão."
                )
                is HttpException -> {
                    when (e.code()) {
                        404 -> UiState.Error(
                            ErrorType.SERVER,
                            "Eventos inscritos não encontrados."
                        )
                        else -> UiState.Error(
                            ErrorType.SERVER,
                            "Erro do servidor (${e.code()})."
                        )
                    }
                }
                else -> UiState.Error(ErrorType.UNKNOWN, "Erro ao buscar eventos inscritos.")
            }
        }
    }

    private suspend fun submitEventRegistration(id: Int): UiState {
        return runCatching {
            dashboardRepository.eventRegistration(id = id)
            _dashboardState.update { it.copy(
                formState = it.formState.copy(
                    eventsListEnrolled = dashboardRepository.getEventsEnrolled()
                )
            ) }
            UiState.Success
        }.getOrElse { e ->
            Log.e("DashboardViewModel", "Error registering for event", e)
            when (e) {
                is IOException -> UiState.Error(
                    ErrorType.NETWORK,
                    "Erro de rede. Verifique sua conexão."
                )
                is HttpException -> {
                    when (e.code()) {
                        400 -> UiState.Error(
                            ErrorType.SERVER,
                            "Erro na requisição: ${e.message}"
                        )
                        409 -> UiState.Error(
                            ErrorType.SERVER,
                            "Você já está inscrito neste evento."
                        )
                        else -> UiState.Error(
                            ErrorType.SERVER,
                            "Erro do servidor (${e.code()})."
                        )
                    }
                }
                else -> UiState.Error(ErrorType.UNKNOWN, "Erro ao se registrar no evento.")
            }
        }
    }

    private suspend fun submitEventUnregistration(id: Int): UiState {
        return runCatching {
            dashboardRepository.eventUnregistration(id = id)
            _dashboardState.update { it.copy(
                formState = it.formState.copy(
                    eventsListEnrolled = dashboardRepository.getEventsEnrolled()
                )
            ) }
            UiState.Success
        }.getOrElse { e ->
            Log.e("DashboardViewModel", "Error unregistering for event", e)
            when (e) {
                is IOException -> UiState.Error(
                    ErrorType.NETWORK,
                    "Erro de rede. Verifique sua conexão."
                )
                is HttpException -> {
                    when (e.code()) {
                        400 -> UiState.Error(
                            ErrorType.SERVER,
                            "Erro na requisição: ${e.message}"
                        )
                        404 -> UiState.Error(ErrorType.SERVER, "Inscrição não encontrada.")
                        else -> UiState.Error(
                            ErrorType.SERVER,
                            "Erro do servidor (${e.code()})."
                        )
                    }
                }
                else -> UiState.Error(ErrorType.UNKNOWN, "Erro ao se desinscrever do evento.")
            }
        }
    }
}
