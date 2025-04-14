package com.riya.doctorpatientappointmentsystem.presentation.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColors = lightColorScheme(
    primary = SystemColor.primary,
    secondary = SystemColor.secondary,
    background = SystemColor.backgroundGray
)

@Composable
fun DoctorPatientAppointmentSystemTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LightColors,
        typography = AppTypography, // from Type.kt
        content = content
    )
}
