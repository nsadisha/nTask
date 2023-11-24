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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nsadisha.android.ntask.R
import com.nsadisha.android.ntask.ui.components.AddNewNoteFAB
import com.nsadisha.android.ntask.ui.components.CategoryCard
import com.nsadisha.android.ntask.ui.models.CategoryCardModel
import com.nsadisha.android.ntask.vm.OnboardingViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    vm: OnboardingViewModel = viewModel(),
) {
    val fName = vm.readName().split(" ")[0]
    val toDoData = CategoryCardModel(
        icon = R.drawable.todo_icon,
        label = "ToDo",
        count = 7
    )
    val completedData = CategoryCardModel(
        icon = R.drawable.completed_icon,
        label = "Completed",
        count = 7
    )
    val allTasksData = CategoryCardModel(
        icon = R.drawable.all_icon,
        label = "All Tasks",
        count = 7
    )

    Scaffold(
        floatingActionButton = { AddNewNoteFAB() }
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
                    CategoryCard(modifier = Modifier.weight(1f), toDoData)
                    CategoryCard(modifier = Modifier.weight(1f), completedData)
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    CategoryCard(modifier = Modifier.weight(1f), allTasksData)
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}