package com.stslex.meal.ui.screens.news

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bumptech.glide.Glide
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.glide.GlideImage
import com.stslex.meal.R
import com.stslex.meal.ui.common.customPlaceholder
import com.stslex.meal.ui.model.ImageModel
import com.stslex.meal.ui.navigation.Screen

@Composable
fun MainScreenImage(imageModel: ImageModel?, navController: NavController) {
    GlideImage(
        modifier = Modifier
            .height(300.dp)
            .fillMaxWidth()
            .clipToBounds()
            .clickable {
                navController.navigate(Screen.Details.route)
            }
            .customPlaceholder(),
        imageModel = imageModel?.url(),
        contentScale = ContentScale.FillBounds,
        circularReveal = CircularReveal(duration = 1000),
        requestBuilder = {
            Glide.with(LocalContext.current.applicationContext).asDrawable()
        }
    )
}