package com.app.campusconnect.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import com.app.campusconnect.R

@Composable
fun LoadingScreen(
    modifier: Modifier
){
    Column (
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.padding_small))
        )
    }

}
@Composable
fun ErrorScreen(
    error: String,
    retryAction: () -> Unit,
    modifier: Modifier
){
    Column (
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Error",
            style = MaterialTheme.typography.displayLarge
        )
        Text(
            text = error,
            style = MaterialTheme.typography.titleSmall,
        )
        Button(
            onClick = retryAction,
            modifier = Modifier.padding(16.dp)
        ) {
            Text("Retry")
        }
    }
}