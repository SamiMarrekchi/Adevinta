package com.adevinta.leboncoin.data.source.local.datasource

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.adevinta.leboncoin.data.source.local.dao.AlbumDao
import com.adevinta.leboncoin.data.source.local.entity.asEntity
import com.adevinta.leboncoin.data.source.local.entity.asModel
import com.adevinta.leboncoin.domain.model.Album
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalAlbumDataSource @Inject constructor(private val albumDao: AlbumDao) {

    fun getAlbumFromLocal(): Flow<PagingData<Album>> {
        return Pager(config = PagingConfig(pageSize = 50), pagingSourceFactory = { albumDao.observeAll() }).flow.map { pagingData ->
            pagingData.map { it.asModel() }
        }
    }

    suspend fun getAlbums(): List<Album> {
        return albumDao.getAll().map { it.asModel() }
    }

    suspend fun insert(albums: List<Album>) {
        val albumEntities = albums.map {
            it.asEntity()
        }
        albumDao.upsertAll(albumEntities)
    }

    fun getAlbum(albumId: Int): Flow<Album?> {
        return albumDao.observeById(albumId).map {
            it?.asModel()
        }
    }
}