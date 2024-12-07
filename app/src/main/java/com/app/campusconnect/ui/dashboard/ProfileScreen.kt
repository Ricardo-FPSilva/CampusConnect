package com.app.campusconnect.ui.dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.app.campusconnect.R
import com.app.campusconnect.data.uistate.dashboard.DashboardFormState
import com.app.campusconnect.models.common.User
import com.app.campusconnect.theme.CampusConnectTheme

@Composable
fun ProfileScreen(
    dashboardFormState: DashboardFormState,
    modifier: Modifier = Modifier
){
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
    ) {

        Spacer(modifier = Modifier.weight(1f))
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .padding(dimensionResource(R.dimen.padding_large))
                .size(dimensionResource(R.dimen.half_screen_size))
                .align(Alignment.CenterHorizontally)
        ) {
            Image(
                painter = painterResource(id = R.drawable._profile_transparente),
                contentDescription = stringResource(R.string.campus_connect_logo),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
            )
        }
        Text(
            text = stringResource(
                R.string.user_title,
                dashboardFormState.user?.role
                    ?.lowercase()
                    ?.replaceFirstChar {
                        it.uppercase()
                    } ?: "",
                dashboardFormState.user?.name ?: ""
            ),
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.padding_medium)),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.padding(dimensionResource(R.dimen.padding_small)))
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = stringResource(R.string.registration_number),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.Start)
            )
            Spacer(modifier = Modifier.padding(dimensionResource(R.dimen.padding_small)))
            Card(
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(dimensionResource(R.dimen.corner_radius_medium)),
                elevation = CardDefaults.cardElevation(defaultElevation = dimensionResource(R.dimen.elevation_small)),
            ) {
                Text(
                    text = dashboardFormState.user?.registrationNumber ?: "",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(dimensionResource(R.dimen.padding_medium)),
                    textAlign = TextAlign.Center
                )
            }
        }
        Spacer(modifier = Modifier.padding(dimensionResource(R.dimen.padding_small)))
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Email User:",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.Start)
            )
            Spacer(modifier = Modifier.padding(dimensionResource(R.dimen.padding_small)))
            Card(
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(dimensionResource(R.dimen.corner_radius_medium)),
                elevation = CardDefaults.cardElevation(defaultElevation = dimensionResource(R.dimen.elevation_small)),
            ) {
                Text(
                    text = dashboardFormState.user?.email ?: "",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(dimensionResource(R.dimen.padding_medium)),
                    textAlign = TextAlign.Center
                )
            }
        }
        Spacer(modifier = Modifier.weight(1f))
    }
}



@Preview
@Composable
fun ProfileScreenLightThemePreview(){
    CampusConnectTheme (darkTheme = false) {
        val user = User(
            id = 1,
            name = "John Doe",
            email = "william.henry.harrison@ifpa.edu.br",
            registrationNumber = "123456",
            role = "Student"
        )
        ProfileScreen(
            dashboardFormState = DashboardFormState(user = user)
        )
    }
}

@Preview
@Composable
fun ProfileScreenDarkThemePreview() {
    CampusConnectTheme(darkTheme = true) {
        val user = User(
            id = 1,
            name = "John Doe",
            email = "william.henry.harrison@ifpa.edu.br",
            registrationNumber = "123456",
            role = "Student"
        )
        ProfileScreen(
            dashboardFormState = DashboardFormState(user = user)
        )
    }
}