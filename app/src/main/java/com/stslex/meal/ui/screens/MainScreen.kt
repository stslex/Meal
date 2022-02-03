package com.stslex.meal.ui.screens

import android.content.Context
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import coil.transform.RoundedCornersTransformation
import com.stslex.meal.ui.theme.MealTheme
import kotlinx.coroutines.Dispatchers

@Preview(
    name = "dark theme",
    showBackground = true,
    apiLevel = 32,
    showSystemUi = true,
    device = "Pixel 6"
)
@Composable
fun DefaultPreview() {
    val context: Context = LocalContext.current
    MealTheme(false) {
        MainScreen(navController = NavController(context))
    }
}

@Composable
fun MainScreen(navController: NavController) {
    val url = remember {
        "https://images.unsplash.com/photo-1640622660721-45b83554ab05?ixlib=rb-1.2.1&ixid=MnwxMjA3fDF8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=2187&q=80"
    }

    LazyColumn {
        items(10) {
            val isPressed = remember { mutableStateOf(false) }
            val targetCount by animateIntAsState(
                targetValue = if (isPressed.value) 10 else 3,
                animationSpec = TweenSpec(300, 0)
            )
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    modifier = Modifier.padding(start = 2.dp, end = 2.dp),
                    text = "Title of the post",
                    maxLines = 1,
                    style = MaterialTheme.typography.titleLarge
                )
                Image(
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .fillMaxWidth()
                        .height(300.dp)
                        .clipToBounds()
                        .clickable {
                            navController.navigate("details")
                        },
                    contentScale = ContentScale.FillBounds,
                    painter = rememberImagePainter(url,
                        builder = {
                            memoryCacheKey(url)
                            placeholderMemoryCacheKey(url)
                            transformations(RoundedCornersTransformation())
                            allowHardware(true)
                            dispatcher(Dispatchers.IO)
                        }),
                    contentDescription = null
                )
                Text(
                    modifier = Modifier
                        .clickable {
                            isPressed.value = !isPressed.value
                        }
                        .padding(top = 8.dp, bottom = 16.dp, start = 2.dp, end = 2.dp),
                    maxLines = targetCount,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Justify,
                    style = MaterialTheme.typography.bodyMedium,
                    text = "In the above example, the composable will recompose when the ImagePainter's state changes. " +
                            "If the image request is successful and not served from the memory cache, it'll execute the animation inside the if statement. " +
                            "In most cases the local ImageLoader will be the singleton ImageLoader, however it's possible to overwrite the local ImageLoader using a CompositionLocalProvider if necessary.\n"
                )
            }

        }
    }

}