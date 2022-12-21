package com.example.kotlin1.activities

import androidx.lifecycle.ViewModel
import com.example.kotlin1.models.Task

class TaskListViewModel : ViewModel() {

    var isInitialized = false
    var list = mutableListOf<Task>()

    fun init() {
        if (!isInitialized) {
            for (i in 1..10) {
                Task.idToIncrement++
                list.add(Task(Task.idToIncrement, "Some name$i", "Some date$i"))
            }
            isInitialized = true
        }
    }
}