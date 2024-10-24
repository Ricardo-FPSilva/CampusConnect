package com.app.campusconnect.ui.loginScreens

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.app.campusconnect.R
import com.app.campusconnect.data.LoginUiState
import com.app.campusconnect.ui.theme.CampusConnectTheme

@Composable
fun NewPasswordScreen(
    loginUiState: LoginUiState,
    onNewPasswordValueChange: (String) -> Unit,
    onConfirmNewPasswordValueChange: (String) -> Unit,
    onSendClick: () -> Unit,
    modifier: Modifier = Modifier,
){
    Column (
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top,
        modifier = modifier
    ){
        Text(
            text = stringResource(R.string.insert_new_password),
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
        )
        Spacer(modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_small)))
        NewPasswordField(
            label = R.string.new_password,
            leadingIcon = Icons.Default.Lock,
            value = loginUiState.newPassword,
            onValueChange = onNewPasswordValueChange,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_medium)))
        Text(
            text = stringResource(R.string.confirm_new_password),
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
        )
        Spacer(modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_small)))
        NewPasswordField(
            label = R.string.new_password,
            leadingIcon = Icons.Default.Lock,
            value = loginUiState.confirmNewPassword,
            onValueChange = onConfirmNewPasswordValueChange,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            modifier = Modifier
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_medium)))
        Button(
            onClick = { onSendClick() },
            modifier = Modifier
                .align(Alignment.End)
                .width(dimensionResource(id = R.dimen.half_screen_size))
                .wrapContentHeight()
        ) {
            Text(
                text = stringResource(R.string.send),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_small))
            )
        }
        Spacer(modifier = Modifier.weight(5f))
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
        NewPasswordScreen(
            loginUiState = LoginUiState(),
            onNewPasswordValueChange = {},
            onConfirmNewPasswordValueChange = {},
            onSendClick = {},
            modifier = Modifier
                .fillMaxSize()
                .padding(dimensionResource(id = R.dimen.padding_medium))
        )
    }
}
@Preview(showBackground = true)
@Composable
fun NewPasswordDarkThemePreview() {
    CampusConnectTheme (darkTheme = true){
        NewPasswordScreen(
            loginUiState = LoginUiState(),
            onNewPasswordValueChange = {},
            onConfirmNewPasswordValueChange = {},
            onSendClick = {},
            modifier = Modifier
                .fillMaxSize()
                .padding(dimensionResource(id = R.dimen.padding_medium))
        )
    }
}

