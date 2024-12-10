package com.app.campusconnect.ui.dashboard.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.campusconnect.theme.CampusConnectTheme
import com.app.campusconnect.ui.navigation.dashboard.DashboardScreen


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
    currentScreen: DashboardScreen,
    onTabSelected: (DashboardScreen) -> Unit = {},
) {
    BottomAppBar (
        tonalElevation = 100.dp,
        contentPadding = PaddingValues(0.dp),
        modifier = Modifier.height(60.dp)
    ) {
        ButtonAppBar(
            imageVector = Icons.Default.Home,
            currentScreen = DashboardScreen.Home,
            onClick = {
                onTabSelected(DashboardScreen.Home) },
            modifier = Modifier
                .weight(1f)
                .fillMaxSize()
        )
        ButtonAppBar(
            imageVector = Icons.AutoMirrored.Filled.List,
            currentScreen = DashboardScreen.MyEvents,
            onClick = { onTabSelected(DashboardScreen.MyEvents) },
            modifier = Modifier
                .weight(1f)
                .fillMaxSize()
        )
        ButtonAppBar(
            imageVector = Icons.Default.AccountCircle,
            currentScreen = DashboardScreen.Profile,
            onClick = { onTabSelected(DashboardScreen.Profile) },
            modifier = Modifier
                .weight(1f)
                .fillMaxSize()
        )


    }
}

@Composable
fun ButtonAppBar(
    currentScreen: DashboardScreen,
    imageVector: ImageVector,
    onClick: () -> Unit = {},
    modifier: Modifier
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        ),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 0.dp,
            focusedElevation = 50.dp,
            disabledElevation = 0.dp
        ),
        shape = RoundedCornerShape(0.dp),
        contentPadding = PaddingValues(0.dp),
        modifier = modifier
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = currentScreen.name,
            modifier = Modifier.size(40.dp)
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
                DashboardBottomAppBar(
                    currentScreen = DashboardScreen.Home,
                    onTabSelected = {}
                )
            }
        ) { innerPadding ->
            Column (modifier = Modifier.padding(innerPadding)) {

            }
        }
    }
}