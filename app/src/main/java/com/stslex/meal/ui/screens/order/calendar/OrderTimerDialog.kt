package com.stslex.meal.ui.screens.order.calendar

import android.widget.TimePicker
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog

@ExperimentalMaterial3Api
@ExperimentalComposeUiApi
@Composable
fun MyTimerView(showDialog: Boolean, setShowDialog: (Boolean) -> Unit) {
    if (showDialog) {
        Dialog(
            onDismissRequest = {
                setShowDialog(false)
            }
        ) {
            ElevatedCard {
                Column(modifier = Modifier.padding(16.dp)) {
                    AndroidView({ TimePicker(it) },
                        Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        update = { view ->
                            view.setOnTimeChangedListener { timePicker, i, i2 -> }
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