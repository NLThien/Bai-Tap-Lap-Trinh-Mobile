package com.example.test_firebase_w5.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.test_firebase_w5.ui.theme.Test_firebase_w5Theme
import com.example.test_firebase_w5.R
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.test_firebase_w5.ui.screen.AuthViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

@Composable
fun HomeScreen(
    OnBackClick: () -> Unit,
    navController: NavController,
    authViewModel: AuthViewModel = viewModel()
) {
    val currentUser by authViewModel.currentUser // Sử dụng delegate
    var isLoading by remember { mutableStateOf(false) }
    var birthday by remember { mutableStateOf("Đang tải...") }

    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize()
            .padding(16.dp)
            .statusBarsPadding(),   // tránh đè lên status bar
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        TopBar("Profile", navController)

        Spacer(modifier = Modifier.height(80.dp))

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_firebase_w5),
                contentDescription = "Easy Time Management",
                modifier = Modifier.size(100.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Text Name
            Text(
                text = "Name",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Left
            )
            //hiển thị tên
            Text(
                text = currentUser?.displayName ?: "Chưa cập nhật tên",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(4.dp)
            )

            // Text Email
            Text(
                text = "Email",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Left
            )
            // Hiển thị email
            Text(
                text = currentUser?.email ?: "Chưa cập nhật email",
                fontSize = 16.sp,
                modifier = Modifier.padding(4.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Text Day of birth
            Text(
                text = "Day of Birth",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Left,
                modifier = Modifier.padding(horizontal = 18.dp)
            )
            // hiển thị ngày sinh
            Text(
                text = "Ngày sinh: ${birthday}",
                fontSize = 16.sp,
                modifier = Modifier.padding(4.dp)
            )
        }

        Spacer(modifier = Modifier.weight(1f))
        // Back Button
        Button(
            onClick = OnBackClick,
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF007BFF)),
            modifier = Modifier
                .height(50.dp)
                .fillMaxWidth(0.8f),
        ) {
            Text(
                text = "Back", color = Color.White,
                fontSize = 20.sp,)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    Test_firebase_w5Theme {
        HomeScreen(
            OnBackClick = {},
            navController = rememberNavController(),
            authViewModel = viewModel()
        )
    }
}