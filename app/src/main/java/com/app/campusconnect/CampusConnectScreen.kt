package com.app.campusconnect

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.app.campusconnect.ui.components.LoginAppBar
import com.app.campusconnect.ui.loginScreens.EmailCodeScreen
import com.app.campusconnect.ui.loginScreens.EnterProfileScreen
import com.app.campusconnect.ui.LoginViewModel
import com.app.campusconnect.ui.HomeScreen
import com.app.campusconnect.ui.loginScreens.NewPasswordScreen
import com.app.campusconnect.ui.loginScreens.RegistrationScreen
import com.app.campusconnect.ui.WelcomeScreen
import com.app.campusconnect.ui.components.CampusConnectBottomAppBar
import com.app.campusconnect.ui.components.CampusConnectTopAppBar
import com.app.campusconnect.ui.theme.CampusConnectTheme

enum class CampusConnectScreen (@StringRes val title: Int){
    Welcome(title = R.string.app_name),
    Registration(title = R.string.registration),
    EmailCode(title = R.string.verification_code),
    NewPassword(title = R.string.new_password),
    EnterProfile(title = R.string.access),
    Home(title = R.string.home),
}

@Composable
fun CampusConnectApp(
    viewModel: LoginViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
){
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = CampusConnectScreen.valueOf(
        backStackEntry?.destination?.route ?: CampusConnectScreen.Welcome.name
    )
    Scaffold (
        topBar = {
            if (currentScreen != CampusConnectScreen.Home) {
                LoginAppBar(
                    currentScreen = currentScreen,
                    canNavigateBack = navController.previousBackStackEntry != null,
                    navigateUp = { navController.navigateUp() }
                )
            } else {
                CampusConnectTopAppBar(currentScreen = currentScreen)
            }

        },
        bottomBar = {
            if (currentScreen == CampusConnectScreen.Home)
                CampusConnectBottomAppBar()
        }
    ) { innerPadding ->
        val uiState by viewModel.uiState.collectAsState()

        NavHost(
            navController = navController,
            startDestination = CampusConnectScreen.Home.name,
            modifier = Modifier.padding(innerPadding)
        ) {

            composable(
                route = CampusConnectScreen.Welcome.name,
            ) {
                WelcomeScreen(
                    onStartedButtonClick = {
                        navController.navigate(CampusConnectScreen.Registration.name)
                    },
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(dimensionResource(id = R.dimen.padding_medium))
                )
            }

            composable(
                route = CampusConnectScreen.Registration.name,
            ) {
                RegistrationScreen(
                    loginUiState = uiState,
                    onSendButtonClick = {
                        navController.navigate(CampusConnectScreen.EmailCode.name)
                    },
                    onValueChange = { viewModel.setMatriculate(it) },
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(dimensionResource(id = R.dimen.padding_medium))
                )
            }
            composable(
                route = CampusConnectScreen.EmailCode.name,

            ) {
                EmailCodeScreen(
                    loginUiState = uiState,
                    onVerifyClick = {
                        navController.navigate(CampusConnectScreen.NewPassword.name)
                    },
                    onValueChange = { viewModel.setEmailCode(it) },
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(dimensionResource(id = R.dimen.padding_medium))
                )
            }
            composable(
                route = CampusConnectScreen.NewPassword.name,

            ) {
                NewPasswordScreen(
                    loginUiState = uiState,
                    onSendClick = {
                        navController.navigate(CampusConnectScreen.EnterProfile.name)
                    },
                    onNewPasswordValueChange = { viewModel.setNewPassword(it) },
                    onConfirmNewPasswordValueChange = { viewModel.setConfirmNewPassword(it) },
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(dimensionResource(id = R.dimen.padding_medium))
                )
            }
            composable(
                route = CampusConnectScreen.EnterProfile.name,
            ) {
                EnterProfileScreen(
                    loginUiState = uiState,
                    onAccessClick = {
                    },
                    onValueChange = { viewModel.setPassword(it) },
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(dimensionResource(id = R.dimen.padding_medium))
                )
            }
            composable(
                route = CampusConnectScreen.Home.name,
            ) {
                HomeScreen(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(dimensionResource(id = R.dimen.padding_medium))
                )
            }
        }
    }

}


@Preview(showBackground = true)
@Composable
fun CampusConnectAppLightThemePreview() {
    CampusConnectTheme (darkTheme = false) {
        CampusConnectApp()
    }
}
@Preview(showBackground = true)
@Composable
fun CampusConnectAppDarkThemePreview() {
    CampusConnectTheme (darkTheme = true) {
        CampusConnectApp()
    }
}