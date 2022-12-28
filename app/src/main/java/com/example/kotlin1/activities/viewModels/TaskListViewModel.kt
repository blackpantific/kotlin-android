package com.example.kotlin1.activities.viewModels

import androidx.lifecycle.ViewModel
import com.example.kotlin1.httpServices.HttpMethodEnum
import com.example.kotlin1.httpServices.HttpService
import com.example.kotlin1.models.Task
import com.google.gson.reflect.TypeToken


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

    fun getAllTasks(): List<Task> {
        val listType: TypeToken<List<Task>> = object : TypeToken<List<Task>>() {}
        val httpService = HttpService()
        val result = httpService.makeRequest<List<Task>>(
            HttpMethodEnum.Get,
            "http://localhost:8080/tasks",
            null,
            listType.type
        )
        return if (result.isSuccessful) {
            result.data!!
        } else {
            mutableListOf<Task>()
        }
    }

    fun saveNewTask(task: Task): Task? {
        val httpService = HttpService()
        val result = httpService.makeRequest<Task>(
            HttpMethodEnum.Post,
            "http://localhost:8080/tasks",
            task,
            Task::class.java
        )
        return if (result.isSuccessful) {
            result.data!!
        } else {
            null
        }
    }

    fun updateTask(task: Task) : String {
        val httpService = HttpService()
        val result =
            httpService.makeRequest<Task>(
                HttpMethodEnum.Put,
                "http://localhost:8080/tasks/" + task.id,
                task
            )
        return if (result.isSuccessful) {
            "Task updated successfully!"
        } else {
            "Something went wrong.."
        }
    }

    fun deleteTask(taskId: Int) : String{
        val httpService = HttpService()
        val result =
            httpService.makeRequest<Task>(
                HttpMethodEnum.Delete,
                "http://localhost:8080/tasks/$taskId",
            )
        return if (result.isSuccessful) {
            "Task deleted successfully!"
        } else {
            "Something went wrong.."
        }
    }
}