package com.nsadisha.android.ntask.ui.components

import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsListItem(
    headlineLabel: String,
    supportingLabel: String? = null,
    leading: @Composable (() -> Unit)? = null,
    trailing: @Composable (() -> Unit)? = null,
    onClick: (() -> Unit) = {}
) {
    val listItemColors = ListItemDefaults.colors(
        containerColor = MaterialTheme.colorScheme.surfaceTint,
        headlineColor = MaterialTheme.colorScheme.onSurface,
        supportingColor = MaterialTheme.colorScheme.secondary,
        trailingIconColor = MaterialTheme.colorScheme.onSurface
    )

    Card(onClick = onClick) {
        ListItem(
            colors = listItemColors,
            headlineContent = { Text(
                text = headlineLabel,
                style = MaterialTheme.typography.titleSmall
            ) },
            supportingContent = {
                if (supportingLabel != null) {
                    Text(
                        text = supportingLabel,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            },
            leadingContent = leading,
            trailingContent = trailing
        )
    }
}