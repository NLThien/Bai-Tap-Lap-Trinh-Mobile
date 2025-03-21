package com.example.baitap1_tuan3.ui.theme.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.baitap1_tuan3.ui.theme.screen.ComponentDetailScreen
import com.example.baitap1_tuan3.ui.theme.screen.ComponentListScreen
import com.example.baitap1_tuan3.ui.theme.screen.WelcomeScreen

sealed class Screen(val route: String){
    object Welcome : Screen("welcome")
    object ComponentList : Screen("components_list")
    object ComponentDetail : Screen("components_detail")
}
@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController, startDestination = Screen.Welcome.route) {
        composable(Screen.Welcome.route) {
            WelcomeScreen(navController)
        }
        composable(Screen.ComponentList.route) {
            ComponentListScreen(navController)
        }
        composable(Screen.ComponentDetail.route) {
            ComponentDetailScreen(navController)
        }
    }
}

fun composable(route: String, function: () -> Unit) {

}

@Composable
fun WelcomeScreen(navController: NavHostController) {

}
