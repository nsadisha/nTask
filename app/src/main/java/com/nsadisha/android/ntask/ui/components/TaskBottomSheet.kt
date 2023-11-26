package com.nsadisha.android.ntask.ui.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.nsadisha.android.ntask.R
import com.nsadisha.android.ntask.database.Task
import com.nsadisha.android.ntask.vm.TaskViewModel

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskBottomSheet(
    show: Boolean,
    onDismiss: () -> Unit,
    task: Task,
    viewModel: TaskViewModel
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var showUpdateTaskSheet by remember{ mutableStateOf(false) }

    fun toggleCompletion() {
        val newTask = task.copy()
        newTask.completed = !newTask.completed
        viewModel.updateTask(newTask)

        onDismiss()
    }

    if (show) {
        ModalBottomSheet(
            onDismissRequest = onDismiss,
            sheetState = sheetState,
        ) {
            Column(modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = task.name,
                    style = MaterialTheme.typography.titleMedium.merge(textAlign = TextAlign.Start),
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.fillMaxWidth()
                )

                Text(
                    text = task.description.ifEmpty {
                        stringResource(id = R.string.tbs_empty_description_message) },
                    style = MaterialTheme.typography.bodyLarge.merge(textAlign = TextAlign.Start),
                    color = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier.fillMaxWidth()
                )
                
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Text(
                        text = stringResource(id = R.string.tbs_complete_on_lbl),
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = task.completeOn,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.secondary
                    )
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(3.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ){
                    FilledButton(
                        label = stringResource(
                            id = when(task.completed){
                                false -> R.string.tbs_complete_lbl
                                true -> R.string.tbs_todo_lbl
                            }
                        ),
                        modifier = Modifier.fillMaxWidth(),
                        onclick = { toggleCompletion() }
                    )
                    OutlineButton(
                        label = stringResource(id = R.string.tbs_outline_btn_lbl),
                        modifier = Modifier.fillMaxWidth(),
                        onclick = { showUpdateTaskSheet = true }
                    )
                }
            }
        }

        EditTaskBottomSheet(
            show = showUpdateTaskSheet,
            onDismiss = {
                showUpdateTaskSheet = false
                onDismiss()
            },
            task = task
        )
    }
}