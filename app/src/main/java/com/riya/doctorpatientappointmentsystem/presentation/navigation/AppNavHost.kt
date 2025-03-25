package com.riya.doctorpatientappointmentsystem.presentation.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.riya.doctorpatientappointmentsystem.presentation.Screens.*
import com.riya.doctorpatientappointmentsystem.presentation.authentication.LoginScreen
import com.riya.doctorpatientappointmentsystem.presentation.authentication.SignUpScreen
import com.riya.doctorpatientappointmentsystem.presentation.composable.BottomNavigationBar
import com.riya.doctorpatientappointmentsystem.presentation.startscreen.SplashScreen

@Composable
fun AppNavHost(navController: NavHostController = rememberNavController()) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val selectedIndex = when (currentRoute) {
        Screen.Home.route -> 0
        Screen.Booking.route -> 1
        Screen.Chat.route -> 2
        Screen.Profile.route -> 3
        else -> -1
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.weight(1f)) {
            NavHost(
                navController = navController,
                startDestination = Screen.Splash.route
            ) {
                composable(Screen.Splash.route) {
                    SplashScreen(navController)
                }
                composable(Screen.Login.route) {
                    LoginScreen(navController)
                }
                composable(Screen.Register.route) {
                    SignUpScreen(navController)
                }
                composable(Screen.Home.route) {
                    DashboardScreen(navController = navController)
                }
                composable(
                    route = "appointment/{doctorId}",
                    arguments = listOf(navArgument("doctorId") { type = NavType.StringType })
                ) { backStackEntry ->
                    val doctorId = backStackEntry.arguments?.getString("doctorId") ?: ""
                    AppointmentScreen(doctorId = doctorId, navController = navController)
                }
                composable(Screen.Booking.route) {
                    BookingScreen(navController)
                }
                composable(Screen.Chat.route) {
                    ChatScreen(navController)
                }
                composable(Screen.Profile.route) {
                    ProfileScreen(navController)
                }
                composable(
                    route = "confirmation/{doctorId}/{selectedDate}/{selectedTime}",
                    arguments = listOf(
                        navArgument("doctorId") { type = NavType.StringType },
                        navArgument("selectedDate") { type = NavType.StringType },
                        navArgument("selectedTime") { type = NavType.StringType }
                    )
                ) { backStackEntry ->
                    val doctorId = backStackEntry.arguments?.getString("doctorId") ?: ""
                    val selectedDate = backStackEntry.arguments?.getString("selectedDate") ?: ""
                    val selectedTime = backStackEntry.arguments?.getString("selectedTime") ?: ""
                    ConfirmationScreen(
                        doctorId = doctorId,
                        selectedDate = selectedDate,
                        selectedTime = selectedTime,
                        navController = navController
                    )
                }

                // ChatDetailScreen route with args
                composable(
                    route = "chatDetail/{doctorId}/{doctorName}/{imageId}",
                    arguments = listOf(
                        navArgument("doctorId") { type = NavType.StringType },
                        navArgument("doctorName") { type = NavType.StringType },
                        navArgument("imageId") { type = NavType.IntType }
                    )
                ) { backStackEntry ->
                    val doctorId = backStackEntry.arguments?.getString("doctorId") ?: ""
                    val doctorName = backStackEntry.arguments?.getString("doctorName") ?: ""
                    val imageId = backStackEntry.arguments?.getInt("imageId") ?: 0
                    ChatDetailScreen(
                        navController = navController,
                        doctorId = doctorId,
                        doctorName = doctorName,
                        doctorImage = imageId
                    )
                }

            }
        }

        if (selectedIndex != -1) {
            BottomNavigationBar(selectedItem = selectedIndex) { index ->
                when (index) {
                    0 -> navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Home.route) { inclusive = true }
                    }
                    1 -> navController.navigate(Screen.Booking.route) {
                        popUpTo(Screen.Home.route) { inclusive = false }
                    }
                    2 -> navController.navigate(Screen.Chat.route) {
                        popUpTo(Screen.Home.route) { inclusive = false }
                    }
                    3 -> navController.navigate(Screen.Profile.route) {
                        popUpTo(Screen.Home.route) { inclusive = false }
                    }
                }
            }
        }
    }
}
