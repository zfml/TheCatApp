package com.zfml.thecatapp.presentation.cat_list

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatImageListScreen(
    viewModel: CatImageViewModel = hiltViewModel(),
) {
    val catImages = viewModel.catImages.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Cats")
                }
            )
        },
        content = { padding ->
            Column(modifier = Modifier.padding(padding)){
                CatListContent(catImages = catImages)
            }
        }
    )
}