package com.stslex.meal.ui.screens.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun DetailsScreen(navController: NavController, viewModel: DetailsViewModel) {
    Column {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
        ) {
            GlideImage(
                imageModel = "https://lh5.googleusercontent.com/p/AF1QipPm5wvpCzgThptV0eaUwlEUDt4mhuyA7nN1ROf1=w900-h975-p-k-no"
            )
        }
    }
}