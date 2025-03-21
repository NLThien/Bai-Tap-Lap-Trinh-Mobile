package com.example.baitap1_tuan3.ui.theme.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.baitap1_tuan3.ui.theme.navigation.Screen
import com.example.baitap1_tuan3.R
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.graphics.Color
import android.R.id.bold
import androidx.compose.ui.unit.sp

@Composable
fun WelcomeScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
            modifier = Modifier.size(300.dp)
        )

        Text("Navigation", fontSize = 30.sp, fontWeight = androidx.compose.ui.text.font.FontWeight.Bold)

        Text(
            text = "Is a framework that simplifies the implementation of navigation between different UI components (activities, fragments or Composables ) in an app.",
            fontSize = 16.sp,
            modifier = Modifier
                .padding(50.dp)
                .align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(30.dp))

        Button(onClick = { navController.navigate(Screen.ComponentList.route) },
            modifier = Modifier
                .width(340.dp)
                .height(50.dp)
        ) {
            Text("Push", fontSize = 20.sp)
        }
    }
}
@Preview(showBackground = true)
@Composable
fun PreviewWelcomeScreen() {
    val navController = NavController(LocalContext.current)
    WelcomeScreen(navController = navController)
}