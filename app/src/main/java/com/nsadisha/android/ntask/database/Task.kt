package com.nsadisha.android.ntask.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    var name: String,
    var description: String,
    var completeOn: String,
    var completed: Boolean
)
