package com.zfml.thecatapp.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CatImageEntity(
    @PrimaryKey
    val id: String,
    val url: String
)
