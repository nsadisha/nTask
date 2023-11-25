package com.nsadisha.android.ntask.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nsadisha.android.ntask.database.Task
import com.nsadisha.android.ntask.ui.models.TaskHomeModel

class TaskViewModel(application: Application) : AndroidViewModel(application) {
    private var _tasks = MutableLiveData(listOf(
        Task(1, "Task 1", "Description 1", "2023-12-12", true),
        Task(2, "Task 2", "Description 2", "2023-12-12", true),
        Task(3, "Task 3", "Description 3", "2023-12-12", true),
        Task(4, "Task 4", "Description 4", "2023-12-12", false),
        Task(5, "Task 5", "Description 5", "2023-12-12", false),
        Task(6, "Task 6", "Description 6", "2024-01-01", false)
    ))
    var tasks: LiveData<List<Task>> = _tasks
    var taskHomeModel: LiveData<TaskHomeModel> = MutableLiveData(_tasks.value?.let { it ->
        TaskHomeModel(
        taskCount = it.size,
        completedCount = it.filter { it.completed }.size,
        todoCount = it.filter { !it.completed }.size
    )
    })

    fun updateTask(task: Task){
        val newTask = task.copy()

        val index = _tasks.value!!.indexOfFirst { it.id == newTask.id }
        if(index != -1){
            val updatedTasks = _tasks.value!!.toMutableList()
            updatedTasks[index] = newTask
            _tasks.value = updatedTasks
        }
    }
    fun toggleCompletion(task: Task){
        val newTask = task.copy()
        newTask.completed = !newTask.completed

        updateTask(newTask)
    }
}