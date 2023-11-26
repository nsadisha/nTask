package com.nsadisha.android.ntask.ui.screens

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nsadisha.android.ntask.R
import com.nsadisha.android.ntask.database.Task
import com.nsadisha.android.ntask.ui.components.AddNewTaskFAB
import com.nsadisha.android.ntask.ui.components.EmptyState
import com.nsadisha.android.ntask.ui.components.TaskBottomSheet
import com.nsadisha.android.ntask.ui.components.TaskListItem
import com.nsadisha.android.ntask.ui.models.TaskFilterType
import com.nsadisha.android.ntask.vm.TaskViewModel

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TasksScreen(
    addNewTaskCallback: () -> Unit = {},
    vm: TaskViewModel = viewModel()
) {
    var currentTask by remember { mutableStateOf<Task?>(null) }
    var showBottomSheet by remember{ mutableStateOf(false) }
    var query by remember{ mutableStateOf("") }

    val tasks by vm.tasks.observeAsState()
    val filterType by vm.filterType.observeAsState()

    val searchedTasks = tasks?.filter {
        it.name.contains(query, ignoreCase = true)
            .or(it.description.contains(query, ignoreCase = true))
    }

    val filteredTasks = when(filterType) {
            TaskFilterType.COMPLETED -> searchedTasks?.filter { it.completed }
            TaskFilterType.TODO -> searchedTasks?.filter { !it.completed }
            else -> searchedTasks
    }

    Scaffold(
        floatingActionButton = { AddNewTaskFAB(onClick = addNewTaskCallback) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(0.dp, top = 0.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Top),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Box(Modifier.padding(vertical = 8.dp, horizontal = 16.dp)){
                OutlinedTextField(
                    value = query,
                    onValueChange = { query = it },
                    label = { Text(stringResource(id = R.string.tasks_txt_lbl)) },
                    placeholder = { Text(stringResource(id = R.string.tasks_txt_hint)) },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    leadingIcon = { Icon(
                        Icons.Filled.Search, "Search",
                    ) }
                )
            }

            if (tasks?.isEmpty() == true){
                EmptyState(stringResource(id = R.string.tasks_empty_message))
            } else if (searchedTasks?.isEmpty() == true){
                EmptyState(stringResource(id = R.string.tasks_search_empty_message))
            } else if (filteredTasks?.isEmpty() == true && filterType == TaskFilterType.TODO) {
                EmptyState(stringResource(id = R.string.filtered_tasks_empty_message))
            }else {
                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Top),
                ) {
                    filteredTasks?.let { fTasks ->
                        items(
                            items = fTasks,
                            key = {task -> task.id}
                        ) { task ->
                            TaskListItem(task, vm, onClick = {
                                currentTask = task
                                showBottomSheet = true
                            })
                        }
                    }
                }
            }

            currentTask?.let {
                TaskBottomSheet(
                    show = showBottomSheet,
                    onDismiss = {
                        showBottomSheet = false
                        currentTask = null
                    },
                    viewModel = vm,
                    task = currentTask!!
                )
            }
        }
    }
}