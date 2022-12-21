package com.example.kotlin1.models

class Task(val id: Int, var name: String, var date: String) : java.io.Serializable{

    constructor(id: Int) : this(id, "", "") {

    }

    companion object{
        var idToIncrement = 0
    }
}