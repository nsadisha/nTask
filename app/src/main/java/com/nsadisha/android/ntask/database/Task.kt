package com.nsadisha.android.ntask.database

data class Task(
    val id: Int,
    var name: String,
    var description: String,
    var completeOn: String,
    var completed: Boolean
)
