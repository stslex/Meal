package com.stslex.meal.ui.navigation

import androidx.annotation.StringRes
import com.stslex.meal.R

sealed class Screen(val route: String, @StringRes val resourceId: Int) {
    object News : Screen("news", R.string.news)
    object Order : Screen("order", R.string.order)
    object Details : Screen("details", R.string.url)
}