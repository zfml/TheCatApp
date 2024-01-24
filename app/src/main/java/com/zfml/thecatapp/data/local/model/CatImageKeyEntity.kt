package com.zfml.thecatapp.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CatImageKeyEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val prevKey: Int?,
    val nextKey: Int?
)
