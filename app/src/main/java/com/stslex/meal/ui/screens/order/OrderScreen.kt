package com.stslex.meal.ui.screens.order

import android.widget.CalendarView
import androidx.compose.animation.Animatable
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog

@ExperimentalComposeUiApi
@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Composable
fun OrderScreen() {
    val isClicked = remember {
        mutableStateOf(0)
    }
    val (showDialog, setShowDialog) = remember { mutableStateOf(false) }
    Column {
        OutlinedButton(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            onClick = {
                setShowDialog(true)
            }
        ) {
            Text(text = "Select time")
        }
        MyCalendarView(showDialog, setShowDialog)
        Row() {
            TableElement(table = listOfTables[0], isClicked = isClicked)
            TableElement(table = listOfTables[1], isClicked = isClicked)
            TableElement(table = listOfTables[2], isClicked = isClicked)
        }
        Row() {
            TableElement(table = listOfTables[3], isClicked = isClicked)
            TableElement(table = listOfTables[4], isClicked = isClicked)
            TableElement(table = listOfTables[5], isClicked = isClicked)
        }
        Row() {
            TableElement(table = listOfTables[6], isClicked = isClicked)
            TableElement(table = listOfTables[7], isClicked = isClicked)
            TableElement(table = listOfTables[8], isClicked = isClicked)
        }
    }
}

private val listOfTables = listOf(
    Table(id = 1, places = 4, available = true),
    Table(id = 2, places = 4, available = true),
    Table(id = 3, places = 4, available = true),
    Table(id = 4, places = 4, available = false),
    Table(id = 5, places = 4, available = true),
    Table(id = 6, places = 4, available = false),
    Table(id = 7, places = 4, available = true),
    Table(id = 8, places = 4, available = false),
    Table(id = 9, places = 4, available = true)
)

data class Table(
    val id: Int,
    val places: Int,
    val available: Boolean
)

@ExperimentalMaterial3Api
@Composable
fun TableElement(table: Table, isClicked: MutableState<Int>) {
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
    ElevatedCard(
        modifier = Modifier
            .width(width.value)
            .height(height.value)
            .padding(16.dp)
            .clickable {
                if (table.available) {
                    isClicked.value = if (width.value == screenWidth) 0 else table.id
                }
            },
        containerColor = if (table.available) MaterialTheme.colorScheme.surface
        else Color.LightGray
    ) {
        Text(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            text = if (table.available) "Available" else "Unavailable",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.labelMedium
        )
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

@ExperimentalComposeUiApi
@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Preview(
    name = "Order the table",
    showSystemUi = true,
    showBackground = true
)
@Composable
fun OrderScreenPreview() {
    OrderScreen()
}

@ExperimentalMaterial3Api
@ExperimentalComposeUiApi
@Composable
fun MyCalendarView(showDialog: Boolean, setShowDialog: (Boolean) -> Unit) {
    if (showDialog) {
        Dialog(
            onDismissRequest = {
                setShowDialog(false)
            }
        ) {
            ElevatedCard {
                Column(modifier = Modifier.padding(16.dp)) {
                    AndroidView({ CalendarView(it) },
                        Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        update = { view ->
                            view.setOnDateChangeListener { _, year, mon, dom ->

                            }
                        }
                    )
                    Row(
                        modifier = Modifier
                            .padding(top = 8.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        TextButton(
                            onClick = {
                                setShowDialog(false)
                            },
                        ) {
                            Text("Confirm")
                        }

                        TextButton(
                            onClick = {
                                setShowDialog(false)
                            },
                        ) {
                            Text("Dismiss")
                        }
                    }

                }
            }

        }
    }
}