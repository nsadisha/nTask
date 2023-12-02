package com.nsadisha.android.ntask.ui.components

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
import com.nsadisha.android.ntask.vm.OnboardingViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditNameBottomSheet(
    show: Boolean,
    onDismiss: () -> Unit,
    vm: OnboardingViewModel = viewModel()
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var name by remember{ mutableStateOf(vm.readName()) }

    fun reset() {
        name = vm.readName()
    }
    fun editName() {
        if(name.isNotEmpty()) {
            vm.writeName(name)
            onDismiss()
        }
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
                    text = stringResource(id = R.string.enbs_title),
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
                    placeholder = { Text(stringResource(id = R.string.setup_txt_hint)) },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(
                        onDone = { editName() }
                    )
                )

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(3.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ){
                    FilledButton(
                        label = stringResource(id = R.string.enbs_filled_btn_lbl),
                        modifier = Modifier.fillMaxWidth(),
                        onclick = { editName() }
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