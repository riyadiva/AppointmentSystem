package com.riya.doctorpatientappointmentsystem.presentation.Screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.riya.doctorpatientappointmentsystem.R
import com.riya.doctorpatientappointmentsystem.data.Doctor
import com.riya.doctorpatientappointmentsystem.data.doctorsList
import com.riya.doctorpatientappointmentsystem.data.specialistCategories
import com.riya.doctorpatientappointmentsystem.presentation.theme.SystemColor

@Composable
fun DashboardScreen(navController: NavController) {
    var searchQuery by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopSection()
        Spacer(modifier = Modifier.height(16.dp))
        SearchSection(query = searchQuery, onQueryChange = { searchQuery = it })
        Spacer(modifier = Modifier.height(24.dp))
        AdsSection()
        Spacer(modifier = Modifier.height(24.dp))
        CategorySection()
        Spacer(modifier = Modifier.height(24.dp))
        DoctorsSection(navController = navController, query = searchQuery)
        Spacer(modifier = Modifier.height(120.dp))
    }
}

@Composable
fun TopSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = "Profile Image",
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.padding(top = 10.dp)
            ) {
                Text(
                    text = "Hi, Welcome Back",
                    style = MaterialTheme.typography.labelSmall.copy(
                        color = SystemColor.textGray,
                        fontSize = 14.sp
                    )
                )
                Text(
                    text = "User One",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                )
            }
        }

        Icon(
            imageVector = Icons.Default.Notifications,
            contentDescription = "Notifications",
            tint = SystemColor.primary,
            modifier = Modifier.size(24.dp)
        )
    }
}

@Composable
fun SearchSection(query: String, onQueryChange: (String) -> Unit) {
    OutlinedTextField(
        value = query,
        onValueChange = onQueryChange,
        placeholder = { Text("Search by name or specialty") },
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFF3F3F3), shape = CircleShape),
        shape = CircleShape,
        singleLine = true
    )
}

@Composable
fun AdsSection() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(SystemColor.tertiary)
    )
}

@Composable
fun CategorySection() {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Categories",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            Text(
                text = "See All",
                color = SystemColor.primary,
                fontSize = 14.sp
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(horizontal = 4.dp)
        ) {
            items(specialistCategories.size) { index ->
                val category = specialistCategories[index]
                CategoryChip(title = category) {
                    println("Clicked on category: $category")
                }
            }
        }
    }
}

@Composable
fun CategoryChip(title: String, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .clickable { onClick() }
            .background(SystemColor.secondary, shape = MaterialTheme.shapes.medium)
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(text = title, color = Color.White)
    }
}

@Composable
fun DoctorsSection(navController: NavController, query: String) {
    val filteredDoctors = doctorsList.filter {
        it.name.contains(query, ignoreCase = true) ||
                it.specialty.contains(query, ignoreCase = true)
    }

    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "All Doctors",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            Text(
                text = "See All",
                color = SystemColor.primary,
                fontSize = 14.sp
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            filteredDoctors.forEach { doctor ->
                DoctorCard(doctor = doctor, navController = navController)
            }
        }
    }
}

@Composable
fun DoctorCard(doctor: Doctor, navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                navController.navigate("appointment/${doctor.id}")
            }
            .background(SystemColor.tertiary, shape = MaterialTheme.shapes.medium)
            .padding(16.dp)
    ) {
        Text(text = doctor.name, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = doctor.specialty, color = SystemColor.textGray)
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .background(SystemColor.primary, shape = CircleShape)
                    .padding(horizontal = 24.dp, vertical = 8.dp)
            ) {
                Text(text = "Book", color = Color.White)
            }
            Text(text = "★ ${doctor.rating}", color = Color(0xFFFFA000))
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DashboardPreview() {
    val navController = rememberNavController()
    DashboardScreen(navController = navController)
}
