package com.riya.doctorpatientappointmentsystem.presentation.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
fun TextBody2(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyMedium,
        modifier = modifier
    )
}

@Composable
fun TextHead3(text: String, modifier: Modifier = Modifier, color: Color = Color.Black) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleMedium.copy(color = color),
        modifier = modifier
    )
}

@Composable
fun TextHead4(text: String, modifier: Modifier = Modifier, color: Color = Color.Black) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodySmall.copy(
            fontWeight = FontWeight.Bold,
            color = color,
            textAlign = TextAlign.Start
        ),
        modifier = modifier
    )
}

@Composable
fun TextHead1(text: String, modifier: Modifier = Modifier, color: Color = Color.Black) {
    Text(
        text = text,
        style = MaterialTheme.typography.headlineLarge.copy(color = color),
        modifier = modifier
    )
}

@Preview
@Composable
fun TextPreview() {
    Column {
        TextHead1(text = "Heading 1")
        TextHead3(text = "Heading 3")
        TextHead4(text = "Heading 4")
        TextBody2(text = "Body2")
    }
}
