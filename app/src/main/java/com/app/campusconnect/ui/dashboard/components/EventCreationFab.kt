package com.app.campusconnect.ui.dashboard.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.app.campusconnect.theme.CampusConnectTheme
import java.util.Vector

@Composable
fun EventCreationFab(
    imageVector: ImageVector,
    onFabClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    ConstraintLayout(modifier = modifier.fillMaxSize()) { // Adiciona o ConstraintLayout
        val (fab) = createRefs() // Cria uma referência para o FAB
        FloatingActionButton(
            onClick = onFabClick,
            modifier = Modifier
                .size(60.dp)
                .constrainAs(fab) { // Usa a referência criada
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end)
                }
        ) {
           Icon(
                imageVector = imageVector,
                contentDescription = "Adicionar evento",
                tint = Color.White
           )

        }
    }
}

@Preview
@Composable
fun EventCreationFabLightThemePreview() {
    CampusConnectTheme(darkTheme = false) {
        EventCreationFab(
            imageVector = Icons.Default.Add,
            onFabClick = {}
        )
    }
}

@Preview
@Composable
fun EventCreationFabDarkThemePreview() {
    CampusConnectTheme(darkTheme = true) {
        EventCreationFab(
            imageVector = Icons.Default.Add,
            onFabClick = {}
        )
    }
}

