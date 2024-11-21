package com.app.campusconnect.ui.dashboard

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.campusconnect.R
import com.app.campusconnect.data.uistate.DashboardUiState
import com.app.campusconnect.network.Event
import com.app.campusconnect.network.User
import com.app.campusconnect.ui.dashboard.components.ErrorScreen
import com.app.campusconnect.ui.dashboard.components.LoadingScreen
import com.app.campusconnect.ui.theme.CampusConnectTheme

@Composable
fun HomeScreen(
    uiState: DashboardUiState,
    onEventClick: (Event) -> Unit,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier
){
    Column (modifier = modifier){
        when (uiState) {
            is DashboardUiState.Loading -> LoadingScreen(modifier = modifier)
            is DashboardUiState.Success -> ListEventsScreen(
                eventList = uiState.eventList,
                onEventClick = onEventClick,
            )
            is DashboardUiState.Error -> ErrorScreen(
                retryAction = retryAction,
                modifier = modifier
            )
        }
    }

}

@Composable
fun ListEventsScreen(
    eventList: List<Event>,
    onEventClick: (Event) -> Unit,
    modifier: Modifier = Modifier
){
    Column (
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Bottom,
        modifier = modifier
    ){

        Spacer(modifier = Modifier.weight(2f))
        OutlinedTextField(
            value = "",
            onValueChange = {},
            shape = MaterialTheme.shapes.large,
            placeholder = {
                Text(
                    text = stringResource(id = R.string.search)
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
            items(eventList){ item: Event ->
                    EventCard(
                        event = item,
                        onEventClick = onEventClick,
                        modifier = modifier
                    )

            }
        }
    }

}
@Composable
fun EventCard(
    event: Event,
    onEventClick: (Event) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        OutlinedButton(
            onClick = { onEventClick(event) },
            shape = MaterialTheme.shapes.medium,
            contentPadding = PaddingValues(0.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            Box (modifier = Modifier.fillMaxSize()){
                Image(
                    painter = painterResource(id = R.drawable.campus_connect_logo),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                )
                Text(
                    text = event.title,
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.BottomCenter)
                )
            }
        }
    }
}





@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun HomeScreenLightThemePreview() {
    CampusConnectTheme (darkTheme = false) {
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
            eventList = mockData,
            onEventClick = {},
            modifier = Modifier
                .padding(16.dp)
        )
    }
}
@Preview(showBackground = true)
@Composable
fun HomeScreenDarkThemePreview() {
    CampusConnectTheme (darkTheme = true) {
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
            eventList = mockData,
            onEventClick = {},
            modifier = Modifier
                .padding(16.dp)
        )
    }
}