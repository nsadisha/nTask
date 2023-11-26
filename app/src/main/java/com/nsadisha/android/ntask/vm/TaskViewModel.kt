package com.nsadisha.android.ntask.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.nsadisha.android.ntask.database.AppDatabase
import com.nsadisha.android.ntask.database.Task
import com.nsadisha.android.ntask.ui.models.TaskFilterType
import com.nsadisha.android.ntask.ui.models.TaskHomeModel
import kotlinx.coroutines.launch

class TaskViewModel(application: Application) : AndroidViewModel(application) {
    private val taskDao by lazy { AppDatabase.getDatabase(application).taskDao() }

    private var _tasks = MutableLiveData<List<Task>>(listOf())
    var tasks: LiveData<List<Task>> = _tasks

    private var _filterType = MutableLiveData(TaskFilterType.TODO)
    var filterType: LiveData<TaskFilterType> = _filterType

    init {
        updateTasks()
    }

    private var _taskHomeModel = MutableLiveData(
        TaskHomeModel(
            taskCount = _tasks.value!!.size,
            completedCount = tasks.value!!.filter { it.completed }.size,
            todoCount = tasks.value!!.filter { !it.completed }.size
        )
    )
    var taskHomeModel: LiveData<TaskHomeModel> = _taskHomeModel

    fun addTask(task: Task) {
        viewModelScope.launch {
            taskDao.insert(task).also { updateTasks() }
        }
    }

    fun updateTask(task: Task){
        viewModelScope.launch {
            taskDao.update(task).also { updateTasks() }
        }
    }

    fun updateFilterType(filterType: TaskFilterType) {
        _filterType.value = filterType
    }

    private fun updateTaskHomeModel() {
        val newModel = TaskHomeModel(
            taskCount = _tasks.value!!.size,
            completedCount = tasks.value!!.filter { it.completed }.size,
            todoCount = tasks.value!!.filter { !it.completed }.size
        )

        _taskHomeModel.value = newModel
    }

    private fun updateTasks(){
        viewModelScope.launch {
            _tasks.value = taskDao.getAllTasks()
            updateTaskHomeModel()
        }
    }
}