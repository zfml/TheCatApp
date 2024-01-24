package com.zfml.thecatapp.di

import android.app.Application
import androidx.room.Room
import com.zfml.thecatapp.data.local.CatDatabase
import com.zfml.thecatapp.data.remote.CatApi
import com.zfml.thecatapp.data.repository.CatRepositoryImpl
import com.zfml.thecatapp.domain.repository.CatRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideCatDatabase(app: Application): CatDatabase {
        return Room.databaseBuilder(
            app,
            CatDatabase::class.java,
            "catdb.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideCatApi(): CatApi {
        return Retrofit.Builder()
            .baseUrl(CatApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CatApi::class.java)
    }



}