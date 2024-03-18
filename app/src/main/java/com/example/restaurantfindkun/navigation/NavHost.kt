package com.example.restaurantfindkun.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.restaurantfindkun.screen.top.TopScreen

@Composable
fun FindKunNavHost(
    navController: NavHostController,
    onChangeDestination: (FindKunDestination) -> Unit,
    modifier: Modifier
) {
    NavHost(
        navController = navController,
        startDestination = FindKunDestination.Top.route,
        modifier = Modifier
    ) {
        composable(route = FindKunDestination.Top.route) {
            TopScreen()
        }
    }
}