package com.riya.doctorpatientappointmentsystem

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.riya.doctorpatientappointmentsystem.presentation.navigation.AppNavHost
import com.riya.doctorpatientappointmentsystem.presentation.theme.DoctorPatientAppointmentSystemTheme
import com.google.firebase.FirebaseApp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        FirebaseApp.initializeApp(this)

        setContent {
            DoctorPatientAppointmentSystemTheme {
                AppNavHost()
            }
        }
    }
}
