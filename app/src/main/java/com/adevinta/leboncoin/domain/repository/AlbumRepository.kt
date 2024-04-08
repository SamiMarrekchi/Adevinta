package com.adevinta.leboncoin.domain.repository

import androidx.paging.PagingData
import com.adevinta.leboncoin.domain.model.Album
import kotlinx.coroutines.flow.Flow


interface AlbumRepository {
    fun getRemoteAlbum(): Flow<Result<Boolean>>
    fun getPagingAlbum(): Flow<PagingData<Album>>
    fun getAlbum(albumId: Int): Flow<Album?>
}