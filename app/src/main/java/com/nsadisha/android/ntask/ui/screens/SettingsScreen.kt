package com.nsadisha.android.ntask.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
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
import com.nsadisha.android.ntask.ui.components.EditNameBottomSheet
import com.nsadisha.android.ntask.ui.components.SettingsListItem

@Composable
fun SettingsScreen() {
//    var isDarkMode by remember{ mutableStateOf(false) }
    var showAboutBottomSheet by remember { mutableStateOf(false) }
    var showEditNameBottomSheet by remember { mutableStateOf(false) }

    fun toggleAboutSheet() { showAboutBottomSheet = !showAboutBottomSheet }
    fun toggleEditNameSheet() { showEditNameBottomSheet = !showEditNameBottomSheet }

    Column(
        modifier = Modifier
            .fillMaxSize(),
//            .padding(top = 16.dp),
//        verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Top),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        SettingsListItem(
            "About",
            onClick = { showAboutBottomSheet = true },
            leading = { Icon(Icons.Filled.Info, contentDescription = "info", tint = MaterialTheme.colorScheme.primary)}
        )
        Divider(color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.5f))
        SettingsListItem(
            "Edit name",
            onClick = { showEditNameBottomSheet = true },
            leading = { Icon(Icons.Filled.Edit, contentDescription = "Edit name", tint = MaterialTheme.colorScheme.primary) }
        )
        //        SettingsListItem(
//            headlineLabel = "Dark mode",
//            trailing = { Switch(
//                checked = isDarkMode,
//                colors = SwitchDefaults.colors(
//                    uncheckedBorderColor = MaterialTheme.colorScheme.primary,
//                    uncheckedTrackColor = Color.Transparent,
//                    uncheckedThumbColor = MaterialTheme.colorScheme.primary,
//                    checkedThumbColor = MaterialTheme.colorScheme.background
//                ),
//                onCheckedChange = {
//                    isDarkMode = it
//                }
//            ) },
//            onClick = {isDarkMode = !isDarkMode}
//        )

        AboutBottomSheet(show = showAboutBottomSheet, onDismiss = { toggleAboutSheet() })
        EditNameBottomSheet(show = showEditNameBottomSheet, onDismiss = { toggleEditNameSheet() })
    }
}