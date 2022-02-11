package com.stslex.meal.ui.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.stslex.meal.ui.screens.detail.DetailsScreen
import com.stslex.meal.ui.screens.detail.DetailsViewModel
import com.stslex.meal.ui.screens.main.MainScreen
import com.stslex.meal.ui.screens.main.MainScreenViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

interface NavigationHost {

    @ExperimentalCoroutinesApi
    fun init(): @Composable () -> Unit

    class Base @Inject constructor() : NavigationHost {

        @ExperimentalCoroutinesApi
        override fun init(): @Composable () -> Unit = {
            val navController: NavHostController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = Screen.Main.route
            ) {
                composable(Screen.Main.route) {
                    val viewModel: MainScreenViewModel = hiltViewModel()
                    MainScreen(navController = navController, viewModel = viewModel)
                }
                composable(Screen.Details.route) {
                    val viewModel: DetailsViewModel = hiltViewModel()
                    DetailsScreen(navController, viewModel = viewModel)
                }
            }
        }
    }
}