package com.riya.doctorpatientappointmentsystem.presentation.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.riya.doctorpatientappointmentsystem.R
import com.riya.doctorpatientappointmentsystem.presentation.composable.MainButton
import com.riya.doctorpatientappointmentsystem.presentation.theme.SystemColor
import com.riya.doctorpatientappointmentsystem.data.doctorsList



@Composable
fun AppointmentScreen(doctorId: String, navController: NavController) {
    val doctor = doctorsList.find { it.id == doctorId } ?: return

    val timeSlots = listOf("10.00 AM", "11.00 AM", "12.00 PM", "1.00", "2.00", "3.00", "4.00")
    val dateSlots = listOf("Mon 5", "Tue 6", "Wed 8", "Thu 9", "Fri 10", "Mon 13", "Tue 14", "Wed 15", "Thu 16")

    var selectedTime by remember { mutableStateOf<String?>(null) }
    var selectedDate by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        // Top Bar
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                modifier = Modifier
                    .size(24.dp)
                    .clickable { navController.popBackStack() },
                tint = SystemColor.primary
            )

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = "Appointment",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontSize = 20.sp,
                    color = SystemColor.primary
                )
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Profile
        Row(modifier = Modifier.fillMaxWidth()) {
            Image(
                painter = painterResource(id = doctor.imageRes),
                contentDescription = "Doctor Image",
                modifier = Modifier
                    .size(90.dp)
                    .clip(RoundedCornerShape(12.dp))
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(doctor.name, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Text(doctor.specialty, color = SystemColor.textGray)
                Text(doctor.price, color = SystemColor.green, fontWeight = FontWeight.SemiBold)
            }

            Column(horizontalAlignment = Alignment.End) {
                Icon(Icons.Default.Call, contentDescription = "Call", tint = SystemColor.primary)
                Spacer(modifier = Modifier.height(8.dp))
                Icon(Icons.Default.MailOutline, contentDescription = "Message", tint = SystemColor.primary)
                Spacer(modifier = Modifier.height(8.dp))
                Image(
                    painter = painterResource(id = R.drawable.videocall),
                    contentDescription = "Video Call",
                    modifier = Modifier
                        .size(24.dp),
                    colorFilter = androidx.compose.ui.graphics.ColorFilter.tint(SystemColor.primary)
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text("Details", fontWeight = FontWeight.Bold)
        Text(
            text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc vulputate libero et velit interdum...",
            style = MaterialTheme.typography.bodyMedium,
            color = SystemColor.textGray
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text("Working Hours", fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(12.dp))
        LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            items(timeSlots) { time ->
                TimeChip(
                    text = time,
                    selected = time == selectedTime,
                    onClick = { selectedTime = time }
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text("Date", fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(12.dp))
        LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            items(dateSlots) { date ->
                TimeChip(
                    text = date,
                    selected = date == selectedDate,
                    onClick = { selectedDate = date }
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        MainButton(
            title = "Book an Appointment",
            modifier = Modifier.fillMaxWidth()
        ) {
            // Handle booking with selectedTime and selectedDate
            if (selectedTime != null && selectedDate != null) {
                // Navigate to confirmation screen with data
                navController.navigate(
                    "confirmation/${doctor.id}/${selectedDate}/${selectedTime}"
                )
            }
        }
    }
}

@Composable
fun TimeChip(text: String, selected: Boolean, onClick: () -> Unit) {
    val backgroundColor = if (selected) SystemColor.primary else SystemColor.backgroundGray
    val textColor = if (selected) Color.White else Color.Black

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .background(backgroundColor)
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(text = text, fontSize = 14.sp, color = textColor)
    }
}


@Preview(showSystemUi = true, showBackground = true)
@Composable
fun AppointmentScreenPreview() {
    val navController = rememberNavController()
    AppointmentScreen(doctorId = "1", navController = navController)
}
