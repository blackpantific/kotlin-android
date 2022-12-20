package com.example.kotlin1

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin1.models.Task

class TaskAdapter(var context: Context, var tasks: List<Task>) : RecyclerView.Adapter<TaskHolder>() {

    private var inflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskHolder {
        val view = inflater.inflate(R.layout.task_list_item, parent, false)
        return TaskHolder(view)
    }

    override fun onBindViewHolder(holder: TaskHolder, position: Int) {
        val task = tasks[position]
        holder.apply {
            taskTitleView.text = task.name
            taskCreationDateView.text = task.date
        }
    }

    override fun getItemCount(): Int {
        return tasks.size
    }
}

class TaskHolder(view: View) : RecyclerView.ViewHolder(view) {
    val taskTitleView: TextView = itemView.findViewById(R.id.task_title)
    val taskCreationDateView: TextView = itemView.findViewById(R.id.task_creation_date)
}