package com.stslex.meal.ui.navigation

import androidx.annotation.StringRes
import com.stslex.meal.R

sealed class Screen(val route: String, @StringRes val resourceId: Int) {
    object Main : Screen("main", R.string.title)
    object Details : Screen("details", R.string.url)
}