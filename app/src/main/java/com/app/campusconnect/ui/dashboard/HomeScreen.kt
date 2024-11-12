package com.app.campusconnect.ui.dashboard

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
import com.app.campusconnect.data.DashboardUiState
import com.app.campusconnect.data.Event
import com.app.campusconnect.ui.theme.CampusConnectTheme

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
){

    val campusConnectUiState = DashboardUiState()
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
            eventList = campusConnectUiState.eventList,
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.padding_small))
                .width(200.dp)
                .height(250.dp)

        )
        Spacer(modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_medium)))
        EventList(
            eventList = campusConnectUiState.eventList,
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.padding_small))
                .width(300.dp)
                .height(200.dp)
        )
        Spacer(modifier = Modifier.weight(1f))
    }
}


@Composable
fun EventCard(
    event: Event,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        OutlinedButton(
            onClick = { /*TODO*/ },
            shape = MaterialTheme.shapes.medium,
            contentPadding = PaddingValues(0.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            Box (modifier = Modifier.fillMaxSize()){
                Image(
                    painter = painterResource(id = event.imageResourceId),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                )
                Text(
                    text = stringResource(id = event.title),
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.BottomCenter)
                )
            }
        }
    }
}

@Composable
fun EventList(
    eventList: List<Event>,
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
                        modifier = modifier
                    )

            }
        }
    }

}




@Preview(showBackground = true)
@Composable
fun HomeScreenLightThemePreview() {
    CampusConnectTheme (darkTheme = false) {
        HomeScreen(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        )
    }
}
@Preview(showBackground = true)
@Composable
fun HomeScreenDarkThemePreview() {
    CampusConnectTheme (darkTheme = true) {
        HomeScreen(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        )
    }
}