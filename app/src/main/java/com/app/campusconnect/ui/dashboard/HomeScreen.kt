package com.app.campusconnect.ui.dashboard

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.campusconnect.R
import com.app.campusconnect.data.uistate.dashboard.DashboardFormState
import com.app.campusconnect.data.uistate.dashboard.DashboardUiState
import com.app.campusconnect.network.dashboard.models.Event
import com.app.campusconnect.network.dashboard.models.User
import com.app.campusconnect.ui.components.ErrorScreen
import com.app.campusconnect.ui.dashboard.components.EventCard
import com.app.campusconnect.ui.components.LoadingScreen
import com.app.campusconnect.ui.theme.CampusConnectTheme

@Composable
fun HomeScreen(
    dashboardUiState: DashboardUiState,
    dashboardFormState: DashboardFormState,
    onEventClick: (Event) -> Unit,
    onValueChange: (DashboardFormState) -> Unit,
    onSearch: (String) -> Unit,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        when (dashboardUiState) {
            is DashboardUiState.Loading -> LoadingScreen(modifier = modifier)
            is DashboardUiState.Success -> ListEventsScreen(
                dashboardFormState = dashboardFormState,
                eventList = dashboardFormState.eventList,
                onEventClick = onEventClick,
                onValueChange = { newValue ->
                    onValueChange(dashboardFormState.copy(searchTerm = newValue))
                },
                onSearch = onSearch // Pass the onSearch lambda directly
            )
            is DashboardUiState.Error -> ErrorScreen(
                error = dashboardUiState.message,
                retryAction = retryAction,
                modifier = modifier
            )
        }
    }
}

@Composable
fun ListEventsScreen(
    dashboardFormState: DashboardFormState,
    eventList: List<Event>,
    onValueChange: (String) -> Unit,
    onSearch: (String) -> Unit, // Receive the onSearch lambda
    onEventClick: (Event) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Bottom,
        modifier = modifier
    ) {
        Spacer(modifier = Modifier.weight(2f))
        OutlinedTextField(
            value = dashboardFormState.searchTerm,
            onValueChange = onValueChange,
            singleLine = true,
            shape = MaterialTheme.shapes.large,
            placeholder = {
                Text(
                    text = stringResource(id = R.string.search)
                )
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    onSearch(dashboardFormState.searchTerm)
                }
            ),
            trailingIcon = {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Search Icon"
                )
            },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_medium)))
        EventList(
            eventList = eventList,
            onEventClick = onEventClick,
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.padding_small))
                .width(200.dp)
                .height(250.dp)
        )
        Spacer(modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_medium)))
        EventList(
            eventList = eventList,
            onEventClick = onEventClick,
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.padding_small))
                .width(300.dp)
                .height(200.dp)
        )
        Spacer(modifier = Modifier.weight(1f))
    }
}

@Composable
fun EventList(
    eventList: List<Event>,
    onEventClick: (Event) -> Unit,
    modifier: Modifier = Modifier
) {
    Column {
        Text(
            text = "Categoria",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
        )
        LazyRow {
            items(eventList) { item: Event ->
                EventCard(
                    event = item,
                    onEventClick = onEventClick,
                    modifier = modifier
                )
            }
        }
    }
}



@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun HomeScreenLightThemePreview() {
    CampusConnectTheme(darkTheme = false) {
        val user = User(
            id = 1,
            name = "",
            email = "",
            registrationNumber = "",
            role = "",
        )
        val mockData = List(10) {
            Event(
                it,
                "Event $it",
                "Description $it",
                "Location $it",
                "Date $it",
                user
            )
        }
        ListEventsScreen(
            dashboardFormState = DashboardFormState(eventList = mockData),
            eventList = mockData,
            onValueChange = {},
            onSearch = {},
            onEventClick = {},
            modifier = Modifier
                .padding(16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenDarkThemePreview() {
    CampusConnectTheme(darkTheme = true) {
        val user = User(
            id = 1,
            name = "",
            email = "",
            registrationNumber = "",
            role = "",
        )
        val mockData = List(10) {
            Event(
                it,
                "Event $it",
                "Description $it",
                "Location $it",
                "Date $it",
                user
            )
        }
        ListEventsScreen(
            dashboardFormState = DashboardFormState(eventList = mockData),
            eventList = mockData,
            onValueChange = {},
            onSearch = {},
            onEventClick = {},
            modifier = Modifier
                .padding(16.dp)
        )
    }
}