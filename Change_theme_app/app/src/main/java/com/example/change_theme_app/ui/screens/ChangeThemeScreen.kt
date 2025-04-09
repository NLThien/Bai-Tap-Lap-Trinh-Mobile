package com.example.change_theme_app.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.background
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.change_theme_app.ThemeViewModel
import com.example.change_theme_app.ui.theme.Change_theme_appTheme

@Composable
fun ThemeScreen(
    viewModel: ThemeViewModel = viewModel(),
    onApply: (String) -> Unit = {}
) {
    var showDetail by remember { mutableStateOf(false) }
    var selectedThemeName by remember { mutableStateOf("") }

    // Theme mặc định khi khởi động (có thể thay đổi)
    val defaultTheme = ThemeOption("White", Color.White)

    // Khởi tạo theme nếu chưa có
    LaunchedEffect(Unit) {
        if (viewModel.backgroundColor == Color.Unspecified) {
            viewModel.changeBackgroundColor(defaultTheme.color)
        }
    }

    // Danh sách theme có thể chọn (loại bỏ theme hiện tại)
    val availableThemes = remember(viewModel.backgroundColor) {
        listOf(
            ThemeOption("Blue", Color.Blue),
            ThemeOption("Dark", Color.Black),
            ThemeOption("Purple", Color.Magenta),
            defaultTheme
        ).filter { it.color != viewModel.backgroundColor }
    }

    if (showDetail) {
        HomeScreen(
            userTheme = selectedThemeName,
            themeColor = viewModel.backgroundColor,
            onBack = { showDetail = false }
        )
    } else {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(viewModel.backgroundColor),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = "Setting",
                    fontSize = 30.sp,
                    color = if (viewModel.backgroundColor == Color.Black) Color.White else Color.Black,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )

                Text(
                    text = "Choosing the right theme sets the tone and personality of your app",
                    fontSize = 16.sp,
                    color = if (viewModel.backgroundColor == Color.Black) Color.White else Color.Black,
                    textAlign = TextAlign.Center
                )

                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(horizontal = 16.dp)
                ) {
                    availableThemes.forEach { theme ->
                        ThemeButton(
                            color = theme.color,
                            themeName = theme.name,
                            onClick = {
                                viewModel.changeBackgroundColor(theme.color)
                                selectedThemeName = "${theme.name} Theme"
                            }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))

                Button(
                    onClick = {
                        if (selectedThemeName.isNotEmpty()) {
                            showDetail = true
                            onApply(selectedThemeName)
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth(0.6f)
                        .height(50.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Blue,
                        contentColor = Color.White
                    ),
                    enabled = selectedThemeName.isNotEmpty()
                ) {
                    Text("Apply", fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

// Data class cho các lựa chọn theme
data class ThemeOption(
    val name: String,
    val color: Color
)

@Composable
fun ThemeButton(
    color: Color,
    themeName: String,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .width(100.dp)
            .height(60.dp),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(containerColor = color)
    ) {
        Text(
            text = themeName,
            color = if (color == Color.Black) Color.White else Color.Black,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ThemeScreenPreview() {
    Change_theme_appTheme {
        ThemeScreen()
    }
}