package com.stslex.meal.ui.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.outlined.List
import androidx.compose.ui.graphics.vector.ImageVector

sealed class ScreenNavigation(
    val route: String,
    @StringRes val resourceId: Int,
    val icon: ImageVector
) {
    object News : ScreenNavigation(
        Screen.News.route,
        Screen.News.resourceId,
        Icons.Outlined.List
    )

    object Order : ScreenNavigation(
        Screen.Order.route,
        Screen.Order.resourceId,
        Icons.Filled.Create
    )
}
