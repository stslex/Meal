package com.stslex.meal.ui.screens.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.stslex.meal.ui.model.ImageModel
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Composable
fun MainScreen(
    navController: NavController,
    viewModel: MainScreenViewModel
) {
    val lazyPagingItems: LazyPagingItems<ImageModel> = viewModel.photos.collectAsLazyPagingItems()
    MainScreenLazyColumn(
        navController = navController,
        lazyPagingItems = lazyPagingItems
    )
}

@Composable
fun MainScreenLazyColumn(
    lazyPagingItems: LazyPagingItems<ImageModel>,
    navController: NavController
) {
    LazyColumn {
        items(lazyPagingItems) { imageModel ->
            Surface(
                modifier = Modifier.padding(16.dp),
                shadowElevation = 4.dp,
                tonalElevation = 4.dp,
                color = MaterialTheme.colorScheme.surfaceVariant
            ) {
                Column {
                    MainScreenImage(imageModel = imageModel, navController = navController)
                    MainScreenContentText()
                }
                MainScreenTitleText(imageModel = imageModel)
            }
        }
    }
}