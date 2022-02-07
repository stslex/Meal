package com.stslex.meal.ui.screens.main

import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.stslex.meal.ui.common.customPlaceholder

@Composable
fun MainScreenContentText() {
    val isPressed = remember { mutableStateOf(false) }
    val targetCount by animateIntAsState(
        targetValue = if (isPressed.value) 10 else 3,
        animationSpec = TweenSpec(300, 0)
    )
    Text(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .customPlaceholder()
            .clickable {
                isPressed.value = !isPressed.value
            },
        maxLines = targetCount,
        overflow = TextOverflow.Ellipsis,
        textAlign = TextAlign.Justify,
        style = MaterialTheme.typography.bodyMedium,
        text = "In the above example, the composable will recompose when the ImagePainter's state changes. " +
                "If the image request is successful and not served from the memory cache, it'll execute the animation inside the if statement. " +
                "In most cases the local ImageLoader will be the singleton ImageLoader, however it's possible to overwrite the local ImageLoader using a CompositionLocalProvider if necessary.\n"
    )
}