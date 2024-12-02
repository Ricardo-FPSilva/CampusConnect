package com.app.campusconnect.ui.dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
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

@OptIn(ExperimentalMaterial3Api::class)
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
        OutlinedTextField (
            value = dashboardFormState.searchTerm,
            onValueChange = onValueChange,
            singleLine = true,
            shape = MaterialTheme.shapes.large,
            placeholder = {
                Text(
                    text = stringResource(id = R.string.search)
                )
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    onSearch(dashboardFormState.searchTerm)
                }
            ),
            trailingIcon = {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Search Icon"
                )
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(dashboardFormState.eventListFiltered) { event ->
                EventCard(
                    event = event,
                    onEventClick = onEventClick,
                    modifier = Modifier
                        .padding(dimensionResource(id = R.dimen.padding_small))
                        .width(300.dp)
                        .height(200.dp)
                )
            }
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