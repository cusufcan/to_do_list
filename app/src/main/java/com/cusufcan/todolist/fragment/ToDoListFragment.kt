package com.cusufcan.todolist.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.cusufcan.todolist.adapter.ToDoAdapter
import com.cusufcan.todolist.database.ToDoDao
import com.cusufcan.todolist.database.ToDoDatabase
import com.cusufcan.todolist.databinding.FragmentToDoListBinding
import com.cusufcan.todolist.model.ToDo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ToDoListFragment : Fragment() {
    private lateinit var binding: FragmentToDoListBinding
    private lateinit var toDoList: ArrayList<ToDo>

    private lateinit var db: ToDoDatabase
    private lateinit var dao: ToDoDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentToDoListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initDb(binding.root)
        CoroutineScope(Dispatchers.Default).launch {
            withContext(Dispatchers.Main) {
                context?.let {
                    toDoList = ArrayList(dao.getAll())
                    dao.getAll().also {
                        val adapter = ToDoAdapter(ArrayList(it.reversed()))
                        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
                        binding.recyclerView.adapter = adapter
                    }
                }
            }
        }

        binding.floatingActionButton.setOnClickListener {
            val action = ToDoListFragmentDirections.actionToDoListFragmentToToDoAddFragment()
            Navigation.findNavController(view).navigate(action)
        }
    }

    private fun initDb(view: View) {
        db = Room.databaseBuilder(
            view.context.applicationContext, ToDoDatabase::class.java, "todo_database"
        ).build()

        dao = db.toDoDao()
    }
}