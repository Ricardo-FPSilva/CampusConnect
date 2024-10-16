package com.app.campusconnect

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.app.campusconnect.ui.loginScreens.InsertRegistrationScreen
import com.app.campusconnect.ui.loginScreens.VerificationCodeScreen
import com.app.campusconnect.ui.loginScreens.WelcomeScreen
import com.app.campusconnect.ui.theme.CampusConnectTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CampusConnectAppBar(
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = {},
        modifier = modifier.padding(dimensionResource(id = R.dimen.padding_small)),
        navigationIcon = {
            if (true) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back),
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
    )
}

@Composable
fun CampusConnectApp(){
    Scaffold (
        topBar = {
            CampusConnectAppBar(
                canNavigateBack = false,
                navigateUp = { /* TODO: implement back navigation */ }
            )
        },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        VerificationCodeScreen()
    }

}

@Preview(showBackground = true)
@Composable
fun CampusConnectAppLightThemePreview() {
    CampusConnectTheme (darkTheme = false) {
        CampusConnectApp()
    }
}
@Preview(showBackground = true)
@Composable
fun CampusConnectAppDarkThemePreview() {
    CampusConnectTheme (darkTheme = true) {
        CampusConnectApp()
    }
}