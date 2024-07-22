package com.cusufcan.todolist.adapter

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.cusufcan.todolist.database.ToDoDao
import com.cusufcan.todolist.database.ToDoDatabase
import com.cusufcan.todolist.databinding.ToDoItemBinding
import com.cusufcan.todolist.model.ToDo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ToDoAdapter(private val toDoList: ArrayList<ToDo>) :
    RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder>() {
    class ToDoViewHolder(val binding: ToDoItemBinding) : RecyclerView.ViewHolder(binding.root)

    private lateinit var db: ToDoDatabase
    private lateinit var dao: ToDoDao

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        val binding = ToDoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        initDb(binding.root)
        return ToDoViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return toDoList.size
    }

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        val toDoItem = toDoList[position]

        holder.binding.toDoText.text = toDoItem.title
        holder.binding.toDoCheckBox.isChecked = toDoItem.getDone()
        changeTextView(toDoItem.getDone(), holder)

        holder.binding.toDoCheckBox.setOnCheckedChangeListener { _, isChecked ->
            toDoItem.setDone(isChecked)
            changeTextView(isChecked, holder)

            CoroutineScope(Dispatchers.Default).launch {
                withContext(Dispatchers.Main) {
                    dao.update(toDoItem.id, isChecked)
                }
            }
        }

        holder.binding.toDoDeleteButton.setOnClickListener {
            CoroutineScope(Dispatchers.Default).launch {
                withContext(Dispatchers.Main) {
                    dao.delete(toDoItem)
                    toDoList.removeAt(position)
                    notifyItemRemoved(position)
                }
            }
        }
    }

    private fun initDb(view: View) {
        db = Room.databaseBuilder(
            view.context.applicationContext, ToDoDatabase::class.java, "todo_database"
        ).build()

        dao = db.toDoDao()
    }

    private fun changeTextView(isCheck: Boolean, holder: ToDoViewHolder) {
        if (isCheck) {
            holder.binding.toDoText.paintFlags =
                holder.binding.toDoText.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        } else {
            holder.binding.toDoText.paintFlags =
                holder.binding.toDoText.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }
    }
}