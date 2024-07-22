package com.cusufcan.todolist.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class ToDo(
    @ColumnInfo(name = "title") val title: String,

    @ColumnInfo(name = "is_done") private var isDone: Boolean = false,
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    fun setDone(isDone: Boolean) {
        this.isDone = isDone
    }

    fun getDone(): Boolean = isDone
}