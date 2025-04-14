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

    // Navigation (Screen.kt)
    data object ChatDetail : ScreenItem("chatDetail/{doctorId}/{doctorName}/{imageId}") {
        fun createRoute(doctorId: String, doctorName: String, imageId: Int) =
            "chatDetail/$doctorId/$doctorName/$imageId"
    }


    data object Profile : ScreenItem("profile")
}

sealed class ScreenItem(val route: String)
