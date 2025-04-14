package com.riya.doctorpatientappointmentsystem.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.riya.doctorpatientappointmentsystem.data.doctorsList
import com.riya.doctorpatientappointmentsystem.data.Doctor

class DoctorViewModel : ViewModel() {
    val doctors = doctorsList
    fun getDoctorById(id: String): Doctor? = doctors.find { it.id == id }
}
