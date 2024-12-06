package com.app.campusconnect.ui.authentication

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.campusconnect.data.datastore.DataStoreManager
import com.app.campusconnect.data.uistate.ScreenState
import com.app.campusconnect.data.uistate.authentication.AuthFormState
import com.app.campusconnect.data.uistate.common.UiState
import com.app.campusconnect.domain.authentication.AuthRepository
import com.app.campusconnect.models.authentication.LoginRequest
import com.app.campusconnect.models.common.ErrorType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.singleOrNull
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val dataStoreManager: DataStoreManager
) : ViewModel() {

    private val _authState = MutableStateFlow(
        ScreenState(
            formState = AuthFormState(),
            uiState = UiState.Success
        )
    )
    val authState: StateFlow<ScreenState<AuthFormState>> = _authState.asStateFlow()

    fun authenticateUser() {
        viewModelScope.launch {
            _authState.update { it.copy(uiState = UiState.Loading) }
            _authState.update { it.copy(uiState = fetchUser()) }
        }
    }

    fun updateUiState(newUiState: UiState) {
        _authState.update { it.copy(uiState = newUiState) }
    }
    fun updateAuthFormState(updatedState: AuthFormState) {
        _authState.update { it.copy(formState = updatedState) }
    }

    private suspend fun fetchUser(): UiState {
        return runCatching {
            val login  = _authState.value.formState.login
            val password = _authState.value.formState.password

            val loginResponse = authRepository.login(
                LoginRequest(
                    login = login,
                    password = password
                )
            )
            dataStoreManager.storeToken(loginResponse.token)
            dataStoreManager.storeUser(loginResponse.user)

            Log.d("AuthViewModel", "User: ${loginResponse.user}")


            _authState.update { it.copy(
                formState = it.formState.copy(
                    isUserLoggedIn = true
                )
            ) }

            Log.d("AuthViewModel", "Login bem-sucedido")

            UiState.Success
        }.getOrElse { e ->
            Log.e("AuthViewModel", "Erro ao fazer login", e)
            when (e) {
                is HttpException -> {
                    if (e.code() == 401) {
                        UiState.Error(ErrorType.SERVER, "Usuário ou senha inválidos") // Usa ErrorType
                    } else {
                        UiState.Error(ErrorType.SERVER, "Erro na requisição: ${e.code()}") // Usa ErrorType
                    }
                }
                is IOException -> UiState.Error(ErrorType.NETWORK, "Sem conexão com a internet") // Usa ErrorType
                else -> UiState.Error(ErrorType.UNKNOWN, "Erro ao fazer login: ${e.message}") // Usa ErrorType
            }
        }
    }
}