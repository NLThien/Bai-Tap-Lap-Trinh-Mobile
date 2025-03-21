package com.example.baitap1_tuan3.ui.theme.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import com.example.baitap1_tuan3.ui.theme.navigation.Screen
import androidx.compose.ui.graphics.Color

@Composable
fun ComponentDetailScreen(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize()) { // Đặt fillMaxSize cho Box
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween // Sắp xếp các phần tử
        ) {
            // Nội dung chính
            Text(
                "Detail",
                fontSize = 24.sp,
                fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(16.dp)) // Khoảng cách giữa tiêu đề và nội dung

            Text("Tiêu đề", fontSize = 18.sp)

            Box(modifier = Modifier.width(360.dp).height(400.dp).background(androidx.compose.ui.graphics.Color.Blue)){
                Text(
                    "nội dung chính",
                    fontSize = 18.sp,
                    fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
                )
            }

            Text(
                "ghi chú",
                fontSize = 18.sp,
                fontStyle = androidx.compose.ui.text.font.FontStyle.Italic
            )

            Spacer(modifier = Modifier.weight(1f)) // Spacer chiếm không gian còn lại

            // Nút quay lại trang đầu
            Button(
                onClick = { navController.navigate(Screen.Welcome.route) },
                modifier = Modifier
                    .width(340.dp)
                    .height(50.dp)
                    .align(Alignment.CenterHorizontally)
            ) {
                Text("BACK TO ROOT", fontSize = 20.sp)
            }
        }

        // Nút Back ở góc trên bên trái
        IconButton(
            onClick = { navController.popBackStack() },
            modifier = Modifier
                .align(Alignment.TopStart)
                .background(color = androidx.compose.ui.graphics.Color.Blue)
                .padding(10.dp)
                .size(20.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "Back"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewComponentDetailScreen() {
    val navController = NavController(LocalContext.current)
    ComponentDetailScreen(navController = navController)
}