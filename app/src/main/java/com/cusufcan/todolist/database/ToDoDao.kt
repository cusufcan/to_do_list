package com.cusufcan.todolist.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.cusufcan.todolist.model.ToDo

@Dao
interface ToDoDao {
    @Query("SELECT * FROM ToDo")
    suspend fun getAll(): List<ToDo>

    @Insert
    suspend fun insert(toDo: ToDo)

    @Delete
    suspend fun delete(toDo: ToDo)

    @Query("UPDATE ToDo SET is_done = :isDone WHERE id = :id")
    suspend fun update(id: Int, isDone: Boolean)
}