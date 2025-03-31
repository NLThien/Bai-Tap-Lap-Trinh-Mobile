package com.example.test_firebase_w5

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.Nullable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Icon
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

import com.example.test_firebase_w5.ui.screen.StartScreen
import com.example.test_firebase_w5.ui.screen.HomeScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Test_firebase_w5Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()

                    // Navigation quản lý điều hướng
                    NavHost(navController = navController, startDestination = "Start") {
                        composable("Start") {
                            StartScreen(OnSignInClick = { navController.navigate("Home") })
                        }
                        composable("Home") {
                            HomeScreen(OnBackClick = { navController.popBackStack() },navController)
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
        "StartScreen" -> StartScreen(OnSignInClick = { currentScreen = "HomeScreen" })
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
        StartScreen(OnSignInClick = { })
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    Test_firebase_w5Theme {
        HomeScreen(OnBackClick = { }, navController = rememberNavController())
    }
}