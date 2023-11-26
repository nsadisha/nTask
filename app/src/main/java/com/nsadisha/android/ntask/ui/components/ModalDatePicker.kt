package com.nsadisha.android.ntask.ui.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.nsadisha.android.ntask.R
import java.time.Instant
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModalDatePicker(
    show: Boolean,
    onDismiss: () -> Unit,
    onSelect: (String) -> Unit
) {
    val formatter = stringResource(id = R.string.nbs_date_formatter)
    val date = rememberDatePickerState(
        initialSelectedDateMillis = Instant.now().toEpochMilli()
    )
    val selectedDate = date.selectedDateMillis?.let {
        Instant.ofEpochMilli(it)
            .atOffset(ZoneOffset.UTC)
            .format(DateTimeFormatter.ofPattern(formatter))
    }

    if (show) {
        DatePickerDialog(
            shape = RoundedCornerShape(28.dp),
            confirmButton = {
                TextButton(
                    onClick = { onSelect.invoke(selectedDate.orEmpty()).also { onDismiss() } }
                ) {
                    Text("Ok", color = MaterialTheme.colorScheme.primary)
                }
            },
            dismissButton = {
                TextButton(onClick = { onDismiss() }) {
                    Text("Cancel", color = MaterialTheme.colorScheme.primary)
                }
            }
            , onDismissRequest = { onDismiss() }
        ) {
            DatePicker(
                state = date
            )
        }
    }
}