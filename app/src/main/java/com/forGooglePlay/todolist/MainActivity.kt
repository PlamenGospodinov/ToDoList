package com.forGooglePlay.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var toDoAdapter: ToDoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toDoAdapter = ToDoAdapter(mutableListOf())
        rvTodoItems.adapter = toDoAdapter
        rvTodoItems.layoutManager = LinearLayoutManager(this)

        btnAddToDo.setOnClickListener{
            val toDoTitle = etTodoTitle.text.toString()
            if(toDoTitle.isNotEmpty()){
                val todo = ToDo(toDoTitle)
                toDoAdapter.addToDo(todo)
                etTodoTitle.text.clear()
            }
        }

        RemoveToDos.setOnClickListener{
            toDoAdapter.deleteToDos()
        }
    }


}