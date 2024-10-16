package com.app.campusconnect.ui.loginScreens

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.app.campusconnect.R
import com.app.campusconnect.ui.theme.CampusConnectTheme

@Composable
fun NewPasswordScreen(
    modifier: Modifier = Modifier,
){
    Column (
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top,
        modifier = modifier
            .fillMaxSize()
    ){
        Text(text = "Nova Senha")
        Text(text = "Digite sua nova senha")
        NewPasswordField(
            label = R.string.password,
            leadingIcon = Icons.Default.Lock,
            value = "",
            onValueChange = {},
            keyboardOptions = KeyboardOptions.Default
        )
        Text(text = "Confirme sua nova senha")
        NewPasswordField(
            label = R.string.password,
            leadingIcon = Icons.Default.Lock,
            value = "",
            onValueChange = {},
            keyboardOptions = KeyboardOptions.Default
        )
        Button(onClick = { /*TODO*/ }) {
            Text(text = stringResource(id = R.string.send))
        }



    }
}
@Composable
fun NewPasswordField(
    @StringRes label: Int,
    leadingIcon: ImageVector,
    value: String,
    onValueChange: (String) -> Unit,
    keyboardOptions: KeyboardOptions,
    modifier: Modifier = Modifier
) {
    TextField(
        value = value,
        leadingIcon = { Icon(imageVector = leadingIcon, null) },
        onValueChange = onValueChange,
        label = { Text(stringResource(label)) },
        singleLine = true,
        keyboardOptions = keyboardOptions,
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun NewPasswordLightThemePreview() {
    CampusConnectTheme (darkTheme = false){
        NewPasswordScreen()
    }
}
@Preview(showBackground = true)
@Composable
fun NewPasswordDarkThemePreview() {
    CampusConnectTheme (darkTheme = true){
        NewPasswordScreen()
    }
}

