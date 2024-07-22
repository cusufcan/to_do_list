package com.cusufcan.todolist.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.cusufcan.todolist.model.ToDo

@Database(entities = [ToDo::class], version = 1)
abstract class ToDoDatabase : RoomDatabase() {
    abstract fun toDoDao(): ToDoDao
}