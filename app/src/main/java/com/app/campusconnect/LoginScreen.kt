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
import com.app.campusconnect.ui.loginScreens.EnterProfileScreen
import com.app.campusconnect.ui.loginScreens.InsertRegistrationScreen
import com.app.campusconnect.ui.loginScreens.NewPasswordScreen
import com.app.campusconnect.ui.loginScreens.VerificationCodeScreen
import com.app.campusconnect.ui.loginScreens.WelcomeScreen
import com.app.campusconnect.ui.theme.CampusConnectTheme

@Composable
fun LoginScreen(){
    Scaffold (
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        EnterProfileScreen()
    }

}

@Preview(showBackground = true)
@Composable
fun LoginScreenLightThemePreview() {
    CampusConnectTheme (darkTheme = false) {
        LoginScreen()
    }
}
@Preview(showBackground = true)
@Composable
fun LoginScreenDarkThemePreview() {
    CampusConnectTheme (darkTheme = true) {
        LoginScreen()
    }
}