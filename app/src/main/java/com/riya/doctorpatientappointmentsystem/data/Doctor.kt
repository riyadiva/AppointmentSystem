package com.riya.doctorpatientappointmentsystem.data

data class Doctor(
    val id: String,
    val name: String,
    val specialty: String,
    val price: String,
    val rating: Float,
    val description: String,
    val imageRes: Int
)