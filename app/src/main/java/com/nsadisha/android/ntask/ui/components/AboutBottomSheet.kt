package com.nsadisha.android.ntask.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.nsadisha.android.ntask.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutBottomSheet(show: Boolean, onDismiss: () -> Unit) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    if (show) {
        ModalBottomSheet(
            onDismissRequest = onDismiss,
            sheetState = sheetState,
        ) {
            Column(modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 24.dp),
                verticalArrangement = Arrangement.spacedBy(36.dp, Alignment.Top),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "logo",
                    modifier = Modifier
                        .width(100.dp)
                        .height(100.dp)
                )

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Text(
                        text = "nTask",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = "Version 1.0.0",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.secondary
                    )
                }

                Text(
                    text = stringResource(id = R.string.abs_description),
                    style = MaterialTheme.typography.bodyLarge.merge(textAlign = TextAlign.Center),
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.padding(horizontal = 24.dp)
                )

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(3.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ){
                    FilledButton(
                        label = stringResource(id = R.string.abs_filled_btn_lbl),
                        modifier = Modifier.fillMaxWidth(),
                        onclick = { println("hello") }
                    )
                    OutlineButton(
                        label = stringResource(id = R.string.abs_outline_btn_lbl),
                        modifier = Modifier.fillMaxWidth(),
                        onclick = { println("hello") }
                    )
                }
            }
        }
    }
}