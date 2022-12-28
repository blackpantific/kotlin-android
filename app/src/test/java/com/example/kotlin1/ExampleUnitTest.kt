package com.example.kotlin1

import com.example.kotlin1.activities.viewModels.TaskListViewModel
import com.example.kotlin1.models.Task
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        var tlvm = TaskListViewModel()
//        tlvm.getAllTasks()

//        var task = Task()
//        task.title = "title amazing 123456789101112"
//        task.description = "Lorem description"
//
//        var res = tlvm.saveNewTask(task)

        var task = Task()
        task.id = 18
        task.title = "title amazing num"
        task.description = "Lorem description"

        //var res = tlvm.saveNewTask(task)

//        var res = tlvm.updateTask(task)

        var res = tlvm.deleteTask(11)
    }
}