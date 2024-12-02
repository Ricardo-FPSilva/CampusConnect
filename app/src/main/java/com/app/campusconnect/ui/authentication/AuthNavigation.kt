package com.app.campusconnect.ui.authentication

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.app.campusconnect.R
import com.app.campusconnect.data.uistate.authentication.AuthUiState
import com.app.campusconnect.ui.authentication.components.AuthAppBar

enum class AuthScreen (@StringRes val title: Int){
    Welcome(title = R.string.app_name),
    Registration(title = R.string.registration),
    EmailCode(title = R.string.verification_code),
    NewPassword(title = R.string.new_password),
    EnterProfile(title = R.string.access),
}

@Composable
fun AuthNavHost(
    viewModel: AuthViewModel = viewModel(),
    navController: NavHostController = rememberNavController(),
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = AuthScreen.valueOf(
        backStackEntry?.destination?.route ?: AuthScreen.Welcome.name
    )

    val authFormState by viewModel.authFormState.collectAsState()
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            AuthAppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        },
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = AuthScreen.Welcome.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = AuthScreen.Welcome.name) {
                WelcomeScreen(
                    onStartedButtonClick = { navController.navigate(AuthScreen.Registration.name) },
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(dimensionResource(id = R.dimen.padding_medium))
                )
            }
            composable(route = AuthScreen.Registration.name) {
                RegistrationScreen(
                    authFormState = authFormState,
                    onSendButtonClick = { navController.navigate(AuthScreen.EmailCode.name) },
                    onValueChange = { updatedState ->
                        viewModel.updateAuthFormState(updatedState)
                    },
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(dimensionResource(id = R.dimen.padding_medium))
                )
            }
            composable(route = AuthScreen.EmailCode.name) {
                EmailCodeScreen(
                    authFormState = authFormState,
                    onVerifyClick = { navController.navigate(AuthScreen.NewPassword.name) },
                    onValueChange = { updatedState ->
                        viewModel.updateAuthFormState(updatedState)
                    },
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(dimensionResource(id = R.dimen.padding_medium))
                )
            }
            composable(route = AuthScreen.NewPassword.name) {
                NewPasswordScreen(
                    authFormState = authFormState,
                    onSendClick = { navController.navigate(AuthScreen.EnterProfile.name) },
                    onValueChange = { updatedState ->
                        viewModel.updateAuthFormState(updatedState)
                    },
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(dimensionResource(id = R.dimen.padding_medium))
                )
            }
            composable(route = AuthScreen.EnterProfile.name) {
                EnterProfileScreen(
                    authUiState = uiState, // Passando o uiState
                    authFormState = authFormState,
                    onAccessClick = { viewModel.authenticateUser() },
                    onValueChange = { updatedState ->
                        viewModel.updateAuthFormState(updatedState)
                    },
                    retryAction = { viewModel.updateUiState(AuthUiState.Success()) }, // Função para tentar novamente
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(dimensionResource(id = R.dimen.padding_medium))
                )
            }
        }
    }
}