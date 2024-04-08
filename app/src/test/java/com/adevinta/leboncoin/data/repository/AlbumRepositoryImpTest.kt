package com.adevinta.leboncoin.data.repository

import androidx.paging.PagingData
import com.adevinta.FakeDataGenerator
import com.adevinta.leboncoin.data.source.local.datasource.LocalAlbumDataSource
import com.adevinta.leboncoin.data.source.remote.datasource.RemoteAlbumDataSource
import com.adevinta.leboncoin.domain.model.Album
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`


class AlbumRepositoryImpTest {

    private lateinit var albumRepository: AlbumRepositoryImp
    private lateinit var localDataSource: LocalAlbumDataSource
    private lateinit var remoteDataSource: RemoteAlbumDataSource

    @Before
    fun setup() {
        localDataSource = mock(LocalAlbumDataSource::class.java)
        remoteDataSource = mock(RemoteAlbumDataSource::class.java)
        albumRepository = AlbumRepositoryImp(localDataSource, remoteDataSource)
    }

    @Test
    fun `getRemoteAlbum success`() = runBlocking {

        `when`(remoteDataSource.fetchAlbums()).thenReturn(emptyList())
        `when`(localDataSource.getAlbums()).thenReturn(emptyList())

        val result = albumRepository.getRemoteAlbum().toList()

        assertEquals(Result.success(true), result.first())
    }

    @Test
    fun `getRemoteAlbum with local data`() = runBlocking {
        // Given
        val localData = listOf(FakeDataGenerator.generateFakeAlbum(1), FakeDataGenerator.generateFakeAlbum(2))
        `when`(localDataSource.getAlbums()).thenReturn(localData)

        // When
        val result = albumRepository.getRemoteAlbum().toList()

        // Then
        assertEquals(Result.success(true), result.first())
    }

    @Test
    fun `getRemoteAlbum error with no local data`() = runBlocking {
        // Given
        `when`(localDataSource.getAlbums()).thenReturn(emptyList())

        `when`(remoteDataSource.fetchAlbums()).thenThrow(RuntimeException())

        // When
        val result = albumRepository.getRemoteAlbum().toList()

        // Then
        assertEquals(Result.failure<Boolean>(RuntimeException()), result.first())
    }

    @Test
    fun `getPagingAlbum success`() = runBlocking {
        // Given
        val pagingData = mock<PagingData<Album>>()
        `when`(localDataSource.getAlbumFromLocal()).thenReturn(flowOf(pagingData))

        // When
        val result = albumRepository.getPagingAlbum()

        // Then
        assertEquals(pagingData, result)
    }

    @Test
    fun `getAlbum success`() = runBlocking {
        // Given
        val albumId = 1
        val album = mock(Album::class.java)
        `when`(localDataSource.getAlbum(albumId)).thenReturn(flowOf(album))

        // When
        val result = albumRepository.getAlbum(albumId)

        // Then
        assertEquals(album, result.first())
    }
}