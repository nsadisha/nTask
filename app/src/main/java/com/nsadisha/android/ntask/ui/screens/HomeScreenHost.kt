package com.nsadisha.android.ntask.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nsadisha.android.ntask.ui.components.AddNewTaskBottomSheet
import com.nsadisha.android.ntask.ui.models.NavItemModel
import com.nsadisha.android.ntask.ui.models.TaskFilterType
import com.nsadisha.android.ntask.vm.TaskViewModel

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenHost(
    vm: TaskViewModel = viewModel()
) {
    var selectedItem by remember { mutableIntStateOf(1) }
    var showBottomSheet by remember { mutableStateOf(false) }
    val filterType by vm.filterType.observeAsState()

    val tasksTitle = when(filterType) {
        TaskFilterType.COMPLETED -> "Completed"
        TaskFilterType.TODO -> "ToDo"
        else -> "All"
    }

    fun toggleAboutSheet() { showBottomSheet = !showBottomSheet }

    fun showTasksScreen(){ selectedItem = 1 }

    val items = listOf(
        NavItemModel(
            label = "Home",
            navLabel = "Home",
            icon = { Icon(Icons.Filled.Home, contentDescription = "Home") },
            view = { HomeScreen({ toggleAboutSheet() }, onFilter = { showTasksScreen() }) }
        ),
        NavItemModel(
            label = tasksTitle,
            navLabel = "Tasks",
            icon = { Icon(Icons.Filled.CheckCircle, contentDescription = "Tasks") },
            view = { TasksScreen({ toggleAboutSheet() }) }
        ),
        NavItemModel(
            label = "Settings",
            navLabel = "Settings",
            icon = { Icon(Icons.Filled.Settings, contentDescription = "Settings") },
            view = { SettingsScreen() },
        )
    )

    Scaffold(
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
//                    scrolledContainerColor = MaterialTheme.colorScheme.applyTonalElevation(
//                        backgroundColor = containerColor,
//                        elevation = TopAppBarSmallTokens.OnScrollContainerElevation
//                    )
                ),
                title = {
                    Text(
                        items[selectedItem].label,
                        modifier = Modifier.padding(vertical = 32.dp),
                        color = MaterialTheme.colorScheme.background,
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            )
        },
    ) { innerPadding ->
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(top = innerPadding.calculateTopPadding())
        ){
            Column(modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){
                items[selectedItem].view.invoke()
            }

            NavigationBar {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        icon = item.icon,
                        label = { Text(
                            text = item.navLabel,
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurface
                        ) },
                        selected = selectedItem == index,
                        onClick = { selectedItem = index }
                    )
                }
            }

            AddNewTaskBottomSheet(show = showBottomSheet, onDismiss = { toggleAboutSheet() })
        }
    }
}