package com.app.campusconnect.ui.dashboard


import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.campusconnect.R
import com.app.campusconnect.data.uistate.dashboard.DashboardFormState
import com.app.campusconnect.theme.CampusConnectTheme

@Composable
fun EventCreationScreen(
    dashboardFormState: DashboardFormState,
    onTitleChange: (String) -> Unit,
    onLocationChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    modifier: Modifier = Modifier
){
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
    ){
        item {
            InsertEventImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .height(300.dp)
            )
        }
        item {
            Column (
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.padding_small))
            ){
                EventTitleInput(
                    title = dashboardFormState.eventCreated.title,
                    onTitleChange = onTitleChange,
                    modifier = Modifier
                        .padding(dimensionResource(id = R.dimen.padding_small))
                )
                EventDateInput(
                    dashboardFormState = dashboardFormState,
                    modifier = Modifier
                        .padding(dimensionResource(id = R.dimen.padding_small))
                )
                EventLocationInput(
                    location = dashboardFormState.eventCreated.location,
                    onLocationChange = onLocationChange,
                    modifier = Modifier
                        .padding(dimensionResource(id = R.dimen.padding_small))
                )
                EventDescriptionInput(
                    description = dashboardFormState.eventCreated.description,
                    onDescriptionChange = onDescriptionChange,
                    modifier = Modifier
                        .padding(dimensionResource(id = R.dimen.padding_small))
                )
            }
        }
    }
}

@Composable
fun InsertEventImage(
    modifier: Modifier = Modifier
){
    Box(
        modifier = modifier
    ){
        Image(
            painter = painterResource(id = R.drawable.campus_connect_logo),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()

        )
        Button(
            onClick = {},
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 10.dp
            ),
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.BottomStart)
        ) {
            Row (
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ){
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Adicionar evento",
                    tint = Color.White,
                    modifier = Modifier.padding(end = 20.dp)
                )
                Text(
                    text = "Adicione uma imagem",
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}

@Composable
fun EventTitleInput(
    title: String,
    onTitleChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = title,
        onValueChange = onTitleChange,
        label = { Text("Título do Evento") },
        modifier = modifier.fillMaxWidth()
    )
}
@Composable
fun EventDescriptionInput(
    description: String,
    onDescriptionChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = description,
        onValueChange = onDescriptionChange,
        label = { Text("Descrição") },
        modifier = modifier
            .fillMaxWidth()
            .height(150.dp),
        maxLines = 5
    )
}

@Composable
fun EventDateInput(
    dashboardFormState: DashboardFormState,
    modifier: Modifier = Modifier
) {
    Row {
        OutlinedTextField(
            value = dashboardFormState.eventCreated.eventDate,
            onValueChange = {
                Log.d("DashboardViewModel", "EventScreen: : OnValueChange$it")
            },
            label = { Text("Data") },
            modifier = modifier
                .weight(1f)
        )

        Button(
            onClick = {  },
            modifier = Modifier
                .align(Alignment.CenterVertically)
        ) {
            Text("Selecionar Data")
        }
    }

}

@Composable
fun EventLocationInput(
    location: String,
    onLocationChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = location,
        onValueChange = onLocationChange,
        label = { Text("Localização") },
        modifier = modifier.fillMaxWidth(),

    )
}

@Preview
@Composable
fun EventCreationScreenLightThemePreview(){
    CampusConnectTheme(darkTheme = false) {
        EventCreationScreen(
            dashboardFormState = DashboardFormState(),
            onTitleChange = {},
            onLocationChange = {},
            onDescriptionChange = {},
        )
    }
}
@Preview
@Composable
fun EventCreationScreenDarkThemePreview(){
    CampusConnectTheme(darkTheme = true) {
        EventCreationScreen(
            dashboardFormState = DashboardFormState(),
            onTitleChange = {},
            onLocationChange = {},
            onDescriptionChange = {},
        )
    }
}
