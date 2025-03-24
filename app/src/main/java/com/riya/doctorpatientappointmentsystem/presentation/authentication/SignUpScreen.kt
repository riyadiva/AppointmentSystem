package com.riya.doctorpatientappointmentsystem.presentation.authentication

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.riya.doctorpatientappointmentsystem.R
import com.riya.doctorpatientappointmentsystem.presentation.composable.Input
import com.riya.doctorpatientappointmentsystem.presentation.composable.MainButton
import com.riya.doctorpatientappointmentsystem.presentation.composable.TextHead1
import com.riya.doctorpatientappointmentsystem.presentation.composable.TransformationType
import com.riya.doctorpatientappointmentsystem.presentation.theme.SystemColor
import com.google.firebase.auth.FirebaseAuth
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext

@Composable
fun SignUpScreen(navController: NavController) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Spacer(modifier = Modifier.height(32.dp))

            TextHead1(text = stringResource(R.string.create_new_account))

            Spacer(modifier = Modifier.height(32.dp))

            Input(
                title = stringResource(R.string.fullname),
                value = name,
                hint = stringResource(R.string.enter_your_name),
                onValueChange = { name = it }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Input(
                title = stringResource(R.string.email),
                value = email,
                hint = stringResource(R.string.enter_your_email),
                onValueChange = { email = it }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Input(
                title = stringResource(R.string.password),
                value = password,
                hint = stringResource(R.string.enter_your_password),
                transformation = TransformationType.Password,
                onValueChange = { password = it }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Input(
                title = stringResource(R.string.confirm_password),
                value = confirmPassword,
                hint = stringResource(R.string.confirm_your_password),
                transformation = TransformationType.Password,
                onValueChange = { confirmPassword = it }
            )

            Spacer(modifier = Modifier.height(24.dp))

            val context = LocalContext.current
            val auth = FirebaseAuth.getInstance()

            MainButton(
                title = stringResource(R.string.sign_up),
                modifier = Modifier.fillMaxWidth()
            ) {
                if (email.isNotBlank() && password.isNotBlank() && password == confirmPassword) {
                    auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                Toast.makeText(context, "Registration successful!", Toast.LENGTH_SHORT).show()
                                navController.navigate("login") {
                                    popUpTo("register") { inclusive = true }
                                }
                            }
                            else {
                                Toast.makeText(context, "Error: ${task.exception?.message}", Toast.LENGTH_LONG).show()
                            }
                        }
                } else {
                    Toast.makeText(context, "Please fill all fields correctly", Toast.LENGTH_SHORT).show()
                }
            }


            Spacer(modifier = Modifier.height(24.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = stringResource(R.string.already_have_account),
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = SystemColor.black
                    )
                )
                TextButton(onClick = {
                    navController.navigate("login")
                }) {
                    Text(
                        text = stringResource(R.string.sign_in),
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium,
                            color = SystemColor.primary
                        )
                    )
                }
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun SignUpScreenPreview() {
    val navController = rememberNavController()
    SignUpScreen(navController = navController)
}
