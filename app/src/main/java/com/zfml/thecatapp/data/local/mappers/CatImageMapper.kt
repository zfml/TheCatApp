package com.zfml.thecatapp.data.local.mappers

import com.zfml.thecatapp.data.local.model.CatImageEntity
import com.zfml.thecatapp.data.remote.CatImageDto
import com.zfml.thecatapp.domain.model.CatImage

fun CatImageDto.toCatImageEntity() = CatImageEntity(
    id = id,
    url = url ?: ""
)

fun CatImageEntity.toCatImage() = CatImage(
    id = id,
    url = url
)