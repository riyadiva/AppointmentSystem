package com.riya.doctorpatientappointmentsystem.presentation.startscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.riya.doctorpatientappointmentsystem.R
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(navController: NavController) {
    SplashContent(navController)
}

@Composable
fun SplashContent(navController: NavController) {
    // Delay and navigate
    LaunchedEffect(true) {
        delay(2500) // wait for 2.5 seconds
        navController.navigate("login") {
            popUpTo("splash") { inclusive = true }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.size(135.dp),
            painter = painterResource(id = R.drawable.medinova),
            contentDescription = "splash logo",
        )
        Text(
            text = "Hope",
            style = MaterialTheme.typography.headlineLarge

        )
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun SplashPreview() {
    SplashContent(navController = rememberNavController())
}
