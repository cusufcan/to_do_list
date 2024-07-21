package com.cusufcan.todolist.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.cusufcan.todolist.ToDo
import com.cusufcan.todolist.databinding.FragmentToDoAddBinding

class ToDoAddFragment : Fragment() {
    private lateinit var binding: FragmentToDoAddBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentToDoAddBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.button.setOnClickListener {
            val title = binding.toDoTitleEditText.text.toString()

            if (title.isNotEmpty()) {
                val toDo = ToDo(title)
                println(toDo.title)
                println(toDo.getDone())

                // TODO("DB OPERATIONS")
            }
        }
    }
}