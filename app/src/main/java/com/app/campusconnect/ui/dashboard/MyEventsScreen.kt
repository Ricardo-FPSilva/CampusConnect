package com.app.campusconnect.ui.dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.app.campusconnect.R
import com.app.campusconnect.data.uistate.dashboard.DashboardFormState
import com.app.campusconnect.data.uistate.dashboard.DashboardUiState
import com.app.campusconnect.network.dashboard.models.Enrollment
import com.app.campusconnect.network.dashboard.models.Event
import com.app.campusconnect.network.dashboard.models.User
import com.app.campusconnect.ui.components.ErrorScreen
import com.app.campusconnect.ui.components.LoadingScreen
import com.app.campusconnect.ui.theme.CampusConnectTheme

@Composable
fun MyEventsScreen(
    dashboardUiState: DashboardUiState,
    dashboardFormState: DashboardFormState, // Adicionado dashboardFormState
    onSelectionSub: (Boolean) -> Unit,
    onSelectionCreate: (Boolean) -> Unit,
    onEventClick: (Event) -> Unit,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        when (dashboardUiState) {
            is DashboardUiState.Loading -> LoadingScreen(modifier = modifier)
            is DashboardUiState.Success -> SubOrCreateScreen(
                dashboardFormState = dashboardFormState,
                onSelectionSub = onSelectionSub,
                onSelectionCreate = onSelectionCreate,
                onEventClick = onEventClick,
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
fun SubOrCreateScreen (
    dashboardFormState: DashboardFormState,
    onSelectionSub: (Boolean) -> Unit,
    onSelectionCreate: (Boolean) -> Unit,
    onEventClick: (Event) -> Unit,
    modifier: Modifier = Modifier
) {
    Column (
        modifier = modifier
    ){
        Row {
            SubOrCreateOutlineButton(
                text = "Inscrições",
                topStart = 50.dp,
                topEnd = 0.dp,
                bottomStart = 50.dp,
                bottomEnd = 0.dp,
                isSelected = dashboardFormState.isSelectedMyEvents,
                onSelectionChange = onSelectionSub,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .weight(1f)
            )
            SubOrCreateOutlineButton(
                text = "Seus Eventos",
                topStart = 0.dp,
                topEnd = 50.dp,
                bottomStart = 0.dp,
                bottomEnd = 50.dp,
                isSelected = !dashboardFormState.isSelectedMyEvents,
                onSelectionChange = onSelectionCreate,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .weight(1f)
            )
        }
        if (dashboardFormState.isSelectedMyEvents) {
            SubscriptionsList(
                subscriptionsList = dashboardFormState.eventListEnrolled,
                onEventClick = onEventClick
            )
        } else {
            EventsCreatedList(
                eventsCreatedList = listOf(
                    Event(
                        id = 1,
                        title = "Event 4",
                        description = "Description 1",
                        location = "Location 1",
                        eventDate = "15/12/1233",
                        createdBy = User(0, "Ricarsd", "", "", ""),
                    ),
                    Event(
                        id = 1,
                        title = "Event 5",
                        description = "Description 1",
                        location = "Location 1",
                        eventDate = "12/45/3212",
                        createdBy = User(0, "Artgh", "", "", ""),
                    ),
                    Event(
                        id = 1,
                        title = "Event 6",
                        description = "Description 1",
                        location = "Location 1",
                        eventDate = "32/12/1233",
                        createdBy = User(0, "Samsd", "", "", ""),
                    ),
                    Event(
                        id = 1,
                        title = "Event 4",
                        description = "Description 1",
                        location = "Location 1",
                        eventDate = "45/65/5665",
                        createdBy = User(0, "Marsa", "", "", ""),
                    ),
                    Event(
                        id = 1,
                        title = "Event 5",
                        description = "Description 1",
                        location = "Location 1",
                        eventDate = "45/45/4545",
                        createdBy = User(0, "ASoudas", "", "", ""),
                    ),
                    Event(
                        id = 1,
                        title = "Event 6",
                        description = "Description 1",
                        location = "Location 1",
                        eventDate = "45/87/8555",
                        createdBy = User(0, "Saoudsa", "", "", ""),
                    ),
                    Event(
                        id = 1,
                        title = "Concurso de miss beleza brasil",
                        description = "Description 1",
                        location = "Location 1",
                        eventDate = "78/85/3632",
                        createdBy = User(0, "Nosaw", "", "", ""),
                    ),
                    Event(
                        id = 1,
                        title = "Event 5",
                        description = "Description 1",
                        location = "Location 1",
                        eventDate = "85/21/2112",
                        createdBy = User(0, "nasrrsa", "", "", ""),
                    ),
                    Event(
                        id = 1,
                        title = "Event 6",
                        description = "Description 1",
                        location = "Location 1",
                        eventDate = "24/04/2024",
                        createdBy = User(0, "Osadjuias", "", "", ""),
                    ),
                ),
                onEventClick = onEventClick
            )
        }
    }

}

@Composable
fun SubOrCreateOutlineButton (
    text: String,
    topStart: Dp,
    topEnd: Dp,
    bottomStart: Dp,
    bottomEnd: Dp,
    isSelected: Boolean,
    onSelectionChange: (Boolean) -> Unit,
    modifier: Modifier
){
    val buttonColors = if (isSelected) {
        ButtonDefaults.outlinedButtonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        )
    } else {
        ButtonDefaults.outlinedButtonColors()
    }
    OutlinedButton(
        shape = RoundedCornerShape(
            topStart = topStart,
            topEnd = topEnd,
            bottomStart = bottomStart,
            bottomEnd = bottomEnd
        ),
        colors = buttonColors,
        onClick = { onSelectionChange(!isSelected) },
        modifier = modifier
    ) {
        Text(
            text = text,
            modifier = Modifier
        )
    }
}


@Composable
fun EventsCreatedList(
    eventsCreatedList: List<Event>,
    onEventClick: (Event) -> Unit,
    modifier: Modifier = Modifier
){
    Text(
        text = "Events created",
        modifier = Modifier
    )
    LazyColumn (
        modifier = modifier
    ) {
        items(eventsCreatedList) { item: Event ->
            MyEventCard(
                event = item,
                onEventClick = onEventClick,
                modifier = Modifier
            )
        }
    }
}

@Composable
fun SubscriptionsList(
    subscriptionsList: List<Enrollment>,
    onEventClick: (Event) -> Unit,
    modifier: Modifier = Modifier
){
    Text(
        text = "Subscriptions",
        modifier = Modifier
    )
    LazyColumn (
        modifier = modifier
    ){
        items(subscriptionsList) { item: Enrollment ->
            MyEventCard(
                event = item.event,
                onEventClick = onEventClick,
                modifier = Modifier
            )
        }
    }
}



@Composable
fun MyEventCard(
    event: Event,
    onEventClick: (Event) -> Unit,
    modifier: Modifier = Modifier
){
    Card (
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = modifier
            .padding(bottom = dimensionResource(R.dimen.padding_large))
            .aspectRatio(3.5f)
    ) {
        OutlinedButton(
            onClick = { onEventClick(event) },
            shape = MaterialTheme.shapes.medium,
            contentPadding = PaddingValues(0.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .aspectRatio(1f)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.campus_connect_logo),
                        contentDescription = "",
                        modifier = Modifier
                            .align(Alignment.Center)
                    )
                }
                Column (
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .padding(dimensionResource(R.dimen.padding_medium))
                ) {
                    Text(
                        text = event.title,
                        maxLines = 1,
                        style = MaterialTheme.typography.titleLarge,
                    )
                    Text(
                        text = event.createdBy.name,
                        style = MaterialTheme.typography.titleMedium,
                    )
                    Text(
                        text = event.eventDate,
                        style = MaterialTheme.typography.titleMedium,
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun EventCardLightThemePreview(){
    CampusConnectTheme(darkTheme = false) {
        MyEventCard(
            event = Event(
                id = 1,
                title = "Event 1",
                description = "Description 1",
                location = "Location 1",
                eventDate = "",
                createdBy = User(0, "", "", "", ""),
            ),
            onEventClick = {}
        )
    }
}
@Preview
@Composable
fun EventCardDarkThemePreview() {
    CampusConnectTheme(darkTheme = true) {
        MyEventCard(
            event = Event(
                id = 1,
                title = "Event 1",
                description = "Description 1",
                location = "Location 1",
                eventDate = "",
                createdBy = User(0, "", "", "", ""),
            ),
            onEventClick = {}
        )
    }
}


@Preview
@Composable
fun MyEventsLightThemePreview(){
    CampusConnectTheme(darkTheme = false) {
        SubOrCreateScreen(
            dashboardFormState = DashboardFormState(),
            onSelectionSub = {},
            onSelectionCreate = {},
            onEventClick = {},
            modifier = Modifier
        )
    }
}

@Preview
@Composable
fun MyEventsDarkThemePreview() {
    CampusConnectTheme(darkTheme = true) {
        SubOrCreateScreen(
            dashboardFormState = DashboardFormState(),
            onSelectionSub = {},
            onSelectionCreate = {},
            onEventClick = {},
            modifier = Modifier
        )
    }
}
