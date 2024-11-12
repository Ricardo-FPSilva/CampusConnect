package com.app.campusconnect.ui.dashboard

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.app.campusconnect.R
import com.app.campusconnect.ui.dashboard.components.DashboardBottomAppBar
import com.app.campusconnect.ui.dashboard.components.DashboardTopAppBar

enum class DashboardScreen (@StringRes val title: Int){
    Home(title = R.string.home)
}

@Composable
fun DashboardNavHost(
    navController: NavHostController = rememberNavController(),
){
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = DashboardScreen.valueOf(
        backStackEntry?.destination?.route ?: DashboardScreen.Home.name
    )
    Scaffold(
        topBar = {
            DashboardTopAppBar(currentScreen = currentScreen)
        },
        bottomBar = {
            DashboardBottomAppBar()
        }
    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = DashboardScreen.Home.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(
                route = DashboardScreen.Home.name,
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