package com.example.kotlin1.models

class Task(var id: Int, var title: String, var description: String) : java.io.Serializable {

    constructor(id: Int) : this(id, "", "") {

    }

    constructor() : this(0) {

    }

    companion object {
        var idToIncrement = 0
    }
}