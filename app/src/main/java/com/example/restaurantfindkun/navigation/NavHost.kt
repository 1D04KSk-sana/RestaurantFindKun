package com.example.restaurantfindkun.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.pecoricompose.screen.profile.MainScreen

@Composable
fun FindKunNavHost(
    navController: NavHostController,
    onChangeDestination: (FindKunDestination) -> Unit,
    modifier: Modifier
) {
    NavHost(
        navController = navController,
        startDestination = FindKunDestination.Main.route,
        modifier = Modifier
    ) {
        composable(route = FindKunDestination.Main.route) {
            MainScreen()
        }
    }
}