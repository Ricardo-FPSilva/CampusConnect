package com.app.campusconnect.ui.dashboard.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.app.campusconnect.R
import com.app.campusconnect.models.dashboard.Event

@Composable
fun EventCard(
    event: Event,
    onEventClick: (Event) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer),
        elevation = CardDefaults.cardElevation(defaultElevation =  dimensionResource(R.dimen.elevation_small))
    ) {
        OutlinedButton(
            onClick = { onEventClick(event) },
            shape = MaterialTheme.shapes.medium,
            contentPadding = PaddingValues(0.dp),
            border = ButtonDefaults.outlinedButtonBorder(false),
            modifier = Modifier.fillMaxSize()
        ) {
            Column (
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ){
                Box(modifier = Modifier.weight(2f)){
                    Image(
                        painter = painterResource(id = R.drawable.campus_connect_logo),
                        contentDescription = "",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxSize()
                    )
                }
                Text(
                    text = event.title,
                    style = MaterialTheme.typography.titleSmall,
                    textAlign = TextAlign.Center,

                    color = MaterialTheme.colorScheme.onTertiaryContainer,
                    fontWeight = FontWeight.Bold,
                )
            }
        }
    }
}