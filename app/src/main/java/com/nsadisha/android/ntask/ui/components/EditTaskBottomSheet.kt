package com.nsadisha.android.ntask.ui.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nsadisha.android.ntask.R
import com.nsadisha.android.ntask.database.Task
import com.nsadisha.android.ntask.vm.TaskViewModel

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditTaskBottomSheet(
    show: Boolean,
    onDismiss: () -> Unit,
    task: Task,
    vm: TaskViewModel = viewModel()
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var name by remember{ mutableStateOf(task.name) }
    var description by remember{ mutableStateOf(task.description) }
    var completeOn by remember{ mutableStateOf(task.completeOn) }

    var showDatePicker by remember{ mutableStateOf(false) }

    fun reset() {
        name = task.name
        description = task.description
        completeOn = task.completeOn
    }
    fun editTask() {
        if(name.isNotEmpty()) {
            val updatingTask = task.copy(
                name = name,
                description = description,
                completeOn = completeOn
            )

            vm.updateTask(updatingTask).also {
                reset()
                onDismiss()
            }
        }
    }

    ModalDatePicker(
        show = showDatePicker,
        onDismiss = { showDatePicker = false },
        onSelect = { completeOn = it }
    )

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
                    text = stringResource(id = R.string.ebs_title),
                    style = MaterialTheme.typography.titleMedium.merge(textAlign = TextAlign.Start),
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp)
                )

                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text(stringResource(id = R.string.nbs_name_lbl)) },
                    placeholder = { Text(stringResource(id = R.string.nbs_name_hint)) },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
                )

                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text(stringResource(id = R.string.nbs_description_lbl)) },
                    placeholder = { Text(stringResource(id = R.string.nbs_description_hint)) },
                    singleLine = false,
                    minLines = 4,
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                    keyboardActions = KeyboardActions(
                        onNext = { showDatePicker = true }
                    )
                )

                Column {
                    OutlinedTextField(
                        value = completeOn,
                        onValueChange = { completeOn = it },
                        label = { Text(stringResource(id = R.string.nbs_complete_lbl)) },
                        placeholder = { Text(stringResource(id = R.string.nbs_complete_hint)) },
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable(enabled = true) {
                                showDatePicker = true
                            },
                        enabled = false,
                    )
                    Text(
                        text = stringResource(id = R.string.nbs_complete_support),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.secondary
                    )
                }

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(3.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ){
                    FilledButton(
                        label = stringResource(id = R.string.ebs_filled_btn_lbl),
                        modifier = Modifier.fillMaxWidth(),
                        onclick = { editTask() }
                    )
                    OutlineButton(
                        label = stringResource(id = R.string.nbs_outline_btn_lbl),
                        modifier = Modifier.fillMaxWidth(),
                        onclick = { reset() }
                    )
                }
            }
        }
    }
}