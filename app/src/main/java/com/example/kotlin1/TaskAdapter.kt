package com.example.kotlin1

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin1.activities.fragments.ThirdTaskFragment
import com.example.kotlin1.models.Task


class TaskAdapter(var context: Context, var tasks: List<Task>) : RecyclerView.Adapter<TaskHolder>() {

    private var inflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskHolder {
        val view = inflater.inflate(R.layout.task_list_item, parent, false)
        return TaskHolder(view)
    }

    override fun onBindViewHolder(holder: TaskHolder, position: Int) {
        val task = tasks[position]
        holder.bind(task)
    }

    override fun getItemCount(): Int {
        return tasks.size
    }
}

class TaskHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {

    private lateinit var task: Task

    private val taskTitleView: TextView = itemView.findViewById(R.id.task_title)
    private val taskCreationDateView: TextView = itemView.findViewById(R.id.task_creation_date)

    fun bind(task: Task) {
        this.task = task
        taskTitleView.text = this.task.name
        taskCreationDateView.text = this.task.date
    }

    override fun onClick(v: View?) {
        val callbacks = v?.context as ThirdTaskFragment.Callbacks
        callbacks.onTaskSelected(task)
    }

    init {
        itemView.setOnClickListener(this)
    }
}