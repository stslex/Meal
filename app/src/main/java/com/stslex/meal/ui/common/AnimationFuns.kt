package com.stslex.meal.ui.common

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import kotlin.math.absoluteValue

fun Modifier.setScrollingColumnAnimation(
    lazyListState: LazyListState,
    id: String?
): Modifier = graphicsLayer {
    val value = 1 - (lazyListState.normalizedPosition(id).absoluteValue * 0.05f)
    alpha = value
    scaleX = value
    scaleY = value
}

private fun LazyListState.normalizedPosition(key: String?): Float = with(layoutInfo) {
    visibleItemsInfo.firstOrNull {
        it.key == key
    }?.let {
        val center = (viewportEndOffset + viewportStartOffset - it.size) / 2F
        (it.offset.toFloat() - center) / center
    } ?: 0F
}