package com.app.campusconnect.ui.dashboard.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.app.campusconnect.R

@Composable
fun EventNotFound() {
    Text(
        text = "Evento não encontrado.",
        style = MaterialTheme.typography.bodyLarge, // ou outro estilo adequado
        modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_medium))
    )
}