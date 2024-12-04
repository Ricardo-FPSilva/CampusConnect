package com.app.campusconnect.ui.navigation.authentication

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
import com.app.campusconnect.data.uistate.common.UiState
import com.app.campusconnect.ui.authentication.components.AuthAppBar
import com.app.campusconnect.models.authentication.AuthScreen
import com.app.campusconnect.ui.authentication.AuthViewModel
import com.app.campusconnect.ui.authentication.EmailCodeScreen
import com.app.campusconnect.ui.authentication.EnterProfileScreen
import com.app.campusconnect.ui.authentication.NewPasswordScreen
import com.app.campusconnect.ui.authentication.RegistrationScreen
import com.app.campusconnect.ui.authentication.WelcomeScreen

@Composable
fun AuthNavigation(
    viewModel: AuthViewModel = viewModel(),
    navController: NavHostController = rememberNavController(),
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = backStackEntry?.destination?.route?.let {
        AuthScreen.valueOf(it)
    } ?: AuthScreen.Welcome

    val authState by viewModel.authState.collectAsState()

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
                    authFormState = authState.formState,
                    onSendButtonClick = { navController.navigate(AuthScreen.EnterProfile.name) },
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
                    authFormState = authState.formState,
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
                    authFormState = authState.formState,
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
                    uiState = authState.uiState, // Passando o uiState
                    authFormState = authState.formState,
                    onAccessClick = { viewModel.authenticateUser() },
                    onValueChange = { updatedState ->
                        viewModel.updateAuthFormState(updatedState)
                    },
                    retryAction = { viewModel.updateUiState(UiState.Success) }, // Função para tentar novamente
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(dimensionResource(id = R.dimen.padding_medium))
                )
            }
        }
    }
}