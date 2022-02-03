package com.stslex.meal.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.stslex.meal.ui.screens.DetailsScreen
import com.stslex.meal.ui.screens.MainScreen

@Composable
fun NavHostInit(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "mainScreen"
    ) {
        composable("mainScreen") {
            MainScreen(navController)
        }
        composable("details") {
            DetailsScreen(navController)
        }
    }
}