package com.zfml.thecatapp.domain.repository

import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.zfml.thecatapp.domain.model.CatImage
import kotlinx.coroutines.flow.Flow

interface CatRepository {

     fun getAllCatImages(): Flow<PagingData<CatImage>>
}