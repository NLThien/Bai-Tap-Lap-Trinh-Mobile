package com.example.test_firebase_w5

import android.os.Bundle
import android.content.Context
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import com.example.test_firebase_w5.ui.theme.Test_firebase_w5Theme
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import androidx.compose.ui.platform.LocalContext
import com.google.firebase.auth.FirebaseAuth
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignIn

import com.example.test_firebase_w5.ui.screen.StartScreen
import com.example.test_firebase_w5.ui.screen.HomeScreen
import com.example.test_firebase_w5.ui.screen.AuthViewModel


class MainActivity : ComponentActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()

        // Configure Google Sign-In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)

        // Sign out before showing login screen to force account selection
        googleSignInClient.signOut().addOnCompleteListener {
            setContent {
                Test_firebase_w5Theme {
                    val context = LocalContext.current
                    AppNavigation(auth, googleSignInClient, context)
                }
            }
        }
    }
}

@Composable
fun AppNavigation(auth: FirebaseAuth, googleSignInClient: GoogleSignInClient, context: Context) {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "start") {
        composable("start") {
            StartScreen(navController, auth, googleSignInClient, context)
        }
        composable("home") {
            HomeScreen(navController, auth)
        }
    }
}