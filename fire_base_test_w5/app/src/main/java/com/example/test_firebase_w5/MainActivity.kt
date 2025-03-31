package com.example.test_firebase_w5

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.test_firebase_w5.ui.theme.Test_firebase_w5Theme
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.NavController
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.navigation.compose.composable
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx. compose. runtime. LaunchedEffect

import com.example.test_firebase_w5.ui.screen.StartScreen
import com.example.test_firebase_w5.ui.screen.HomeScreen
import com.example.test_firebase_w5.ui.screen.AuthViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Test_firebase_w5Theme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    val navController = rememberNavController()
                    val authViewModel: AuthViewModel = viewModel() // Thêm dòng này

                    NavHost(
                        navController = navController,
                        startDestination = if (Firebase.auth.currentUser != null) "Home" else "Start"
                    ) {
                        composable("Start") {
                            StartScreen(navController, authViewModel) // Truyền viewModel
                        }
                        composable("Home") {
                            HomeScreen(
                                OnBackClick = {
                                    Firebase.auth.signOut()
                                    navController.navigate("Start") {
                                        popUpTo("Home") { inclusive = true }
                                    }
                                },
                                navController = navController,
                                authViewModel = authViewModel // Truyền viewModel
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MainContent(NavController: NavController) {
    var currentScreen by remember { mutableStateOf("StartScreen") }

    when (currentScreen) {
        "StartScreen" -> StartScreen(navController = rememberNavController())
        "HomeScreen" -> HomeScreen(
            OnBackClick = { currentScreen = "StartScreen"},
            navController = NavController
        )
    }
}

@Preview(showBackground = true)
@Composable
fun StartScreenPreview() {
    Test_firebase_w5Theme {
        StartScreen(navController = rememberNavController())
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    Test_firebase_w5Theme {
        HomeScreen(
            OnBackClick = {},
            navController = rememberNavController(),
        )
    }
}