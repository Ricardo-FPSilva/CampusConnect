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
import androidx.compose.material.icons.filled.AccountCircle
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.app.campusconnect.R
import com.app.campusconnect.ui.theme.CampusConnectTheme

@Composable
fun InsertRegistrationScreen(
    modifier: Modifier = Modifier,
) {
    Column (
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top,
        modifier = modifier
            .fillMaxSize()
            .padding(dimensionResource(id = R.dimen.padding_medium))

    ) {
        Spacer(modifier = Modifier.weight(3f))
        Text(
            text = stringResource(R.string.registration),
            style = MaterialTheme.typography.displayLarge,
            fontWeight = FontWeight.Bold,
        )
        Spacer(modifier = Modifier.weight(2f))
        Text(
            text = stringResource(R.string.digite_sua_matricula),
            style = MaterialTheme.typography.headlineSmall,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
        )
        Spacer(modifier = Modifier.weight(1f))
        InsertRegistrationField(
            label = R.string.registration,
            leadingIcon = Icons.Default.AccountCircle,
            value = "",
            onValueChange = {},
            keyboardOptions =  KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            modifier = Modifier
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_medium)))
        Button(
            onClick = { /*TODO*/ },
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
        Spacer(modifier = Modifier.weight(1f))
        Column {
            Text(
                text = stringResource(R.string.no_registration),
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = stringResource(R.string.instructions_to_no_registration),
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Justify,
            )
        }
        Spacer(modifier = Modifier.weight(1f))
    }
}
@Composable
fun InsertRegistrationField(
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
fun InsertRegistrationLightThemePreview() {
    CampusConnectTheme (darkTheme = false){
        InsertRegistrationScreen()
    }
}
@Preview(showBackground = true)
@Composable
fun InsertRegistrationDarkThemePreview() {
    CampusConnectTheme (darkTheme = true){
        InsertRegistrationScreen()
    }
}