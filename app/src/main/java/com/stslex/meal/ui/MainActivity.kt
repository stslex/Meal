package com.stslex.meal.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.stslex.meal.ui.navigation.NavigationHost
import com.stslex.meal.ui.navigation.ScreenNavigation
import com.stslex.meal.ui.theme.MealTheme
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MealTheme {
                val navController: NavHostController = rememberNavController()
                Scaffold(
                    bottomBar = bottomAppBar(navController = navController)
                ) {
                    NavigationHost(navController = navController)
                }
            }
        }
    }

    private fun bottomAppBar(navController: NavHostController): @Composable () -> Unit = {
        BottomNavigation(
            backgroundColor = MaterialTheme.colorScheme.surface
        ) {
            val selectedItem = remember { mutableStateOf(ScreenNavigation.News.route) }
            val items = listOf(ScreenNavigation.News, ScreenNavigation.Order)
            items.forEach { screen ->
                BottomNavigationItem(
                    icon = { Icon(screen.icon, screen.route) },
                    label = { Text(stringResource(screen.resourceId)) },
                    selected = selectedItem.value == screen.route,
                    onClick = {
                        selectedItem.value = screen.route
                        navController.navigate(screen.route) {
                            navController.graph.startDestinationRoute?.let { route ->
                                popUpTo(route) {
                                    inclusive = true
                                    saveState = true
                                }
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    alwaysShowLabel = false
                )
            }
        }
    }
}