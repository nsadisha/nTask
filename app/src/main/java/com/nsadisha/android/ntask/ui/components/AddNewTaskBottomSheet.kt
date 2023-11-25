package com.nsadisha.android.ntask.ui.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.nsadisha.android.ntask.R
import java.time.Instant
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.Calendar

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNewTaskBottomSheet(show: Boolean, onDismiss: () -> Unit) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var name by remember{ mutableStateOf("") }
    var description by remember{ mutableStateOf("") }
    var completeOn by remember{ mutableStateOf("") }

    var showDatePicker by remember{ mutableStateOf(false) }

    fun reset() {
        name = ""
        description = ""
        completeOn = ""
    }
    fun addTask() {

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
                    text = stringResource(id = R.string.nbs_title),
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
                )

                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text(stringResource(id = R.string.nbs_description_lbl)) },
                    placeholder = { Text(stringResource(id = R.string.nbs_description_hint)) },
                    singleLine = false,
                    minLines = 4,
                    modifier = Modifier.fillMaxWidth(),
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
                        label = stringResource(id = R.string.nbs_filled_btn_lbl),
                        modifier = Modifier.fillMaxWidth(),
                        onclick = { addTask() }
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