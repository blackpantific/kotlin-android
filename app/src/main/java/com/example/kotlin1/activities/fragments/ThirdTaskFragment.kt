package com.example.kotlin1.activities.fragments

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin1.R
import com.example.kotlin1.TaskAdapter
import com.example.kotlin1.activities.TaskListViewModel
import com.example.kotlin1.models.Task

class ThirdTaskFragment : Fragment() {

    //WHAT HAVE I HAVE TO DO WITH THIS?
    interface Callbacks {
        fun onTaskSelected(task: Task?)
    }

    private var callbacks: Callbacks? = null
    private lateinit var taskRecyclerView: RecyclerView
    private lateinit var thisFragmentActivity: AppCompatActivity

    private val taskListViewModel: TaskListViewModel by lazy {
        ViewModelProvider(requireActivity()).get(TaskListViewModel::class.java)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callbacks = context as Callbacks?
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_third_task, container, false)

        thisFragmentActivity = activity as AppCompatActivity

        taskListViewModel.init()

        taskRecyclerView = view.findViewById(R.id.tasks_list)
        taskRecyclerView.layoutManager = LinearLayoutManager(context)
        taskRecyclerView.adapter = TaskAdapter(thisFragmentActivity, taskListViewModel.list)

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.add_task_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.new_task -> {
                callbacks?.onTaskSelected(null)
                true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onDetach() {
        super.onDetach()
        callbacks = null
    }
}