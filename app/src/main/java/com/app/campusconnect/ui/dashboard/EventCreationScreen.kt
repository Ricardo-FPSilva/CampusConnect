package com.app.campusconnect.ui.dashboard


import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.campusconnect.R
import com.app.campusconnect.ui.theme.CampusConnectTheme
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@Composable
fun EventCreationScreen(
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
                    title = "",
                    onTitleChange = {},
                    modifier = Modifier
                        .padding(dimensionResource(id = R.dimen.padding_small))
                )
                EventDateInput(
                    onDateSelected = {},
                    modifier = Modifier
                        .padding(dimensionResource(id = R.dimen.padding_small))
                )
                EventLocationInput(
                    location = "",
                    onLocationChange = {},
                    modifier = Modifier
                        .padding(dimensionResource(id = R.dimen.padding_small))
                )
                EventDescriptionInput(
                    description = "",
                    onDescriptionChange = {},
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
    onDateSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    val dateFormatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    var selectedDate by remember { mutableStateOf(dateFormatter.format(calendar.time)) }
    var showDialog by remember { mutableStateOf(false) }


    Row {
        OutlinedTextField(
            value = selectedDate,
            onValueChange = { },
            label = { Text("Data") },
            modifier = modifier
                .weight(1f)
                .clickable { showDialog = true },
            readOnly = true
        )

        Button(
            onClick = { showDialog = true },
            modifier = Modifier
                .align(Alignment.CenterVertically)
        ) {
            Text("Selecionar Data")
        }
    }

    if (showDialog) {
        val datePickerDialog = DatePickerDialog(
            context,
            { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
                calendar.set(year, month, dayOfMonth)

                selectedDate = dateFormatter.format(calendar.time)
                onDateSelected(selectedDate)
                showDialog = false
            },
            year,
            month,
            day
        )

        // Define o OnCancelListener
        datePickerDialog.setOnCancelListener {
            showDialog = false
        }

        datePickerDialog.show()
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
        // Aqui você pode adicionar lógica para mostrar sugestões de endereço
        // usando o Google Places API ou outro serviço similar
    )
}

@Preview
@Composable
fun EventCreationScreenLightThemePreview(){
    CampusConnectTheme(darkTheme = false) {
        EventCreationScreen()
    }
}
@Preview
@Composable
fun EventCreationScreenDarkThemePreview(){
    CampusConnectTheme(darkTheme = true) {
        EventCreationScreen()
    }
}
