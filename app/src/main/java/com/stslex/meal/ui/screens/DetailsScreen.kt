package com.stslex.meal.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter

@Composable
fun DetailsScreen(navController: NavController) {
    Column {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
        ) {
            Image(
                painter = rememberImagePainter("https://lh5.googleusercontent.com/p/AF1QipPm5wvpCzgThptV0eaUwlEUDt4mhuyA7nN1ROf1=w900-h975-p-k-no"),
                contentDescription = "image"
            )
        }
    }
}