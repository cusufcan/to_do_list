package com.cusufcan.todolist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cusufcan.todolist.ToDo
import com.cusufcan.todolist.databinding.ToDoItemBinding

class ToDoAdapter(private val toDoList: List<ToDo>) :
    RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder>() {
    class ToDoViewHolder(val binding: ToDoItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        val binding = ToDoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ToDoViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return toDoList.size
    }

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        val toDoItem = toDoList[position]

        holder.binding.toDoText.text = toDoItem.title
        holder.binding.toDoCheckBox.isChecked = toDoItem.getDone()

        holder.binding.toDoCheckBox.setOnCheckedChangeListener { _, isChecked ->
            toDoItem.setDone(isChecked)
            notifyItemChanged(position)
        }
    }
}