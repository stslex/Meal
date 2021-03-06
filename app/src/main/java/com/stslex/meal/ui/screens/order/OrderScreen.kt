package com.stslex.meal.ui.screens.order

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.dp
import com.stslex.meal.ui.screens.order.calendar_dialog.CalendarPickDialog
import com.stslex.meal.ui.screens.order.date_time_picker.DateTimePickerText
import com.stslex.meal.ui.screens.order.model.OrderTable
import com.stslex.meal.ui.screens.order.timer_dialog.TimePickDialog
import java.util.*

@ExperimentalUnitApi
@ExperimentalComposeUiApi
@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Composable
fun OrderScreen() {
    val isClicked = remember {
        mutableStateOf(0)
    }
    val (showCalendarDialog, setCalendarDialog) = remember { mutableStateOf(false) }
    val (showTimeDialog, setTimeDialog) = remember { mutableStateOf(false) }
    val timestamp: MutableState<Date> = remember {
        mutableStateOf(Date(System.currentTimeMillis()))
    }
    Column {
        DateTimePickerText(
            setCalendarDialog = setCalendarDialog,
            setTimeDialog = setTimeDialog,
            timestamp
        )
        CalendarPickDialog(
            showDialog = showCalendarDialog,
            setShowDialog = setCalendarDialog,
            timestamp = timestamp
        )
        TimePickDialog(
            showDialog = showTimeDialog,
            setShowDialog = setTimeDialog,
            timestamp = timestamp
        )
        Spacer(modifier = Modifier.padding(32.dp))
        val rowsNumber = 3
        for (rowIndex in 0 until rowsNumber) {
            Row {
                val tableInit = rowIndex * rowsNumber
                val tableEnd = tableInit + 2
                for (tableNumber in tableInit..tableEnd) {
                    TableElement(table = listOfTables[tableNumber], isClicked = isClicked)
                }
            }
        }
    }
}

private val listOfTables = listOf(
    OrderTable(id = 1, places = 4, available = true),
    OrderTable(id = 2, places = 4, available = true),
    OrderTable(id = 3, places = 4, available = true),
    OrderTable(id = 4, places = 4, available = false),
    OrderTable(id = 5, places = 4, available = true),
    OrderTable(id = 6, places = 4, available = false),
    OrderTable(id = 7, places = 4, available = true),
    OrderTable(id = 8, places = 4, available = false),
    OrderTable(id = 9, places = 4, available = true)
)

@ExperimentalUnitApi
@ExperimentalComposeUiApi
@ExperimentalMaterial3Api
@ExperimentalFoundationApi
@Preview(
    name = "Order the table",
    showSystemUi = true,
    showBackground = true
)
@Composable
fun OrderScreenPreview() {
    OrderScreen()
}