package com.app.campusconnect.ui.authentication

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.campusconnect.data.datastore.DataStoreManager
import com.app.campusconnect.data.repository.authentication.AuthRepository
import com.app.campusconnect.data.uistate.authentication.AuthFormState
import com.app.campusconnect.data.uistate.authentication.AuthUiState
import com.app.campusconnect.network.authentication.models.LoginRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val dataStoreManager: DataStoreManager
) : ViewModel() {

    private val _uiState = MutableStateFlow<AuthUiState>(AuthUiState.Success())
    val uiState: StateFlow<AuthUiState> = _uiState.asStateFlow()

    private val _authFormState = MutableStateFlow(AuthFormState())
    val authFormState = _authFormState.asStateFlow()


    fun authenticateUser() {
        viewModelScope.launch {
            _uiState.value = AuthUiState.Loading
            _uiState.value = fetchUser()
        }
    }

    private fun updateLoginStatus(isLoggedIn: Boolean) {
        viewModelScope.launch {
            _authFormState.emit(authFormState.value.copy(isUserLoggedIn = isLoggedIn))
        }
    }

    fun updateAuthFormState(updatedState: AuthFormState) {
        viewModelScope.launch {
            _authFormState.emit(updatedState)
        }
    }


    fun updateUiState(newState: AuthUiState) {
        viewModelScope.launch {
            _uiState.emit(newState)
        }
    }

    private suspend fun fetchUser(): AuthUiState {
        return runCatching {
            val registrationNumber = authFormState.value.email
            val password = authFormState.value.password

            val loginResponse = authRepository.login(LoginRequest(email = registrationNumber, password = password))
            dataStoreManager.storeToken(loginResponse.token)

            Log.d("AuthViewModel", "Login bem-sucedido")

            updateLoginStatus(true)
            AuthUiState.Success()
        }.getOrElse { e ->
            Log.e("AuthViewModel", "Erro ao fazer login", e)
            updateLoginStatus(false)
            when (e) {
                is HttpException -> {
                    if (e.code() == 401) { // Unauthorized
                        AuthUiState.Error("Usuário ou senha inválidos")
                    } else {
                        AuthUiState.Error("Erro na requisição: ${e.code()}")
                    }
                }
                is IOException -> AuthUiState.Error("Sem conexão com a internet")
                else -> AuthUiState.Error("Erro ao fazer login: ${e.message}")
            }
        }
    }
}