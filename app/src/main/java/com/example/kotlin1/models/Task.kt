package com.example.kotlin1.models

class Task(val id: Int, val name: String, val date: String) : java.io.Serializable{
    companion object{
        var idToIncrement = 0
    }
}