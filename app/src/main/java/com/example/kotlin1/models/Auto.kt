package com.example.kotlin1.models

import com.example.kotlin1.R

data class Auto(var title: String, var description: String, var icon: CarIcon) : java.io.Serializable

enum class CarIcon(var path: Int){
    CAR1(R.drawable.ic_baseline_electric_car_24),
    CAR2(R.drawable.ic_baseline_local_car_wash_24),
    CAR3(R.drawable.ic_baseline_car_rental_24),
    CAR4(R.drawable.ic_baseline_car_repair_24)
}