package com.riya.doctorpatientappointmentsystem.presentation.navigation

data object Screen {
    data object Splash : ScreenItem("splash")
    data object Login : ScreenItem("login")
    data object Register : ScreenItem("register")
    data object Home : ScreenItem("home")
    data object Booking : ScreenItem("booking")
    data object Appointment : ScreenItem("appointment/{doctorId}") {
        fun createRoute(doctorId: String) = "appointment/$doctorId"
    }
    data object Chat : ScreenItem("chat")
    data object Profile : ScreenItem("profile")
}

sealed class ScreenItem(val route: String)

