package com.zfml.thecatapp.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.zfml.thecatapp.data.local.CatDatabase
import com.zfml.thecatapp.data.local.mappers.toCatImage
import com.zfml.thecatapp.data.remote.CatApi
import com.zfml.thecatapp.data.remote.CatImageRemoteMediator
import com.zfml.thecatapp.domain.model.CatImage
import com.zfml.thecatapp.domain.repository.CatRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CatRepositoryImpl @Inject constructor(
    private val api: CatApi,
    private val db: CatDatabase,
) : CatRepository {

    @OptIn(ExperimentalPagingApi::class)
    override fun getAllCatImages(): Flow<PagingData<CatImage>> {
        return Pager(
            config = PagingConfig(20),
            remoteMediator = CatImageRemoteMediator(api = api, db = db),
            pagingSourceFactory = { db.cateImageDao.getAllCatImages() }
        ).flow.map { it.map { it.toCatImage() } }
    }
}