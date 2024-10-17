package com.app.campusconnect

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.app.campusconnect.ui.loginScreens.EnterProfileScreen
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