package com.zfml.thecatapp.presentation.cat_list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.zfml.thecatapp.R
import com.zfml.thecatapp.domain.model.CatImage

@Composable
fun CatImageItem(
    catImage: CatImage
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
        ,
        verticalArrangement = Arrangement.Center
    ){
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(194.dp)
            ,
            placeholder = painterResource(R.drawable.placeholder),
            model = catImage.url,
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
    }

}