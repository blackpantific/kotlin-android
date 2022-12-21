package com.example.kotlin1.activities.fragments

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.kotlin1.R
import com.example.kotlin1.activities.TaskListViewModel
import com.example.kotlin1.models.Task


private const val ARG_TASK = "task_key"

class TaskDetailsFragment : Fragment() {

    private val taskListViewModel: TaskListViewModel by lazy {
        ViewModelProvider(requireActivity()).get(TaskListViewModel::class.java)
    }

    private lateinit var title: EditText
    private lateinit var taskDescription: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_task_details, container, false)

        title = view.findViewById(R.id.title)
        taskDescription = view.findViewById(R.id.task_description)

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.save_task_menu, menu)
    }

    private fun hideKeyboardFrom(context: Context, view: View) {
        val imm: InputMethodManager =
            context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        this.view?.let { hideKeyboardFrom(this.requireContext(), it) }
        taskListViewModel.list.add(
            Task(
                title.text.toString(),
                taskDescription.text.toString()
            )
        )
        fragmentManager?.popBackStack()
        return super.onOptionsItemSelected(item)
    }

    companion object {
        fun newInstance(selectedTask: Task): TaskDetailsFragment {
            val args = Bundle().apply {
                putSerializable(ARG_TASK, selectedTask)
            }
            return TaskDetailsFragment().apply {
                arguments = args
            }
        }
    }
}