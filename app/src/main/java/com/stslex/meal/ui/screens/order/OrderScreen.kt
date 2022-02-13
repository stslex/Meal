package com.stslex.meal.ui.screens.order

import androidx.compose.animation.Animatable
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun OrderScreen() {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp

    val elementWidth = screenWidth / 2
    val elementHeight = screenHeight / 4
    val modifier: Modifier = Modifier
        .height(elementHeight)
        .width(elementWidth)
    Column {
        Row {
            OneElement(modifier)
            OneElement(modifier)
        }
        Row {
            OneElement(modifier)
            OneElement(modifier)
        }
        Row {
            OneElement(modifier)
            OneElement(modifier)
        }
    }
}

@Composable
fun OneElement(modifier: Modifier) {
    val itemSelected = remember {
        mutableStateOf(false)
    }
    val color = remember {
        Animatable(Color.Gray)
    }
    LaunchedEffect(itemSelected.value) {
        color.animateTo(
            targetValue = if (itemSelected.value) Color.DarkGray else Color.Gray,
            animationSpec = tween(durationMillis = 1000, easing = FastOutLinearInEasing)
        )
    }
    Canvas(
        modifier = modifier
            .clickable {
                itemSelected.value = !itemSelected.value
            }
    ) {
        val canvasSize = size
        val rectSize = Size(canvasSize.width / 2f, 30f)
        val offset = Offset(rectSize.width / 2f, canvasSize.height / 3f)
        drawRect(
            color = color.value,
            size = rectSize,
            topLeft = offset
        )
        val leftOffset = Offset(
            x = offset.x + 15f,
            y = offset.y + rectSize.height + 15f
        )

        drawRect(
            color = color.value,
            size = Size(30f, 90f),
            topLeft = leftOffset
        )
        val rightOffset = Offset(
            x = offset.x + rectSize.width - 15f - 30f,
            y = offset.y + rectSize.height + 15f
        )
        drawRect(
            color = color.value,
            size = Size(30f, 90f),
            topLeft = rightOffset
        )

        val ovalOffset = Offset(
            x = canvasSize.width / 2 - 45f,
            y = offset.y - 30f - 15f
        )
        drawOval(
            color = color.value,
            size = Size(90f, 30f),
            topLeft = ovalOffset
        )

        val canvasWidth = size.width
        val canvasHeight = size.height
        drawLine(
            color = Color.Black,
            start = Offset(0f, canvasHeight),
            end = Offset(canvasWidth, canvasHeight),
        )
        drawLine(
            color = Color.Black,
            start = Offset(canvasWidth, canvasHeight),
            end = Offset(canvasWidth, 0f),
        )
    }
}

@Preview(
    name = "Order the table",
    showSystemUi = true,
    showBackground = true
)
@Composable
fun OrderScreenPreview() {
    OrderScreen()
}