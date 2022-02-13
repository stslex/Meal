package com.stslex.meal.ui.screens.news

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import coil.transform.RoundedCornersTransformation
import com.stslex.meal.ui.common.customPlaceholder
import com.stslex.meal.ui.model.ImageModel
import com.stslex.meal.ui.navigation.Screen
import kotlinx.coroutines.Dispatchers

@Composable
fun MainScreenImage(imageModel: ImageModel?, navController: NavController) {
    Image(
        modifier = Modifier
            .height(300.dp)
            .fillMaxWidth()
            .clipToBounds()
            .clickable {
                navController.navigate(Screen.Details.route)
            }
            .customPlaceholder(),
        contentScale = ContentScale.FillBounds,
        painter = rememberImagePainter(
            data = imageModel?.url(),
            builder = {
                memoryCacheKey(imageModel?.url())
                placeholderMemoryCacheKey(imageModel?.url())
                transformations(RoundedCornersTransformation())
                allowHardware(true)
                dispatcher(Dispatchers.IO)
            }),
        contentDescription = null
    )
}