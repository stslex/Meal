package com.stslex.meal.ui.screens.order.date_time_picker

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.stslex.meal.utils.TimeUtil.convertDate
import com.stslex.meal.utils.TimeUtil.convertTime
import java.util.*


@ExperimentalMaterial3Api
@Composable
fun ColumnScope.DateTimePickerText(
    setCalendarDialog: (Boolean) -> Unit,
    setTimeDialog: (Boolean) -> Unit,
    settingTimestamp: MutableState<Date>
) {
    Row(
        modifier = Modifier
            .padding(top = 16.dp)
            .align(Alignment.CenterHorizontally)
    ) {
        OutlinedCard(
            modifier = Modifier
                .padding(4.dp)
                .clickable {
                    setCalendarDialog(true)
                }
        ) {
            TimeDateElementText(text = settingTimestamp.value.convertDate())
        }
        OutlinedCard(
            modifier = Modifier
                .padding(top = 4.dp, start = 32.dp)
                .clickable {
                    setTimeDialog(true)
                }
        ) {
            TimeDateElementText(text = settingTimestamp.value.convertTime())
        }
    }
}

@Composable
fun TimeDateElementText(text: String) {
    Row(horizontalArrangement = Arrangement.Center) {
        Text(
            modifier = Modifier.padding(8.dp),
            text = text,
            style = MaterialTheme.typography.labelLarge
        )
        Icon(
            modifier = Modifier.padding(8.dp),
            imageVector = Icons.Filled.ArrowDropDown,
            contentDescription = null
        )
    }
}