package com.stslex.meal.ui.screens.main

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.stslex.meal.ui.common.customPlaceholder
import com.stslex.meal.ui.model.ImageModel

@Composable
fun MainScreenTitleText(imageModel: ImageModel?) {
    Text(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .customPlaceholder(),
        text = imageModel?.description().toString(),
        maxLines = 1,
        style = MaterialTheme.typography.titleLarge
    )
}