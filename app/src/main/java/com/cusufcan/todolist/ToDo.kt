package com.cusufcan.todolist

class ToDo(val title: String, private var isDone: Boolean = false) {
    fun setDone(isDone: Boolean) {
        this.isDone = isDone
    }

    fun getDone(): Boolean = isDone
}