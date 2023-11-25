package com.nsadisha.android.ntask.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.nsadisha.android.ntask.database.Task
import com.nsadisha.android.ntask.vm.TaskViewModel

@Composable
fun TaskListItem(
    task: Task,
    viewModel: TaskViewModel,
    onClick: () -> Unit = {}
) {
    val listItemColors = ListItemDefaults.colors(
        containerColor = MaterialTheme.colorScheme.surfaceTint,
        headlineColor = MaterialTheme.colorScheme.onSurface,
        supportingColor = MaterialTheme.colorScheme.secondary,
        trailingIconColor = MaterialTheme.colorScheme.onSurface
    )

    ListItem(
        colors = listItemColors,
        headlineContent = { Text(
            text = task.name,
            style = MaterialTheme.typography.titleSmall
        ) },
        supportingContent = { Text(
            text = task.description,
            style = MaterialTheme.typography.bodyLarge
        ) },
        leadingContent = {
            RadioButton(
                selected = task.completed,
                onClick = { viewModel.toggleCompletion(task) }
            )
        },
        modifier = Modifier.clickable(onClick = onClick),
        trailingContent = {
            Icon(
                Icons.Filled.KeyboardArrowRight,
                "Localized description"
            )
        }
    )
}