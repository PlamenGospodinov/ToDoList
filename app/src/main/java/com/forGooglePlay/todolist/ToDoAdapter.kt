package com.forGooglePlay.todolist

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.icu.text.CaseMap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_todo.view.*

class ToDoAdapter(
    private val todos: MutableList<ToDo>
) : RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder>()
{
    class ToDoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        return  ToDoViewHolder(
                LayoutInflater.from(parent.context).inflate(
                        R.layout.item_todo,
                        parent,
                        false
                )
        )
    }

    fun addToDo(todo: ToDo){
        todos.add(todo)
        notifyItemInserted(todos.size-1)
    }

    fun deleteToDos(){
        todos.removeAll{todo->
            todo.isChecked
        }
        notifyDataSetChanged()
    }


    private fun toggleStrikeThrough(tvToDoTitle: TextView,isChecked:Boolean){
        if(isChecked){
            tvToDoTitle.paintFlags = tvToDoTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
        }
        else{
            tvToDoTitle.paintFlags = tvToDoTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        val curToDo = todos[position]
        holder.itemView.apply {
            tvToDoTitle.text = curToDo.title
            cbDone.isChecked = curToDo.isChecked
            toggleStrikeThrough(tvToDoTitle,curToDo.isChecked)
            cbDone.setOnCheckedChangeListener{_,isChecked ->
                toggleStrikeThrough(tvToDoTitle,isChecked)
                curToDo.isChecked = !curToDo.isChecked
            }
        }
    }

    override fun getItemCount(): Int {
        return todos.size
    }
}