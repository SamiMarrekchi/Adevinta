package com.adevinta.leboncoin.data.source.remote.datasource

import com.adevinta.leboncoin.data.source.remote.response.asModel
import com.adevinta.leboncoin.data.source.remote.retrofit.ApiService
import com.adevinta.leboncoin.di.AppDispatchers
import com.adevinta.leboncoin.di.Dispatcher
import com.adevinta.leboncoin.domain.model.Album
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RemoteAlbumDataSource @Inject constructor(private val apiService: ApiService,
                                                @Dispatcher(AppDispatchers.IO) private val ioDispatcher: CoroutineDispatcher) {

    suspend fun fetchAlbums(): List<Album> = withContext(ioDispatcher) {
        apiService.getAlbums().map { it.asModel() }
    }

}