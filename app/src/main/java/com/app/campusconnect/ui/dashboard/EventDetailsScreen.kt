package com.app.campusconnect.ui.dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.campusconnect.R
import com.app.campusconnect.network.models.Event
import com.app.campusconnect.network.models.User
import com.app.campusconnect.ui.theme.CampusConnectTheme

@Composable
fun EventDetailsScreen(
    event: Event,
    modifier: Modifier = Modifier,
){

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Card (
                modifier = Modifier
                    .weight(3f)
                    .aspectRatio(1f)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.campus_connect_logo),
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxSize()
                )
            }

            Spacer(modifier = Modifier.weight(0.5f))
            Column (
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.padding_small))
                    .weight(3f)
            ) {
                Text(
                    text = event.title,
                    style = MaterialTheme.typography.headlineLarge,
                    modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.padding_small))
                )
                Text(
                    text = event.createdBy.name,
                    style = MaterialTheme.typography.bodyLarge,
                )
                Spacer(modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_medium)))
                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(text = stringResource(R.string.subscribe))
                }
            }

        }
        Spacer(modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_medium)))
        Column (
            modifier = Modifier

        ) {
            Text(
                text = stringResource(R.string.date, event.eventDate),
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_small))
            )
            Text(
                text = stringResource(R.string.local, event.location),
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_small))
            )
            Text(
                text = event.description,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_small))
            )
        }
    }

}



@Preview(showBackground = true)
@Composable
fun EventDetailsScreenLightThemePreview() {
    CampusConnectTheme (darkTheme = false) {
        val user = User(
            id = 1,
            name = "Ricardo Silva",
            email = "",
            registrationNumber = "",
            role = "",
        )
        EventDetailsScreen(
            event = Event(
                1,
                "Event 1",
                stringResource(R.string.instructions_to_no_registration),
                "Rua Manoel Barata, 1500",
                "24/04/2002",
                user
            ),
            modifier = Modifier
                .padding(16.dp)
        )
    }
}
@Preview(showBackground = true)
@Composable
fun EventDetailsScreenDarkThemePreview() {
    CampusConnectTheme (darkTheme = true) {
        val user = User(
            id = 1,
            name = "Ricardo Silva",
            email = "",
            registrationNumber = "",
            role = "",
        )
        EventDetailsScreen(
            event = Event(
                1,
                "Event 1",
                stringResource(R.string.instructions_to_no_registration),
                "Rua Manoel Barata, 1500",
                "24/04/2002",
                user
            ),
            modifier = Modifier
                .padding(16.dp)
        )
    }
}