package com.zfml.thecatapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.zfml.thecatapp.presentation.cat_list.CatImageListScreen
import com.zfml.thecatapp.ui.theme.TheCatAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TheCatAppTheme {
                CatImageListScreen()
            }
        }
    }
}
