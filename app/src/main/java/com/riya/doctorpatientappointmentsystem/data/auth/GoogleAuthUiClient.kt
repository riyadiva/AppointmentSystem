package com.riya.doctorpatientappointmentsystem.data.auth

import android.content.Context
import android.util.Log
import androidx.credentials.*
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import org.json.JSONObject

class GoogleAuthUiClient(
    private val context: Context,
    private val oneTapClient: CredentialManager
) {
    suspend fun signInWithGoogle(): SignInResult {
        val request = GetCredentialRequest(
            credentialOptions = listOf(
                GetGoogleIdOption.Builder()
                    .setFilterByAuthorizedAccounts(false)
                    .setServerClientId("AIzaSyAI7I4wFbv2FPWcCe8AuG6fwS22nihAamI")
                    .build()
            )
        )

        return try {
            val result = oneTapClient.getCredential(context, request)
            val googleCredential = result.credential as? PublicKeyCredential
            val idToken = googleCredential?.authenticationResponseJson
                ?.let { JSONObject(it).getString("id_token") }

            val credential = GoogleAuthProvider.getCredential(idToken, null)
            val authResult = Firebase.auth.signInWithCredential(credential).await()

            SignInResult(
                data = authResult.user,
                errorMessage = null
            )
        } catch (e: Exception) {
            SignInResult(null, e.message)
        }
    }
}

data class SignInResult(
    val data: FirebaseUser?,
    val errorMessage: String?
)