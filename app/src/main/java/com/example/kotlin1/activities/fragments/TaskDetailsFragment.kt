package com.example.kotlin1.activities.fragments

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.example.kotlin1.R
import com.example.kotlin1.activities.MainActivity
import com.example.kotlin1.models.Task


private const val ARG_TASK = "task_key"

class TaskDetailsFragment : Fragment() {

    private lateinit var thisFragmentActivity: AppCompatActivity

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

        thisFragmentActivity = activity as AppCompatActivity
        thisFragmentActivity.supportActionBar!!.hide()

        val toolbar = view.findViewById<Toolbar>(R.id.my_toolbar)
        thisFragmentActivity.setSupportActionBar(toolbar)

        thisFragmentActivity.supportActionBar!!.setDisplayHomeAsUpEnabled(true);
        thisFragmentActivity.supportActionBar!!.setDisplayShowHomeEnabled(true);

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.add_task_menu, menu)
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