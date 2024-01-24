package com.zfml.thecatapp.data.local.dao

import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.zfml.thecatapp.data.local.model.CatImageEntity

@Dao
interface CatImageDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCatImages(catImages: List<CatImageEntity>)

    @Query("SELECT * FROM CatImageEntity")
    fun getAllCatImages(): PagingSource<Int,CatImageEntity>


    @Query("DELETE FROM CatImageEntity")
    suspend fun clearAllCatImages()

}