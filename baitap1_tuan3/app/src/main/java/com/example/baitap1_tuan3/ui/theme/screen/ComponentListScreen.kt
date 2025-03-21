package com.example.baitap1_tuan3.ui.theme.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.baitap1_tuan3.ui.theme.navigation.Screen

data class UIcomponent(val ten:String, val mota:String)

@Composable
fun ComponentListScreen(navController:NavController) {
    // Tạo danh sách 100 item
    val components = List(100) { index ->
        UIcomponent("Component $index", "Mô tả cho component $index")
    }

    Box {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Lazy column", modifier = Modifier.padding(20.dp), fontSize = 20.sp)

            LazyColumn {
                items(components) { component ->
                    Card(
                        modifier = Modifier.fillMaxSize().padding(8.dp).clickable {
                            navController.navigate(
                                Screen.ComponentDetail.route
                            )
                        }) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(text = component.ten)
                            Text(text = component.mota)
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(30.dp))
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
fun PreviewComponentListScreen() {
    val navController = NavController(LocalContext.current)
    ComponentListScreen(navController = navController)
}