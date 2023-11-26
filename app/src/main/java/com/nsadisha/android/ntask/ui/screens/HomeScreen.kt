package com.nsadisha.android.ntask.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nsadisha.android.ntask.R
import com.nsadisha.android.ntask.ui.components.AddNewTaskFAB
import com.nsadisha.android.ntask.ui.components.CategoryCard
import com.nsadisha.android.ntask.ui.models.CategoryCardModel
import com.nsadisha.android.ntask.ui.models.TaskFilterType
import com.nsadisha.android.ntask.vm.OnboardingViewModel
import com.nsadisha.android.ntask.vm.TaskViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    addNewTaskCallback: () -> Unit = {},
    vm: OnboardingViewModel = viewModel(),
    taskViewModel: TaskViewModel = viewModel(),
    onFilter: () -> Unit = {}
) {
    val fName = vm.readName().split(" ")[0]
    val taskCounts by taskViewModel.taskHomeModel.observeAsState()

    val toDoData = CategoryCardModel(
        icon = R.drawable.todo_icon,
        label = "ToDo",
        count = taskCounts!!.todoCount
    )
    val completedData = CategoryCardModel(
        icon = R.drawable.completed_icon,
        label = "Completed",
        count = taskCounts!!.completedCount
    )
    val allTasksData = CategoryCardModel(
        icon = R.drawable.all_icon,
        label = "All",
        count = taskCounts!!.taskCount
    )

    fun filter(filter: TaskFilterType) {
        taskViewModel.updateFilterType(filter)
        onFilter()
    }

    Scaffold(
        floatingActionButton = { AddNewTaskFAB(onClick = addNewTaskCallback) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Text(
                    text = "Hi $fName!",
                    style = MaterialTheme.typography.bodyLarge.merge(textAlign = TextAlign.Center),
                    color = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier.fillMaxWidth()
                )
                Box(Modifier.height(3.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    CategoryCard(
                        modifier = Modifier.weight(1f),
                        data = toDoData,
                        onClick = { filter(TaskFilterType.TODO) }
                    )
                    CategoryCard(
                        modifier = Modifier.weight(1f),
                        data = completedData,
                        onClick = { filter(TaskFilterType.COMPLETED) }
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    CategoryCard(
                        modifier = Modifier.weight(1f),
                        data = allTasksData,
                        onClick = { filter(TaskFilterType.ALL) }
                    )
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}