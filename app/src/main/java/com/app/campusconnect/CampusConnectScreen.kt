package com.app.campusconnect

import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.app.campusconnect.ui.loginScreens.EmailCodeScreen
import com.app.campusconnect.ui.loginScreens.EnterProfileScreen
import com.app.campusconnect.ui.loginScreens.LoginViewModel
import com.app.campusconnect.ui.loginScreens.NewPasswordScreen
import com.app.campusconnect.ui.loginScreens.RegistrationScreen
import com.app.campusconnect.ui.loginScreens.WelcomeScreen
import com.app.campusconnect.ui.theme.CampusConnectTheme

enum class LoginScreen (@StringRes val title: Int){
    Welcome(title = R.string.app_name),
    Registration(title = R.string.registration),
    EmailCode(title = R.string.verification_code),
    NewPassword(title = R.string.new_password),
    EnterProfile(title = R.string.access)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CampusConnectAppBar(
    currentScreen: LoginScreen,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    LargeTopAppBar(
        title = {
            Text(
                text = stringResource(id = currentScreen.title),
                style = MaterialTheme.typography.displayLarge,
                fontWeight = FontWeight.Bold,
            )
        },
        modifier = modifier
            .padding(dimensionResource(id = R.dimen.padding_small)),
        navigationIcon = {
            IconButton(onClick = navigateUp) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(R.string.back),
                    modifier = Modifier.fillMaxSize()
                )
            }
        },

    )
}


@Composable
fun CampusConnectApp(
    viewModel: LoginViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
){
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = LoginScreen.valueOf(
        backStackEntry?.destination?.route ?: LoginScreen.Welcome.name
    )
    Scaffold (
        topBar = {
            if(currentScreen != LoginScreen.Welcome)
            CampusConnectAppBar(
                currentScreen = currentScreen,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->
        val uiState by viewModel.uiState.collectAsState()

        NavHost(
            navController = navController,
            startDestination = LoginScreen.Welcome.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(
                route = LoginScreen.Welcome.name,
                enterTransition = {
                    slideIntoContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.Left,
                        animationSpec = tween(700)
                    )
                },
                exitTransition = {
                    slideOutOfContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.Left,
                        animationSpec = tween(700)
                    )
                }
            ) {

                WelcomeScreen(
                    onStartedButtonClick = {
                        navController.navigate(LoginScreen.Registration.name)
                    },
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(dimensionResource(id = R.dimen.padding_medium))
                )
            }
            composable(
                route = LoginScreen.Registration.name,
                enterTransition = {
                    slideIntoContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.Left,
                        animationSpec = tween(700)
                    )
                },
                exitTransition = {
                    slideOutOfContainer (
                        towards = AnimatedContentTransitionScope.SlideDirection.Right,
                        animationSpec = tween(700)
                    )
                }
            ) {
                RegistrationScreen(
                    loginUiState = uiState,
                    onSendButtonClick = {
                        navController.navigate(LoginScreen.EmailCode.name)
                    },
                    onValueChange = { viewModel.updateMatricula(it) },
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(dimensionResource(id = R.dimen.padding_medium))
                )
            }
            composable(
                route = LoginScreen.EmailCode.name,
                enterTransition = {
                    slideIntoContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.Left,
                        animationSpec = tween(700)
                    )
                },
                exitTransition = {
                    slideOutOfContainer (
                        towards = AnimatedContentTransitionScope.SlideDirection.Right,
                        animationSpec = tween(700)
                    )
                }
            ) {
                EmailCodeScreen(
                    loginUiState = uiState,
                    onVerifyClick = {
                        navController.navigate(LoginScreen.NewPassword.name)
                    },
                    onValueChange = { viewModel.updateEmailCode(it) },
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(dimensionResource(id = R.dimen.padding_medium))
                )
            }
            composable(
                route = LoginScreen.NewPassword.name,
                enterTransition = {
                    slideIntoContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.Left,
                        animationSpec = tween(700)
                    )
                },
                exitTransition = {
                    slideOutOfContainer (
                        towards = AnimatedContentTransitionScope.SlideDirection.Right,
                        animationSpec = tween(700)
                    )
                }
            ) {
                NewPasswordScreen(
                    onSendClick = {
                        navController.navigate(LoginScreen.EnterProfile.name)
                    },
                    onNewPasswordValueChange = { viewModel.updateNewPassword(it) },
                    onConfirmNewPasswordValueChange = { viewModel.updateConfirmNewPassword(it) },
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(dimensionResource(id = R.dimen.padding_medium))
                )
            }
            composable(
                route = LoginScreen.EnterProfile.name,
                enterTransition = {
                    slideIntoContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.Left,
                        animationSpec = tween(700)
                    )
                },
                exitTransition = {
                    slideOutOfContainer (
                        towards = AnimatedContentTransitionScope.SlideDirection.Right,
                        animationSpec = tween(700)
                    )
                }
            ) {
                EnterProfileScreen(
                    loginUiState = uiState,
                    onAccessClick = {
                        navController.navigate(LoginScreen.Welcome.name)
                    },
                    onValueChange = { viewModel.updateConfirmaSenha(it) },
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