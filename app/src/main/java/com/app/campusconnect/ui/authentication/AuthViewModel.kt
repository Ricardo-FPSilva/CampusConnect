package com.app.campusconnect.ui.authentication

import android.accounts.NetworkErrorException
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
import java.util.concurrent.TimeoutException
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

    private suspend fun fetchUser(): AuthUiState {
        return try {
            val email = authFormState.value.email
            val password = authFormState.value.password

            val loginResponse = authRepository.login(LoginRequest(email = email,password = password))
            dataStoreManager.storeToken(loginResponse.token)

            Log.d("AuthViewModel", "Login bem-sucedido")

            updateLoginStatus(true)
            AuthUiState.Success()
        } catch (e: HttpException) {

            if (e.code() == 401) { // Unauthorized
                Log.e("AuthViewModel", "Erro de autenticação", e)
                updateLoginStatus(false)
                AuthUiState.Error("Usuário ou senha inválidos")
            } else {
                Log.e("AuthViewModel", "Erro na requisição", e)
                updateLoginStatus(false)
                AuthUiState.Error("Erro ao fazer login: ${e.message}")
            }
        } catch (e: NetworkErrorException) {
            Log.e("AuthViewModel", "Sem conexão com a internet", e)
            updateLoginStatus(false)
            AuthUiState.Error("Sem conexão com a internet")
        } catch (e: TimeoutException) {
            Log.e("AuthViewModel", "Tempo limite excedido", e)
            updateLoginStatus(false)
            AuthUiState.Error("Tempo limite excedido")
        } catch (e: Exception) {
            Log.e("AuthViewModel", "Erro ao fazer login", e)
            updateLoginStatus(false)
            AuthUiState.Error("Erro ao fazer login: ${e.message}")
        }
    }

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
}