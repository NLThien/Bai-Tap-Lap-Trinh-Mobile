package com.example.test_firebase_w5.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import android.app.Activity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.ui.platform.LocalContext
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.gms.auth.api.signin.GoogleSignIn
import kotlin.jvm.java
import com.google.android.gms.common.api.ApiException
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.DisposableEffect
import androidx. compose. runtime. mutableStateOf
import androidx. compose. runtime. remember
import androidx. compose. material3.AlertDialog
import androidx. compose. runtime. getValue
import androidx.compose.runtime.setValue

@Composable
fun StartScreen(
    navController: NavController,
    authViewModel: AuthViewModel = viewModel()
) {
    val context = LocalContext.current
    val activity = context as? Activity ?: return
    var showError by remember { mutableStateOf<String?>(null) }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            result.data?.let { data ->
                try {
                    val account = GoogleSignIn.getSignedInAccountFromIntent(data)
                        .getResult(ApiException::class.java)
                    account.idToken?.let { token ->
                        authViewModel.handleGoogleSignInResult(token)
                    }
                } catch (e: Exception) {
                    showError = "Lỗi đăng nhập: ${e.localizedMessage}"
                }
            }
        }
    }

    LaunchedEffect(authViewModel.isSignedIn) {
        authViewModel.isSignedIn.collect { isSignedIn ->
            if (isSignedIn) {
                navController.navigate("Home") { popUpTo("Start") { inclusive = true } }
            }
        }
    }

    // Hiển thị lỗi nếu có
    if (showError != null) {
        AlertDialog(
            onDismissRequest = { showError = null },
            title = { Text("Lỗi") },
            text = { Text(showError!!) },
            confirmButton = {
                Button(onClick = { showError = null }) {
                    Text("OK")
                }
            }
        )
    }

//    DisposableEffect(Unit) {
//        onDispose {
//            // Dọn dẹp khi Composable bị hủy
//        }
//    }

    Column (
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Spacer(modifier = Modifier.height(50.dp))

        Column (horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = R.drawable.uth_logo),
                contentDescription = "UTH Logo",
                modifier = Modifier.size(160.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "SmartTasks",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Text(
                text = "A simple and efficient to do app",
                fontSize = 12.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(80.dp))

            Text(
                text = "Welcome",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Text(
                text = "Ready to explore? Log in to started",
                fontSize = 12.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    val signInClient = authViewModel.getGoogleSignInClient(activity)
                    launcher.launch(signInClient.signInIntent)
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF007BFF)),
                modifier = Modifier
                    .height(50.dp)
                    .fillMaxWidth(0.8f),
            ) {
                Row (verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_google_logo),
                        contentDescription = "Google Logo",
                        modifier = Modifier.size(24.dp)
                    )

                    Text(
                        text = "SIGN IN WITH GOOGLE",
                        color = Color.White,
                        fontSize = 16.sp,
                    )
                }
            }
        }
        Text(
            text = "UTHSmartTasks",
            fontSize = 12.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
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