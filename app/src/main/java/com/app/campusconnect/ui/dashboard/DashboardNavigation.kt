package com.app.campusconnect.ui.dashboard

import androidx.annotation.StringRes
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
import com.app.campusconnect.ui.dashboard.components.DashboardBottomAppBar
import com.app.campusconnect.ui.dashboard.components.DashboardTopAppBar
import com.app.campusconnect.ui.dashboard.components.EventCreationFab

enum class DashboardScreen (@StringRes val title: Int){
    Home(title = R.string.home),
    EventDetails(title = R.string.event_details),
    EventCreation(title = R.string.event_creation),
    MyEvents(title = R.string.my_events)
}

@Composable
fun DashboardNavHost(
    navController: NavHostController = rememberNavController(),
){
    val dashboardViewModel: DashboardViewModel = viewModel(factory = DashboardViewModel.Factory)
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = DashboardScreen.valueOf(
        backStackEntry?.destination?.route ?: DashboardScreen.Home.name
    )
    Scaffold(
        topBar = {
            DashboardTopAppBar(currentScreen = currentScreen)
        },
        bottomBar = {
            DashboardBottomAppBar(
                currentScreen = currentScreen,
                onTabSelected = { newScreen ->
                    navController.navigate(newScreen.name)
                }
            )
        },
        floatingActionButton = {
            EventCreationFab(
                onFabClick = {
                    navController.navigate(DashboardScreen.EventCreation.name)
                }
            )
        }
    ) { innerPadding ->
        val uiState by dashboardViewModel.uiState.collectAsState()
        NavHost(
            navController = navController,
            startDestination = DashboardScreen.Home.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(
                route = DashboardScreen.Home.name,
            ) {
                HomeScreen(
                    uiState = uiState,
                    onEventClick = { event ->
                        dashboardViewModel.setSelectedEvent(event)
                        if (dashboardViewModel.selectedEvent.value != null){
                            navController.navigate(DashboardScreen.EventDetails.name)
                        }
                    },
                    retryAction = { dashboardViewModel.getEventsList() },
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(dimensionResource(id = R.dimen.padding_medium))
                )
            }
            composable(
                route = DashboardScreen.EventDetails.name,
            ) {
                val selectedEvent by dashboardViewModel.selectedEvent.collectAsState()
                EventDetailsScreen(
                    event = selectedEvent!!,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            dimensionResource(id = R.dimen.padding_medium)
                        )
                )
            }
            composable(
                route = DashboardScreen.EventCreation.name,
            ) {
                EventCreationScreen(
                    modifier = Modifier
                        .fillMaxSize()
                )
            }
            composable (
                route = DashboardScreen.MyEvents.name,
            ){
                MyEventsScreen(
                    uiState = uiState,
                    onSelectionSub = { isSelected ->
                        dashboardViewModel.setSelectedMyEvents(isSelected)
                    },
                    onSelectionCreate = { isSelected ->
                        dashboardViewModel.setSelectedMyEvents(!isSelected)
                    },
                    onEventClick = { event ->
                        dashboardViewModel.setSelectedEvent(event)
                        if (dashboardViewModel.selectedEvent.value != null){
                            navController.navigate(DashboardScreen.EventDetails.name)
                        }
                    },
                    retryAction = { dashboardViewModel.getEventsList() },
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(dimensionResource(id = R.dimen.padding_medium))
                )
            }
        }
    }
}