package com.stslex.meal.ui.screens.order.calendar_dialog

import android.widget.CalendarView
import android.widget.Spinner
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



@ExperimentalMaterial3Api
@ExperimentalComposeUiApi
@Composable
fun CalendarPickDialog(
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
                    AndroidView({ CalendarView(it) },
                        Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        update = { view ->
                            view.date = calendar.time.time
                            view.setOnDateChangeListener { viewPicker, year, mon, dom ->
                                val hours = calendar[Calendar.HOUR]
                                val minutes = calendar[Calendar.MINUTE]
                                calendar.set(year, mon, dom, hours, minutes)
                                viewPicker.date = calendar.time.time
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