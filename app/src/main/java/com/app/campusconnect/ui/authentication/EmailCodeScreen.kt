package com.app.campusconnect.ui.authentication

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
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.app.campusconnect.R
import com.app.campusconnect.data.uistate.authentication.AuthFormState
import com.app.campusconnect.theme.CampusConnectTheme

@Composable
fun EmailCodeScreen(
    authFormState: AuthFormState,
    onVerifyClick: () -> Unit,
    onValueChange: (AuthFormState) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top,
        modifier = modifier
            .fillMaxSize()
            .padding(dimensionResource(id = R.dimen.padding_medium))
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(R.string.verifique_seu_email),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = authFormState.email, // Acessando email do authFormState
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.End)
            )
            Spacer(modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_large)))
            Text(
                text = stringResource(id = R.string.code),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_small))
            )
            EmailCodeField(
                label = R.string.code_format,
                value = authFormState.emailCode, // Acessando emailCode do authFormState
                onValueChange = { newValue ->
                    onValueChange(authFormState.copy(emailCode = newValue))
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                modifier = Modifier
                    .width(dimensionResource(id = R.dimen.text_field_width))
                    .wrapContentHeight()
                    .padding(bottom = dimensionResource(id = R.dimen.padding_large))
            )
            Text(
                text = stringResource(R.string.nao_recebeu_codigo),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_small))
            )
            TextButton(
                onClick = { /*TODO*/ }
            ) {
                Text(
                    text = stringResource(R.string.send_again),
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_small))

                )
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = { onVerifyClick() },
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
        ) {
            Text(
                text = stringResource(R.string.verify),
                style = MaterialTheme.typography.displaySmall,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_small))
            )
        }
        Spacer(modifier = Modifier.weight(2f))
    }
}

@Composable
fun EmailCodeField(
    @StringRes label: Int,
    value: String,
    onValueChange: (String) -> Unit,
    keyboardOptions: KeyboardOptions,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        singleLine = true,
        label = { Text(stringResource(label)) },
        value = value,
        onValueChange = onValueChange,
        keyboardOptions = keyboardOptions,
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun EmailCodeLightThemePreview() {
    CampusConnectTheme(darkTheme = false) {
        EmailCodeScreen(
            authFormState = AuthFormState(),
            onVerifyClick = {},
            onValueChange = {},
            modifier = Modifier
        )
    }
}

@Preview(showBackground = true)
@Composable
fun EmailCodeDarkThemePreview() {
    CampusConnectTheme(darkTheme = true) {
        EmailCodeScreen(
            authFormState = AuthFormState(),
            onVerifyClick = {},
            onValueChange = {},
            modifier = Modifier
                .fillMaxSize()
                .padding(dimensionResource(id = R.dimen.padding_medium))
        )
    }
}