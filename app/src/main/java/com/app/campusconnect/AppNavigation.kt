package com.app.campusconnect

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.app.campusconnect.ui.navigation.authentication.AuthNavigation
import com.app.campusconnect.ui.authentication.AuthViewModel
import com.app.campusconnect.ui.navigation.dashboard.DashboardNavHost

@Composable
fun AppNavigation() {
    val authViewModel: AuthViewModel = viewModel()
    val authState by authViewModel.authState.collectAsState()

    if (authState.formState.isUserLoggedIn) {
        DashboardNavHost()
    } else {
        AuthNavigation()
    }
}
