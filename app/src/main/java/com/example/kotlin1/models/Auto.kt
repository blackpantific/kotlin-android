package com.example.kotlin1.models

data class Auto(var title: String, var description: String, var icon: CarIcon) : java.io.Serializable

enum class CarIcon(){
    DEFAULT
}