package com.app.campusconnect.ui.dashboard

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import com.app.campusconnect.ui.dashboard.models.DashboardScreen


@Composable
fun DashboardNavHost(
    navController: NavHostController = rememberNavController(),
) {
    val dashboardViewModel: DashboardViewModel = viewModel()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = DashboardScreen.valueOf(
        backStackEntry?.destination?.route ?: DashboardScreen.Home.name
    )
    val dashboardFormState by dashboardViewModel.dashboardFormState.collectAsState()
    val uiState by dashboardViewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            DashboardTopAppBar(currentScreen = currentScreen)
        },
        bottomBar = {
            DashboardBottomAppBar(
                currentScreen = currentScreen,
                onTabSelected = { newScreen ->
                    navController.popBackStack(DashboardScreen.Home.name, inclusive = false)
                    navController.navigate(newScreen.name)
                }
            )
        },
        floatingActionButton = {
            if (currentScreen == DashboardScreen.Home) {
                EventCreationFab(
                    onFabClick = {
                        navController.navigate(DashboardScreen.EventCreation.name)
                    }
                )
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = DashboardScreen.Home.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = DashboardScreen.Home.name) {
                HomeScreen(
                    dashboardUiState = uiState,
                    dashboardFormState = dashboardFormState,
                    onEventClick = { event ->
                        dashboardViewModel.setSelectedEvent(event)
                        navController.navigate(DashboardScreen.EventDetails.name)
                    },
                    onValueChange = { updatedState ->
                        dashboardViewModel.updateDashboardFormState(updatedState)
                    },
                    retryAction = { dashboardViewModel.getEventsList() },
                    onSearch = { searchTerm ->
                        dashboardViewModel.performSearch(searchTerm)
                        navController.navigate(DashboardScreen.Search.name)
                    },
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(dimensionResource(id = R.dimen.padding_medium))
                )
            }
            composable(route = DashboardScreen.EventDetails.name) {
                val selectedEvent = dashboardFormState.selectedEvent
                if (selectedEvent != null) {
                    EventDetailsScreen(
                        event = selectedEvent,
                        onSubscribeClick = { event ->
                            dashboardViewModel.eventRegistration(event.id)
                        },
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(dimensionResource(id = R.dimen.padding_medium))
                    )
                } else {
                    Text("Evento não encontrado.")
                }
            }
            composable(route = DashboardScreen.EventCreation.name) {
                EventCreationScreen(
                    modifier = Modifier
                        .fillMaxSize()
                )
            }
            composable(route = DashboardScreen.MyEvents.name) {
                MyEventsScreen(
                    dashboardUiState = uiState,
                    dashboardFormState = dashboardFormState,
                    onSelectionSub = { isSelected ->
                        dashboardViewModel.updateSelectedMyEvents(isSelected)
                    },
                    onSelectionCreate = { isSelected ->
                        dashboardViewModel.updateSelectedMyEvents(!isSelected)
                    },
                    onEventClick = { event ->
                        dashboardViewModel.setSelectedEvent(event)
                        navController.navigate(DashboardScreen.EventDetails.name)
                    },
                    retryAction = { dashboardViewModel.getEventsEnrolled() },
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(dimensionResource(id = R.dimen.padding_medium))
                )
            }
            // Add composable for the Search screen
            composable(route = DashboardScreen.Search.name) {
                SearchScreen(
                    dashboardFormState = dashboardFormState,
                    onEventClick = { event ->
                        dashboardViewModel.setSelectedEvent(event)
                        navController.navigate(DashboardScreen.EventDetails.name)
                    },
                    onValueChange = { updatedState ->
                        dashboardViewModel.updateDashboardFormState(
                            dashboardFormState.copy(searchTerm = updatedState)
                        )
                    },
                    onSearch = { searchTerm ->
                        dashboardViewModel.performSearch(searchTerm)
                    }
                )
            }
        }
    }
}