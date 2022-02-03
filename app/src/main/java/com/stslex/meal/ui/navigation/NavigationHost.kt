package com.stslex.meal.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.stslex.meal.ui.screens.detail.DetailsScreen
import com.stslex.meal.ui.screens.main.MainScreen
import com.stslex.meal.ui.screens.main.MainScreenViewModel
import javax.inject.Inject

interface NavigationHost {

    fun init(): @Composable () -> Unit

    class Base @Inject constructor() : NavigationHost {

        override fun init(): @Composable () -> Unit = {
            val navController: NavHostController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = Screen.Main.route
            ) {
                composable(Screen.Main.route) {
                    val parentEntry = remember {
                        navController.getBackStackEntry("Parent")
                    }
                    val viewModel: MainScreenViewModel = hiltViewModel(parentEntry)
                    MainScreen(navController = navController, viewModel = viewModel)
                }
                composable(Screen.Details.route) {
                    DetailsScreen(navController)
                }
            }
        }
    }
}