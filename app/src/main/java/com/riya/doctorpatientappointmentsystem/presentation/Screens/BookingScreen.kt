package com.riya.doctorpatientappointmentsystem.presentation.Screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.riya.doctorpatientappointmentsystem.presentation.theme.SystemColor
import com.riya.doctorpatientappointmentsystem.presentation.viewmodel.BookingViewModel

@Composable
fun BookingScreen(navController: NavController) {
    val viewModel: BookingViewModel = hiltViewModel()
    val bookings = viewModel.bookings.collectAsState().value

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {

        Text(
            text = "My Appointments",
            style = MaterialTheme.typography.headlineSmall,
            color = SystemColor.primary
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (bookings.isEmpty()) {
            Text(
                text = "No appointments yet.",
                style = MaterialTheme.typography.bodyMedium,
                color = SystemColor.textGray
            )
        } else {
            LazyColumn {
                items(bookings) { booking ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        colors = CardDefaults.cardColors(containerColor = SystemColor.tertiary)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                text = "${booking.doctorName}",
                                style = MaterialTheme.typography.titleMedium
                            )
                            Text("Specialty: ${booking.specialty}")
                            Text("Date: ${booking.date}")
                            Text("Time: ${booking.time}")

                            Spacer(modifier = Modifier.height(8.dp))

                            Button(
                                onClick = { viewModel.deleteBooking(booking) },
                                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
                            ) {
                                Text("Cancel Appointment")
                            }
                        }
                    }
                }
            }
        }
    }
}
