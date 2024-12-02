package com.app.campusconnect

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.app.campusconnect.ui.authentication.AuthNavHost
import com.app.campusconnect.ui.authentication.AuthViewModel
import com.app.campusconnect.ui.dashboard.DashboardNavHost

@Composable
fun AppNavigation() {
    val loginViewModel: AuthViewModel = viewModel()
    val authFormState by loginViewModel.authFormState.collectAsState()

    if (authFormState.isUserLoggedIn) {
        DashboardNavHost()
    } else {
        AuthNavHost()
    }
}
