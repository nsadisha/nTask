package com.nsadisha.android.ntask.ui.models

import androidx.compose.runtime.Composable
data class NavItemModel(
    val label: String,
    val navLabel: String,
    val icon: @Composable () -> Unit,
    val view: @Composable () -> Unit
)
