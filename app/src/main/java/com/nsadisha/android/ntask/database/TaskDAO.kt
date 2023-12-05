package com.nsadisha.android.ntask.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.RoomWarnings
import androidx.room.Update

@Dao
interface TaskDao {
    @Insert
    suspend fun insert(task: Task)

    @Query("SELECT * FROM tasks ORDER BY completeOn")
    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    suspend fun getAllTasks(): List<Task>

    @Update
    suspend fun update(task: Task)

    @Delete
    suspend fun delete(task: Task)
}
