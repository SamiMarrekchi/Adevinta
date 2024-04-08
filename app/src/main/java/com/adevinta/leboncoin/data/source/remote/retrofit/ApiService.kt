package com.adevinta.leboncoin.data.source.remote.retrofit

import com.adevinta.leboncoin.data.source.remote.response.AlbumResponse
import retrofit2.http.GET

interface ApiService {

    @GET("technical-test.json")
    suspend fun getAlbums(): List<AlbumResponse>

}