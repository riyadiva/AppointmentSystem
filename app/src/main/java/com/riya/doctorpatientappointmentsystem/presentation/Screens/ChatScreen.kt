package com.riya.doctorpatientappointmentsystem.presentation.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.riya.doctorpatientappointmentsystem.R
import com.riya.doctorpatientappointmentsystem.presentation.theme.SystemColor

data class DoctorChatItem(
    val id: String,
    val name: String,
    val message: String,
    val time: String,
    val unreadCount: Int = 0,
    val profileImage: Int,
    val isActive: Boolean = false
)

val dummyDoctors = listOf(
    DoctorChatItem("1", "Dr.Upul", "Lorem ipsum...", "12.50", 2, R.drawable.doctor1, true),
    DoctorChatItem("2", "Dr.Silva", "Lorem ipsum...", "12.50", 0, R.drawable.doctor2, false),
    DoctorChatItem("3", "Dr.Malcom", "Lorem ipsum...", "12.50", 0, R.drawable.doctor1, true),
    DoctorChatItem("4", "Dr.Mohamed", "Lorem ipsum...", "12.50", 2, R.drawable.doctor2, true),
    DoctorChatItem("5", "Dr.Gamal", "Lorem ipsum...", "12.50", 5, R.drawable.doctor1, false),
    DoctorChatItem("6", "Dr.Sami", "Lorem ipsum...", "12.50", 0, R.drawable.doctor2, true),
)

@Composable
fun ChatScreen(navController: NavController) {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {

        // Top Bar
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Message",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = SystemColor.primary
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Search Bar
        OutlinedTextField(
            value = "",
            onValueChange = {},
            placeholder = { Text("Search A Doctor") },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = CircleShape,
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Active Now
        Text("Active Now", fontWeight = FontWeight.Bold, fontSize = 18.sp)
        ActiveNowList(doctors = dummyDoctors)

        Spacer(modifier = Modifier.height(16.dp))

        // Messages
        Text("Messages", fontWeight = FontWeight.Bold, fontSize = 18.sp)
        MessagesList(doctors = dummyDoctors, navController = navController)
    }
}

@Composable
fun ActiveNowList(doctors: List<DoctorChatItem>) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(doctors.filter { it.isActive }) { doctor ->
            Box {
                Image(
                    painter = painterResource(id = doctor.profileImage),
                    contentDescription = "Doctor Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(60.dp)
                        .clip(CircleShape)
                )
                Box(
                    modifier = Modifier
                        .size(12.dp)
                        .align(Alignment.BottomEnd)
                        .clip(CircleShape)
                        .background(if (doctor.isActive) Color.Green else Color.Red)
                        .border(1.dp, Color.White, CircleShape)
                )
            }
        }
    }
}

@Composable
fun MessagesList(doctors: List<DoctorChatItem>, navController: NavController) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(doctors) { doctor ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        navController.navigate("chatDetail/${doctor.id}/${doctor.name}/${doctor.profileImage}")
                    }
                    .padding(vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = doctor.profileImage),
                    contentDescription = "Doctor Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(56.dp)
                        .clip(CircleShape)
                )

                Spacer(modifier = Modifier.width(12.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(doctor.name, fontWeight = FontWeight.Bold)
                    Text(doctor.message, fontSize = 14.sp, color = SystemColor.textGray)
                }

                Column(horizontalAlignment = Alignment.End) {
                    Text(doctor.time, fontSize = 14.sp)
                    if (doctor.unreadCount > 0) {
                        Box(
                            modifier = Modifier
                                .padding(top = 4.dp)
                                .size(24.dp)
                                .clip(CircleShape)
                                .background(SystemColor.primary),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = doctor.unreadCount.toString(),
                                color = Color.White,
                                fontSize = 12.sp
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ChatScreenPreview() {
    ChatScreen(navController = rememberNavController())
}
