package com.example.kotlin1.activities

import androidx.lifecycle.ViewModel
import com.example.kotlin1.models.Task

class TaskListViewModel : ViewModel() {

    var list = mutableListOf<Task>()

    fun init(){
        for (i in 1..10) {
            list.add(Task("Some name$i", "Some date$i"))
        }
    }
}