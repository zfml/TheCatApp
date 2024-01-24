package com.zfml.thecatapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.zfml.thecatapp.data.local.model.CatImageKeyEntity

@Dao
interface CatImageKeyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCatImageKeys(keys: List<CatImageKeyEntity>)

    @Query("SELECT * FROM CatImageKeyEntity")
    suspend fun getAllImageKeys(): List<CatImageKeyEntity>

    @Query("SELECT * FROM CatImageKeyEntity WHERE id=:id")
    suspend fun getCatImageKeyById(id: String): CatImageKeyEntity?

    @Query("DELETE FROM CatImageKeyEntity")
    fun clearAllImageKeys()

}