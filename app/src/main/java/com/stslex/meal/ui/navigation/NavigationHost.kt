package com.stslex.meal.ui.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.stslex.meal.ui.screens.detail.DetailsScreen
import com.stslex.meal.ui.screens.detail.DetailsViewModel
import com.stslex.meal.ui.screens.news.NewsScreen
import com.stslex.meal.ui.screens.news.NewsScreenViewModel
import com.stslex.meal.ui.screens.order.OrderScreen

@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Composable
fun NavigationHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.News.route
    ) {
        composable(Screen.News.route) {
            val viewModel: NewsScreenViewModel = hiltViewModel()
            NewsScreen(navController = navController, viewModel = viewModel)
        }
        composable(Screen.Details.route) {
            val viewModel: DetailsViewModel = hiltViewModel()
            DetailsScreen(navController, viewModel = viewModel)
        }
        composable(Screen.Order.route) {
            OrderScreen()
        }
    }
}