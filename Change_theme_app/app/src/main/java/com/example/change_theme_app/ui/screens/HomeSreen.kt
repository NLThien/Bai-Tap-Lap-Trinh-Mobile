package com.example.change_theme_app.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.change_theme_app.ui.theme.Change_theme_appTheme
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Alignment
import androidx. compose. foundation. layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.ui.text.style.TextAlign

@Composable
fun HomeScreen(userTheme: String, themeColor: Color, onBack: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(themeColor),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Text(
                text = "$userTheme",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
            )

            Text(
                text = "Choosing the right theme sets the tone and personality of your app, enchancing user experience and reinforcing your brand identity.",
                fontSize = 20.sp,
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .align(Alignment.CenterHorizontally),
                textAlign = TextAlign.Center
            )

            Button(
                onClick = onBack,
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .height(50.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Blue,
                    contentColor = Color.White
                )
            ) {
                Text("BACK", fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    Change_theme_appTheme {
        HomeScreen(userTheme = "light",themeColor = Color.White, onBack = {})
    }
}