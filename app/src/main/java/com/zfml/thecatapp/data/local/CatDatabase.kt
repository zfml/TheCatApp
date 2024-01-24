package com.zfml.thecatapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.zfml.thecatapp.data.local.dao.CatImageDao
import com.zfml.thecatapp.data.local.dao.CatImageKeyDao
import com.zfml.thecatapp.data.local.model.CatImageEntity
import com.zfml.thecatapp.data.local.model.CatImageKeyEntity

@Database(
    entities = [CatImageEntity::class,CatImageKeyEntity::class],
    version = 1
)
abstract class CatDatabase: RoomDatabase() {
    abstract val cateImageDao: CatImageDao
    abstract val cateImageKeyDao: CatImageKeyDao
}