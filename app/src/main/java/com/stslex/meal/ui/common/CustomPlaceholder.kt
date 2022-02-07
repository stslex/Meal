package com.stslex.meal.ui.common

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.fade
import com.google.accompanist.placeholder.placeholder


fun Modifier.customPlaceholder() = placeholder(
    visible = false,
    highlight = PlaceholderHighlight.fade(highlightColor = Color.LightGray),
    color = Color.Gray
)