package com.app.campusconnect.ui.dashboard.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.campusconnect.ui.dashboard.DashboardScreen
import com.app.campusconnect.ui.theme.CampusConnectTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardTopAppBar (
    currentScreen: DashboardScreen
) {
    TopAppBar (
        title = {
            Text(
                text = stringResource(id = currentScreen.title),
                style = MaterialTheme.typography.displaySmall,
                fontWeight = FontWeight.Bold,
            )
        }

    )
}

@Composable
fun DashboardBottomAppBar (
) {
    BottomAppBar (
        containerColor = MaterialTheme.colorScheme.primary,
        modifier = Modifier
    ) {
        Row (
            modifier = Modifier
                .fillMaxSize()
        ) {
            ButtonAppBar(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
            )
            ButtonAppBar(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
            )
            ButtonAppBar(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
            )

        }
    }
}

@Composable
fun ButtonAppBar(
    modifier: Modifier
) {
    Button(
        onClick = { /*TODO*/ },
        colors = ButtonDefaults.buttonColors(
            contentColor = MaterialTheme.colorScheme.onPrimary
        ),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 0.dp,
            pressedElevation = 10.dp,
            disabledElevation = 0.dp
        ),
        shape = MaterialTheme.shapes.extraSmall,
        contentPadding = PaddingValues(0.dp),
        modifier = modifier
    ) {
        Text(
            text = "Home",
            style = MaterialTheme.typography.displaySmall,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxSize()
        )
    }
}


@Preview(showBackground = true)
@Composable
fun DashboardAppBarPreview() {
    CampusConnectTheme {
        Scaffold (
            topBar = {
                DashboardTopAppBar(currentScreen = DashboardScreen.Home)
            },
            bottomBar = {
                DashboardBottomAppBar()
            }
        ) { innerPadding ->
            Column (modifier = Modifier.padding(innerPadding)) {

            }
        }
    }
}