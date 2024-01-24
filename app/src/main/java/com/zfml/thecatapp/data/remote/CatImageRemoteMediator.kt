package com.zfml.thecatapp.data.remote

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.zfml.thecatapp.data.local.CatDatabase
import com.zfml.thecatapp.data.local.mappers.toCatImageEntity
import com.zfml.thecatapp.data.local.model.CatImageEntity
import com.zfml.thecatapp.data.local.model.CatImageKeyEntity
import java.lang.Exception

@OptIn(ExperimentalPagingApi::class)
class CatImageRemoteMediator constructor(
    private val db: CatDatabase,
    private val api: CatApi
): RemoteMediator<Int,CatImageEntity>(){
    private val catImageDao = db.cateImageDao
    private val catImageKeyDao = db.cateImageKeyDao
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, CatImageEntity>,
    ): MediatorResult {
        return try {


            val page = when(loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextKey?.minus(1) ?: 1
                }

                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    val prevPage =  remoteKeys?.prevKey  ?:
                    return MediatorResult.Success(
                        endOfPaginationReached = remoteKeys != null
                    )
                    prevPage
                }
                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    val nextPage = remoteKeys?.nextKey ?:
                    return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                    nextPage
                }
            }




            val catImages = api.getAllCatImages(page)

            val endOfPagination = catImages.isEmpty()

            val prevPage = if(page == 1) null else page -1
            val nextPage = if(endOfPagination) null else page + 1

            db.withTransaction{
                if(loadType == LoadType.REFRESH) {

                    catImageDao.clearAllCatImages()
                    catImageKeyDao.clearAllImageKeys()
                }
                val keys = catImages.map {
                    CatImageKeyEntity(
                        id = it.id,
                        prevKey = prevPage,
                        nextKey = nextPage
                    )
                }
                catImageKeyDao.insertCatImageKeys(keys)


                catImageDao.insertAllCatImages(catImages = catImages.map { it.toCatImageEntity() })

            }



            MediatorResult.Success(endOfPaginationReached = endOfPagination)


        }catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }


    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int,CatImageEntity>
    ): CatImageKeyEntity? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                catImageKeyDao.getCatImageKeyById(id)
            }

        }
    }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int,CatImageEntity>
    ): CatImageKeyEntity? {
        return state.pages.firstOrNull{it.data.isNotEmpty()}?.data?.firstOrNull()
            ?.let { catImage ->
            catImageKeyDao.getCatImageKeyById(catImage.id)
        }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, CatImageEntity>
    ): CatImageKeyEntity? {
        return state.pages.lastOrNull{it.data.isNotEmpty()}?.data?.lastOrNull()
            ?.let { catImage ->
                    catImageKeyDao.getCatImageKeyById(catImage.id)

            }
    }


}