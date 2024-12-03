package com.app.campusconnect.ui.dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.campusconnect.R
import com.app.campusconnect.data.uistate.dashboard.DashboardFormState
import com.app.campusconnect.network.dashboard.models.Event
import com.app.campusconnect.ui.dashboard.components.EventCard
import com.app.campusconnect.ui.theme.CampusConnectTheme

@Composable
fun SearchScreen(
    dashboardFormState: DashboardFormState,
    onValueChange: (String) -> Unit,
    onEventClick: (Event) -> Unit,
    onSearch: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(dimensionResource(id = R.dimen.padding_medium))
    ) {
        SearchTextField(
            value = dashboardFormState.searchTerm,
            onValueChange = onValueChange,
            onSearch = { onSearch(dashboardFormState.searchTerm) }
        )

        Spacer(modifier = Modifier.height(16.dp))

        EventGrid(
            events = dashboardFormState.eventListFiltered,
            onEventClick = onEventClick
        )
    }
}

@Composable
fun SearchTextField(
    value: String,
    onValueChange: (String) -> Unit,
    onSearch: () -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        shape = MaterialTheme.shapes.large,
        placeholder = { Text(text = stringResource(id = R.string.search)) },
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(onSearch = { onSearch() }),
        trailingIcon = { Icon(imageVector = Icons.Filled.Search, contentDescription = "Search Icon") },
        modifier = modifier.fillMaxWidth()
    )
}

@Composable
fun EventGrid(
    events: List<Event>,
    onEventClick: (Event) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(dimensionResource(id = R.dimen.padding_small)) // Adiciona padding interno
    ) {
        items(events) { event ->
            EventCard(
                event = event,
                onEventClick = onEventClick,
                modifier = Modifier
                    .fillMaxWidth() // Garante que os cards ocupem toda a largura da coluna
                    .height(200.dp)
            )
        }
    }
}

@Preview
@Composable
fun SearchScreenLightThemePreview() {
    CampusConnectTheme(darkTheme = false) {
        SearchScreen(
            onValueChange = {},
            onSearch = {},
            dashboardFormState = DashboardFormState(),
            onEventClick = {}
        )
    }
}

@Preview
@Composable
fun SearchScreenDarkThemePreview() {
    CampusConnectTheme(darkTheme = true) {
        SearchScreen(
            onValueChange = {},
            onSearch = {},
            dashboardFormState = DashboardFormState(),
            onEventClick = {}
        )
    }
}