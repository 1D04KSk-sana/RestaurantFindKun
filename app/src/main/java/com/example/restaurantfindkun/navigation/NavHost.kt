package com.example.restaurantfindkun.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.restaurantfindkun.screen.detail.DetailScreen
import com.example.restaurantfindkun.screen.result.ResultScreen
import com.example.restaurantfindkun.screen.top.TopScreen

//
//画面遷移を行うナビゲーション
//

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
        //初期画面
        composable(route = FindKunDestination.Top.route) {
            TopScreen(
                modifier = modifier,
                moveNextScreen = {
                    navController.navigate(FindKunDestination.Result.route) {
                        onChangeDestination(FindKunDestination.Result)
                        popUpTo(FindKunDestination.Top.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        //検索結果
        composable(route = FindKunDestination.Result.route) {
            ResultScreen(
                modifier = modifier,
                moveNextScreen = {
                    navController.navigate(FindKunDestination.Detail.route) {
                        onChangeDestination(FindKunDestination.Detail)
                        popUpTo(FindKunDestination.Result.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable(route = FindKunDestination.Detail.route) {
            DetailScreen()
        }
    }
}