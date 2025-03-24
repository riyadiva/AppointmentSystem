package com.riya.doctorpatientappointmentsystem.presentation.composable

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.riya.doctorpatientappointmentsystem.R
import com.riya.doctorpatientappointmentsystem.presentation.theme.SystemColor

val NAVIGATION_HEIGHT = 90.dp

@Composable
fun BottomNavigationBar(
    selectedItem: Int,
    onItemSelected: (Int) -> Unit
) {
    NavigationBar(
        containerColor = SystemColor.backgroundGray,
        modifier = Modifier
            .fillMaxWidth()
            .height(NAVIGATION_HEIGHT)
    ) {
        NavigationBarItem(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.home_tap),
                    contentDescription = "Home",
                    modifier = Modifier.size(24.dp)
                )
            },
            label = { Text("Home") },
            selected = selectedItem == 0,
            onClick = { onItemSelected(0) }
        )

        NavigationBarItem(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.appointment_tap),
                    contentDescription = "Booking",
                    modifier = Modifier.size(24.dp)
                )
            },
            label = { Text("Booking") },
            selected = selectedItem == 1,
            onClick = { onItemSelected(1) }
        )

        NavigationBarItem(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.chat_tap),
                    contentDescription = "Chat",
                    modifier = Modifier.size(24.dp)
                )
            },
            label = { Text("Chat") },
            selected = selectedItem == 2,
            onClick = { onItemSelected(2) }
        )

        NavigationBarItem(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.profile_tap),
                    contentDescription = "Profile",
                    modifier = Modifier.size(24.dp)
                )
            },
            label = { Text("Profile") },
            selected = selectedItem == 3,
            onClick = { onItemSelected(3) }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BottomNavigationBarPreview() {
    BottomNavigationBar(selectedItem = 0, onItemSelected = {})
}
