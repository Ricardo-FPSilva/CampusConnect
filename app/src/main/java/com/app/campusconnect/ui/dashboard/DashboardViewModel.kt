package com.app.campusconnect.ui.dashboard

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.campusconnect.data.datastore.DataStoreManager
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
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val dashboardRepository: DashboardRepository,
    private val dataStoreManager: DataStoreManager
) : ViewModel() {


    private val _dashboardState = MutableStateFlow(
        ScreenState(
            formState = DashboardFormState(),
            uiState = UiState.Loading
        )
    )
    val dashboardState: StateFlow<ScreenState<DashboardFormState>> = _dashboardState.asStateFlow()



    init {
        getEventsList()
        getEventsEnrolled()
        getUserProfile()
    }

    fun getEventsList() {
        viewModelScope.launch {
            _dashboardState.update { it.copy(uiState = UiState.Loading) }
            _dashboardState.update { it.copy(uiState = fetchEvents()) }
        }
    }

    fun getEventsEnrolled() {
        viewModelScope.launch {
            _dashboardState.update { it.copy(uiState = UiState.Loading) }
            _dashboardState.update { it.copy(uiState = fetchEventsEnrolled()) }
        }
    }

    private fun getUserProfile() {
        viewModelScope.launch {
            _dashboardState.update { it.copy(uiState = UiState.Loading) }
            _dashboardState.update { it.copy(uiState = recoverUser()) }
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

    fun subscribeEvent(id: Int) {
        viewModelScope.launch {
            Log.d("DashboardViewModel", "Subscribing to event with ID: $id")
            _dashboardState.update { it.copy(uiState = UiState.Loading) }
            _dashboardState.update { it.copy(uiState = submitSubscribeEvent(id)) }
            _dashboardState.update { it.copy(formState = it.formState.copy(isSubscribed = true)) }
            getEventsEnrolled()
        }
    }

    fun unsubscribeEvent(id: Int) {
        viewModelScope.launch {
            Log.d("DashboardViewModel", "Unsubscribing from event with ID: $id")
            _dashboardState.update { it.copy(uiState = UiState.Loading) }
            _dashboardState.update { it.copy(uiState = submitUnsubscribeEvent(id)) }
            _dashboardState.update { it.copy(formState = it.formState.copy(isSubscribed = false)) }
            getEventsEnrolled()
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
                    ErrorType.NETWORK, "Erro de rede. Verifique sua conexão."
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
            _dashboardState.update { it.copy(
                formState = it.formState.copy(
                    eventsListEnrolled = dashboardRepository.getEventsEnrolled()
                )
            ) }
            UiState.Success
        }.onFailure {
            Log.e("DashboardViewModel", "Error fetching enrolled events", it)
            UiState.Success
        }.getOrElse { e ->
            Log.e("DashboardViewModel", "Error fetching enrolled events", e)
            when (e) {
                is IOException -> UiState.Error(
                    ErrorType.NETWORK, "Erro de rede. Verifique sua conexão."
                )
                is HttpException -> {
                    when (e.code()) {
                        400 -> UiState.Success
                        404 -> UiState.Error(
                            ErrorType.SERVER, "Eventos inscritos não encontrados."
                        )
                        else -> UiState.Error(
                            ErrorType.SERVER, "Erro do servidor (${e.code()})."
                        )
                    }
                }
                else -> UiState.Error(ErrorType.UNKNOWN, "Erro ao buscar eventos inscritos.")
            }
        }
    }

    private suspend fun recoverUser() : UiState{
        return runCatching {
            val user = dataStoreManager.getUser().firstOrNull()
            Log.d("DashboardViewModel", "User: $user")
            user
        }.getOrElse {
            Log.e("DashboardViewModel", "Error recovering user", it)
            null
        }.let { user ->
            if (user != null) {
                _dashboardState.update {
                    it.copy(
                        formState = it.formState.copy(
                            user = user
                        )
                    )
                }
                when (user.role) {
                    "STUDENT" -> _dashboardState.update {
                        it.copy(formState = it.formState.copy(user = it.formState.user?.copy(role = "Student")))
                    }
                    "TEACHER" -> _dashboardState.update {
                        it.copy(formState = it.formState.copy(user = it.formState.user?.copy(role = "Teacher")))
                    }
                    "INSTITUTE" -> _dashboardState.update {
                        it.copy(formState = it.formState.copy(user = it.formState.user?.copy(role = "Institute")))
                    }
                }
                UiState.Success
            } else {
                UiState.Error(ErrorType.UNKNOWN, "Erro ao recuperar usuário.")
            }
        }
    }

    private suspend fun submitSubscribeEvent(id: Int): UiState {
        return runCatching {
            dashboardRepository.subscribeEvent(id = id)
            Log.d("DashboardViewModel", "Event subscribed successfully")
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

    private suspend fun submitUnsubscribeEvent(id: Int): UiState {
        return runCatching {
            dashboardRepository.unsubscribeEvent(id = id)
            Log.d("DashboardViewModel", "Event unsubscribed successfully")
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
