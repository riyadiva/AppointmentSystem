package com.riya.doctorpatientappointmentsystem.presentation.authentication

import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import com.riya.doctorpatientappointmentsystem.R
import com.riya.doctorpatientappointmentsystem.presentation.composable.Input
import com.riya.doctorpatientappointmentsystem.presentation.composable.MainButton
import com.riya.doctorpatientappointmentsystem.presentation.composable.TextHead1
import com.riya.doctorpatientappointmentsystem.presentation.composable.TextHead4
import com.riya.doctorpatientappointmentsystem.presentation.composable.TransformationType
import androidx.credentials.CredentialManager
import androidx.credentials.CredentialManager.Companion.create
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import com.riya.doctorpatientappointmentsystem.data.auth.GoogleAuthUiClient

@Composable
fun LoginScreen(navController: NavController) {
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    val context = LocalContext.current
    val auth = FirebaseAuth.getInstance()

    val credentialManager = remember { CredentialManager.create(context) }
    val googleAuthUiClient = remember { GoogleAuthUiClient(context, credentialManager) }

    LoginContent(
        email = email,
        password = password,
        onEmailChange = { email = it },
        onPasswordChange = { password = it },
        onClickLogin = {
            if (email.isNotBlank() && password.isNotBlank()) {
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(context, "Login successful", Toast.LENGTH_SHORT).show()
                            navController.navigate("home") {
                                popUpTo("login") { inclusive = true }
                            }
                        } else {
                            Toast.makeText(context, "Error: ${task.exception?.message}", Toast.LENGTH_LONG).show()
                        }
                    }
            } else {
                Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        },
        onClickRegister = {
            navController.navigate("register")
        },
        onGoogleClick = {
            (context as? ComponentActivity)?.lifecycleScope?.launch {
                val result = googleAuthUiClient.signInWithGoogle()
                if (result.data != null) {
                    Toast.makeText(context, "Google login success!", Toast.LENGTH_SHORT).show()
                    navController.navigate("home") {
                        popUpTo("login") { inclusive = true }
                    }
                } else {
                    Toast.makeText(context, result.errorMessage ?: "Google sign-in failed", Toast.LENGTH_LONG).show()
                }
            }
        }
    )
}



@Composable
fun LoginContent(
    email: String,
    password: String,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onClickLogin: () -> Unit,
    onClickRegister: () -> Unit,
    onGoogleClick: () -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Spacer(modifier = Modifier.height(40.dp))
            TextHead1(text = stringResource(R.string.welcome))

            Spacer(modifier = Modifier.height(36.dp))

            Text(
                text = stringResource(R.string.sign_in),
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = stringResource(R.string.login_description),
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Start
            )

            Spacer(modifier = Modifier.height(36.dp))

            Input(
                title = stringResource(R.string.email),
                value = email,
                hint = stringResource(R.string.enter_your_email),
                onValueChange = onEmailChange
            )

            Spacer(modifier = Modifier.height(24.dp))

            Input(
                title = stringResource(R.string.password),
                value = password,
                hint = stringResource(R.string.enter_your_password),
                transformation = TransformationType.Password,
                onValueChange = onPasswordChange
            )

            Spacer(modifier = Modifier.height(12.dp))

            TextHead4(
                text = stringResource(R.string.forget_password),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(24.dp))

            MainButton(
                title = stringResource(R.string.login),
                modifier = Modifier.fillMaxWidth(),
                onClick = onClickLogin
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = stringResource(R.string.or),
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.titleMedium.copy(
                    color = Color(0xFF858585),
                    textAlign = TextAlign.Center
                )
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Inline Social Icons
            Row {
                Box(
                    modifier = Modifier
                        .size(56.dp) // Total size including icon + padding
                        .border(width = 1.dp, color = Color(0xFFD2EBE7), shape = CircleShape)
                        .padding(12.dp) // Padding inside the circle
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.facebook_icon),
                        contentDescription = "facebook_icon",
                        modifier = Modifier.fillMaxSize() // Fill remaining space inside padding
                    )
                }

                Spacer(modifier = Modifier.width(30.dp))

                Box(
                    modifier = Modifier
                        .size(56.dp)
                        .border(width = 1.dp, color = Color(0xFFD2EBE7), shape = CircleShape)
                        .padding(12.dp)
                        .clickable { onGoogleClick() } //this triggers sign-in
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.google_icon),
                        contentDescription = "google_icon",
                        modifier = Modifier.fillMaxSize()
                    )
                }

            }


            Spacer(modifier = Modifier.height(24.dp))

            // Don’t have an account? Sign Up
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(R.string.don_t_have_an_account),
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.width(4.dp))
                TextButton(onClick = { onClickRegister() }) {
                    Text(
                        text = stringResource(R.string.sign_up),
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.SemiBold
                        )
                    )
                }
            }


        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun LoginScreenPreview() {
    val navController = rememberNavController()
    LoginScreen(navController = navController)
}
