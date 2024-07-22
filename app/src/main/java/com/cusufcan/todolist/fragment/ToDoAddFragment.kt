package com.cusufcan.todolist.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.room.Room
import com.cusufcan.todolist.database.ToDoDao
import com.cusufcan.todolist.database.ToDoDatabase
import com.cusufcan.todolist.databinding.FragmentToDoAddBinding
import com.cusufcan.todolist.model.ToDo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ToDoAddFragment : Fragment() {
    private lateinit var binding: FragmentToDoAddBinding

    private lateinit var db: ToDoDatabase
    private lateinit var dao: ToDoDao

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
        initDb(binding.root)

        binding.button.setOnClickListener {
            val title = binding.toDoTitleEditText.text.toString()

            if (title.isNotEmpty()) {
                val toDo = ToDo(title)
                println(toDo.title)
                println(toDo.getDone())

                CoroutineScope(Dispatchers.Default).launch {
                    withContext(Dispatchers.Main) {
                        context?.let {
                            db.toDoDao().insert(toDo)
                            val action =
                                ToDoAddFragmentDirections.actionToDoAddFragmentToToDoListFragment()
                            Navigation.findNavController(view).navigate(action)
                        }
                    }
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
}