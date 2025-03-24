package com.riya.doctorpatientappointmentsystem.presentation.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.riya.doctorpatientappointmentsystem.data.doctorsList
import com.riya.doctorpatientappointmentsystem.data.roomdatabase.BookingEntity
import com.riya.doctorpatientappointmentsystem.presentation.composable.MainButton
import com.riya.doctorpatientappointmentsystem.presentation.theme.SystemColor
import com.riya.doctorpatientappointmentsystem.presentation.viewmodel.BookingViewModel

@Composable
fun ConfirmationScreen(
    doctorId: String,
    selectedDate: String,
    selectedTime: String,
    navController: NavController,
    viewModel: BookingViewModel = hiltViewModel() // Inject ViewModel
) {
    val doctor = doctorsList.find { it.id == doctorId } ?: return

    LaunchedEffect(true) {
        viewModel.insertBooking(
            BookingEntity(
                doctorId = doctor.id,
                doctorName = doctor.name,
                specialty = doctor.specialty,
                date = selectedDate,
                time = selectedTime
            )
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Check Icon in Circle
        Box(
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
                .background(Color(0xFF9BD1CE)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = "Success",
                tint = Color.White,
                modifier = Modifier.size(64.dp)
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "Congratulations",
            style = MaterialTheme.typography.headlineMedium.copy(
                color = SystemColor.primary,
                fontWeight = FontWeight.Bold
            )
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Your Appointment is Confirmed!",
            style = MaterialTheme.typography.bodyMedium.copy(
                color = SystemColor.textGray,
                fontSize = 16.sp
            )
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "${doctor.name} (${doctor.specialty})",
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Medium
            )
        )

        Text(
            text = "$selectedDate at $selectedTime",
            style = MaterialTheme.typography.bodyMedium.copy(
                fontSize = 16.sp,
                color = SystemColor.textGray
            )
        )

        Spacer(modifier = Modifier.height(48.dp))

        MainButton(
            title = "Back to Home",
            modifier = Modifier.fillMaxWidth()
        ) {
            navController.navigate("home") {
                popUpTo("dashboard") { inclusive = true }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ConfirmationScreenPreview() {
    val navController = rememberNavController()
    ConfirmationScreen(
        doctorId = "1",
        selectedDate = "Tue 26",
        selectedTime = "11.00 AM",
        navController = navController
    )
}
