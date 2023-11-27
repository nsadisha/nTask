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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import com.nsadisha.android.ntask.R
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

    fun toggleCompletion() {
        val newTask = task.copy()
        newTask.completed = !newTask.completed
        viewModel.updateTask(newTask)
    }

    ListItem(
        colors = listItemColors,
        headlineContent = { Text(
            text = task.name,
            style = MaterialTheme.typography.titleSmall,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        ) },
        supportingContent = { Text(
            text = task.description.ifEmpty {
                stringResource(id = R.string.tbs_empty_description_message) },
            style = MaterialTheme.typography.bodyLarge,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        ) },
        leadingContent = {
            RadioButton(
                selected = task.completed,
                onClick = { toggleCompletion() }
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