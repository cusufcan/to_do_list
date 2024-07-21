package com.cusufcan.todolist.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.cusufcan.todolist.ToDo
import com.cusufcan.todolist.adapter.ToDoAdapter
import com.cusufcan.todolist.databinding.FragmentToDoListBinding

class ToDoListFragment : Fragment() {
    private lateinit var binding: FragmentToDoListBinding
    private lateinit var toDoList: List<ToDo>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        toDoList = listOf()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentToDoListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ToDoAdapter(toDoList)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter

        binding.floatingActionButton.setOnClickListener {
            val action = ToDoListFragmentDirections.actionToDoListFragmentToToDoAddFragment()
            Navigation.findNavController(view).navigate(action)
        }
    }
}