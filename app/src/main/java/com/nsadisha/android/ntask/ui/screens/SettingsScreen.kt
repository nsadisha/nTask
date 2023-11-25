package com.nsadisha.android.ntask.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.nsadisha.android.ntask.ui.components.AboutBottomSheet
import com.nsadisha.android.ntask.ui.components.SettingsListItem

@Composable
fun SettingsScreen() {
    var isDarkMode by remember{ mutableStateOf(false) }
    var showBottomSheet by remember { mutableStateOf(false) }

    fun toggleAboutSheet() { showBottomSheet = !showBottomSheet }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Top),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        SettingsListItem(
            headlineLabel = "Dark mode",
            trailing = { Switch(
                checked = isDarkMode,
                colors = SwitchDefaults.colors(
                    uncheckedBorderColor = MaterialTheme.colorScheme.primary,
                    uncheckedTrackColor = Color.Transparent,
                    uncheckedThumbColor = MaterialTheme.colorScheme.primary,
                    checkedThumbColor = MaterialTheme.colorScheme.background
                ),
                onCheckedChange = {
                    isDarkMode = it
                }
            ) },
            onClick = {isDarkMode = !isDarkMode}
        )
        SettingsListItem("About", onClick = {
            showBottomSheet = true
        })

        AboutBottomSheet(show = showBottomSheet, onDismiss = { toggleAboutSheet() })
    }
}