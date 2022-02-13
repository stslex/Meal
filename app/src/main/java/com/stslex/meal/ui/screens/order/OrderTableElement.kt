package com.stslex.meal.ui.screens.order

import androidx.activity.compose.BackHandler
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.dp
import com.stslex.meal.R
import com.stslex.meal.ui.screens.order.model.OrderTable

@ExperimentalUnitApi
@ExperimentalMaterial3Api
@Composable
fun TableElement(table: OrderTable, isClicked: MutableState<Int>) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenWidthDp.dp

    val width = animateDpAsState(
        targetValue = if (isClicked.value == table.id) screenWidth else if (isClicked.value == 0) screenWidth / 3 else 0.dp,
        animationSpec = tween(durationMillis = 500, easing = FastOutLinearInEasing)
    )
    val height = animateDpAsState(
        targetValue = if (isClicked.value == table.id) screenHeight else if (isClicked.value == 0) screenHeight / 3 else 0.dp,
        animationSpec = tween(durationMillis = 500, easing = FastOutLinearInEasing)
    )
    val color = animateColorAsState(
        targetValue = if (isClicked.value == table.id) {
            Color.LightGray
        } else if (isClicked.value == 0) {
            if (table.available) MaterialTheme.colorScheme.surface else Color.LightGray
        } else Color.White,
        animationSpec = tween(durationMillis = 500, easing = FastOutLinearInEasing)
    )
    val textColor = animateColorAsState(
        targetValue = if (isClicked.value == table.id || isClicked.value == 0) {
            Color.Black
        } else Color.White,
        animationSpec = tween(durationMillis = 500, easing = FastOutLinearInEasing)
    )
    val labelId = if (table.available) R.string.label_available else R.string.label_unavailable
    val textLabel = stringResource(id = labelId)

    Column {
        ElevatedCard(
            modifier = Modifier
                .width(width.value)
                .height(height.value)
                .padding(16.dp)
                .wrapContentSize(Alignment.Center)
                .clickable {
                    if (table.available) {
                        isClicked.value = if (width.value == screenWidth) 0 else table.id
                    }
                },
            containerColor = color.value
        ) {
            BackHandler(enabled = width.value == screenWidth) {
                isClicked.value = 0
            }
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    modifier = Modifier.padding(8.dp),
                    text = textLabel,
                    style = MaterialTheme.typography.labelLarge,
                    color = textColor.value,
                    maxLines = 1
                )
            }
        }
    }

}