package com.stslex.meal.ui.screens.news

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.stslex.meal.ui.common.setScrollingColumnAnimation
import com.stslex.meal.ui.model.ImageModel

@ExperimentalMaterial3Api
@Composable
fun NewsScreen(
    navController: NavController,
    viewModel: NewsScreenViewModel
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
    navController: NavController,
    lazyListState: LazyListState = rememberLazyListState()
) {
    LazyColumn(
        state = lazyListState
    ) {
        items(
            items = lazyPagingItems,
            key = { it.url() }
        ) { imageModel ->
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .setScrollingColumnAnimation(lazyListState, imageModel?.url())
            ) {
                MainScreenTitleText(imageModel = imageModel)
                MainScreenImage(imageModel = imageModel, navController = navController)
                NewsScreenContentText()
            }
        }
    }
}