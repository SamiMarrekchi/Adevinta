package com.adevinta.leboncoin.data.repository

import androidx.paging.PagingData
import com.adevinta.leboncoin.data.source.local.datasource.LocalAlbumDataSource
import com.adevinta.leboncoin.data.source.remote.datasource.RemoteAlbumDataSource
import com.adevinta.leboncoin.domain.model.Album
import com.adevinta.leboncoin.domain.repository.AlbumRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AlbumRepositoryImp @Inject constructor(
    private val localAlbumDataSource: LocalAlbumDataSource,
    private val remoteAlbumDataSource: RemoteAlbumDataSource,
) : AlbumRepository {
    override fun getRemoteAlbum(): Flow<Result<Boolean>> = flow {
        val localData = localAlbumDataSource.getAlbums()
        try {
            val remoteData = remoteAlbumDataSource.fetchAlbums()
            localAlbumDataSource.insert(remoteData)
            emit(Result.success(true))
        } catch (e: Exception) {
            if (localData.isNotEmpty()) {
                emit(Result.success(true))
            } else {
                emit(Result.failure(e))
            }
        }
    }

    override fun getPagingAlbum(): Flow<PagingData<Album>> {
        return localAlbumDataSource.getAlbumFromLocal()
    }

    override fun getAlbum(albumId: Int): Flow<Album?> {
        return localAlbumDataSource.getAlbum(albumId)
    }

}




