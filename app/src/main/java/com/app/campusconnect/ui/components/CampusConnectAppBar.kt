package com.app.campusconnect.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.app.campusconnect.CampusConnectScreen
import com.app.campusconnect.ui.theme.CampusConnectTheme



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CampusConnectTopAppBar (
    currentScreen: CampusConnectScreen
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
fun CampusConnectBottomAppBar (
    currentScreen: CampusConnectScreen
) {
    BottomAppBar {
        Text(
            text = stringResource(id = currentScreen.title),
            style = MaterialTheme.typography.displaySmall,
            fontWeight = FontWeight.Bold,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CampusConnectAppBarPreview() {
    CampusConnectTheme {
        Scaffold (
            topBar = {
                CampusConnectTopAppBar(currentScreen = CampusConnectScreen.Welcome)
            },
            bottomBar = {
                CampusConnectBottomAppBar(currentScreen = CampusConnectScreen.Welcome)
            }
        ) { innerPadding ->
            Column (modifier = Modifier.padding(innerPadding)) {

            }
        }
    }
}