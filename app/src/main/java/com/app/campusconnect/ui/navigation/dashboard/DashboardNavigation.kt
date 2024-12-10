package com.app.campusconnect.ui.navigation.dashboard

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Done
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
import com.app.campusconnect.ui.dashboard.DashboardViewModel
import com.app.campusconnect.ui.dashboard.EventCreationScreen
import com.app.campusconnect.ui.dashboard.EventDetailsScreen
import com.app.campusconnect.ui.dashboard.HomeScreen
import com.app.campusconnect.ui.dashboard.MyEventsScreen
import com.app.campusconnect.ui.dashboard.ProfileScreen
import com.app.campusconnect.ui.dashboard.SearchScreen
import com.app.campusconnect.ui.dashboard.components.DashboardBottomAppBar
import com.app.campusconnect.ui.dashboard.components.DashboardTopAppBar
import com.app.campusconnect.ui.dashboard.components.EventCreationFab
import com.app.campusconnect.ui.dashboard.components.EventNotFound


@Composable
fun DashboardNavHost(
    dashboardViewModel: DashboardViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = backStackEntry?.destination?.route?.let {
        DashboardScreen.valueOf(it)
    } ?: DashboardScreen.Home

    val dashboardState by dashboardViewModel.dashboardState.collectAsState()

    Scaffold(
        topBar = {
            DashboardTopAppBar(currentScreen = currentScreen)
        },
        bottomBar = {
            DashboardBottomAppBar(
                currentScreen = currentScreen,
                onTabSelected = { newScreen ->
                    navController.popBackStack(DashboardScreen.Home.name, inclusive = false)
                    navController.navigate(newScreen.name) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        },
        floatingActionButton = {
            if (dashboardState.formState.isPermittedCreationEvent) {
                when (currentScreen) {
                    DashboardScreen.Home ->
                        EventCreationFab(
                            imageVector = Icons.Default.Add,
                            onFabClick = {
                                navController.navigate(DashboardScreen.EventCreation.name)
                            }
                        )
                    DashboardScreen.EventCreation ->
                        EventCreationFab(
                            imageVector = Icons.Default.Done,
                            onFabClick = {
                                navController.popBackStack()
                            }
                        )
                    else -> {}
                }
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
                    uiState = dashboardState.uiState,
                    dashboardFormState = dashboardState.formState,
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
                val selectedEvent = dashboardState.formState.selectedEvent
                if (selectedEvent != null) {
                    EventDetailsScreen(
                        event = selectedEvent,
                        isSubscribed = dashboardViewModel.setIsSubscribed(selectedEvent),
                        onSubscribeClick = { event ->
                            dashboardViewModel.subscribeEvent(event.id)
                        },
                        onUnsubscribeClick = { event ->
                            dashboardViewModel.unsubscribeEvent(event.id)
                        },
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(dimensionResource(id = R.dimen.padding_medium))
                    )
                } else {
                    EventNotFound()
                }
            }
            composable(route = DashboardScreen.EventCreation.name) {
                EventCreationScreen(
                    dashboardFormState = dashboardState.formState,
                    onTitleChange = { title ->
                        dashboardViewModel.updateDashboardFormState(
                            dashboardState.formState.copy(
                                eventCreated = dashboardState.formState
                                    .eventCreated.copy(
                                        title = title
                                    )
                            )
                        )
                    },
                    onLocationChange = { location ->
                        dashboardViewModel.updateDashboardFormState(
                            dashboardState.formState.copy(
                                eventCreated = dashboardState.formState
                                    .eventCreated.copy(
                                        location = location
                                    )
                            )
                        )
                    },
                    onDescriptionChange = { description ->
                        dashboardViewModel.updateDashboardFormState(
                            dashboardState.formState.copy(
                                eventCreated = dashboardState.formState
                                    .eventCreated.copy(
                                        description = description
                                    )
                            )
                        )
                    },
                    modifier = Modifier
                        .fillMaxSize()
                )
            }
            composable(route = DashboardScreen.MyEvents.name) {
                MyEventsScreen(
                    uiState = dashboardState.uiState,
                    dashboardFormState = dashboardState.formState,
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
            composable(route = DashboardScreen.Profile.name) {
                ProfileScreen(
                    dashboardFormState = dashboardState.formState,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(dimensionResource(id = R.dimen.padding_medium))
                )
            }
            composable(route = DashboardScreen.Search.name) {
                SearchScreen(
                    dashboardFormState = dashboardState.formState,
                    onEventClick = { event ->
                        dashboardViewModel.setSelectedEvent(event)
                        navController.navigate(DashboardScreen.EventDetails.name)
                    },
                    onValueChange = { updatedState ->
                        dashboardViewModel.updateDashboardFormState(
                            dashboardState.formState.copy(searchTerm = updatedState)
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