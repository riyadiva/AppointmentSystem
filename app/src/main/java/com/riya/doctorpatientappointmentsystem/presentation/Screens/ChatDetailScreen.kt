package com.riya.doctorpatientappointmentsystem.presentation.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.riya.doctorpatientappointmentsystem.R
import com.riya.doctorpatientappointmentsystem.presentation.theme.SystemColor

data class MessageItem(
    val id: Int,
    val text: String,
    val isSentByUser: Boolean
)

@Composable
fun ChatDetailScreen(
    navController: NavController,
    doctorId: String,
    doctorName: String = "Dr. Upul",
    doctorImage: Int = R.drawable.doctor1
) {
    var message by remember { mutableStateOf(TextFieldValue("")) }
    val messages = remember {
        mutableStateListOf(
            MessageItem(1, "Hello doctor!", true),
            MessageItem(2, "Hi! How can I help you today?", false),
            MessageItem(3, "I have a persistent headache.", true)
        )
    }

    Column(modifier = Modifier.fillMaxSize()) {
        // Top Bar
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .background(SystemColor.primary)
                .padding(16.dp)
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White)
            }
            Spacer(modifier = Modifier.width(8.dp))
            Image(
                painter = painterResource(id = doctorImage),
                contentDescription = "Doctor Image",
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = doctorName, color = Color.White, fontSize = 18.sp)
        }

        // Chat Messages
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(16.dp),
            reverseLayout = true
        ) {
            items(messages.reversed()) { msg ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    horizontalArrangement = if (msg.isSentByUser) Arrangement.End else Arrangement.Start
                ) {
                    Text(
                        text = msg.text,
                        modifier = Modifier
                            .background(
                                color = if (msg.isSentByUser) SystemColor.primary else Color.LightGray,
                                shape = MaterialTheme.shapes.medium
                            )
                            .padding(10.dp),
                        color = if (msg.isSentByUser) Color.White else Color.Black
                    )
                }
            }
        }

        // Input Field
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            BasicTextField(
                value = message,
                onValueChange = { message = it },
                modifier = Modifier
                    .weight(1f)
                    .background(Color(0xFFF0F0F0), shape = CircleShape)
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                decorationBox = { innerTextField ->
                    if (message.text.isEmpty()) {
                        Text("Type a message...", color = Color.Gray)
                    }
                    innerTextField()
                }
            )
            IconButton(onClick = {
                if (message.text.isNotBlank()) {
                    messages.add(MessageItem(messages.size + 1, message.text, true))
                    message = TextFieldValue("")
                }
            }) {
                Icon(Icons.Default.Send, contentDescription = "Send", tint = SystemColor.primary)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ChatDetailScreenPreview() {
    ChatDetailScreen(
        navController = rememberNavController(),
        doctorId = "1",
        doctorName = "Dr. Upul",
        doctorImage = R.drawable.doctor1
    )
}

