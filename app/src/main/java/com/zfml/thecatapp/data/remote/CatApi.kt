package com.zfml.thecatapp.data.remote


import retrofit2.http.GET
import retrofit2.http.Query

interface CatApi {
    @GET("images/search?limit=20")
    suspend fun getAllCatImages(
        @Query("page") page: Int,
        @Query("api_key") apiKey: String = "live_A5dCv3dJ5qkRObZdLtOiaaZvmrAy5aQAY7M2UvhwVBHWBJ9h7ObWFu5BbB1h7I0V"
    ): List<CatImageDto>

    companion object{
        const val BASE_URL = "https://api.thecatapi.com/v1/"
    }
}