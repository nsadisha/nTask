package com.nsadisha.android.ntask.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nsadisha.android.ntask.database.Task
import com.nsadisha.android.ntask.ui.models.TaskHomeModel

class TaskViewModel(application: Application) : AndroidViewModel(application) {
    private var _tasks = MutableLiveData<List<Task>>(listOf())
    var tasks: LiveData<List<Task>> = _tasks

    private var _taskHomeModel = MutableLiveData(
        TaskHomeModel(
            taskCount = _tasks.value!!.size,
            completedCount = tasks.value!!.filter { it.completed }.size,
            todoCount = tasks.value!!.filter { !it.completed }.size
        )
    )
    var taskHomeModel: LiveData<TaskHomeModel> = _taskHomeModel

    fun addTask(task: Task) {
        val newTask = Task(
            id = taskHomeModel.value!!.taskCount.plus(1),
            name = task.name,
            description = task.description,
            completed = task.completed,
            completeOn = task.completeOn
        )
        val updatedTasks = _tasks.value!!.plus(newTask).toMutableList()
        _tasks.value = updatedTasks
        updateTaskHomeModel()
    }

    fun updateTask(task: Task){
        val newTask = task.copy()

        val index = _tasks.value!!.indexOfFirst { it.id == newTask.id }
        if(index != -1){
            val updatedTasks = _tasks.value!!.toMutableList()
            updatedTasks[index] = newTask
            _tasks.value = updatedTasks
        }
        updateTaskHomeModel()
    }

    fun toggleCompletion(task: Task){
        val newTask = task.copy()
        newTask.completed = !newTask.completed

        updateTask(newTask)
    }

    private fun updateTaskHomeModel() {
        val newModel = TaskHomeModel(
            taskCount = _tasks.value!!.size,
            completedCount = tasks.value!!.filter { it.completed }.size,
            todoCount = tasks.value!!.filter { !it.completed }.size
        )

        _taskHomeModel.value = newModel
    }
}