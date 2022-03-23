package com.stslex.meal.ui.screens.order.timer_dialog

import android.widget.TimePicker
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import java.util.*
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
@ExperimentalMaterial3Api
@ExperimentalComposeUiApi
@Composable
fun TimePickDialog(
    showDialog: Boolean,
    setShowDialog: (Boolean) -> Unit,
    timestamp: MutableState<Date>
) {
    if (showDialog) {
        Dialog(
            onDismissRequest = {
                setShowDialog(false)
            }
        ) {
            ElevatedCard {
                Column(modifier = Modifier.padding(16.dp)) {
                    val calendar = Calendar.getInstance(Locale.getDefault())
                    calendar.time = remember { timestamp.value }
                    AndroidView(
                        factory = { TimePicker(it) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        update = { view ->
                            view.setIs24HourView(true)
                            view.hour = calendar[Calendar.HOUR]
                            view.minute = calendar[Calendar.MINUTE]
                            view.setOnTimeChangedListener { timePicker, hours, minutes ->
                                val year = calendar[Calendar.YEAR]
                                val month = calendar[Calendar.MONTH]
                                val day = calendar[Calendar.DAY_OF_MONTH]
                                calendar.set(year, month, day, hours, minutes)
                                timePicker.hour = calendar[Calendar.HOUR]
                                timePicker.minute = calendar[Calendar.MINUTE]
                                timestamp.value = calendar.time
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