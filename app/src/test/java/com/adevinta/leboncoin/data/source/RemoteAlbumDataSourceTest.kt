package com.adevinta.leboncoin.data.source

import com.adevinta.FakeDataGenerator
import com.adevinta.leboncoin.data.source.remote.datasource.RemoteAlbumDataSource
import com.adevinta.leboncoin.data.source.remote.response.asModel
import com.adevinta.leboncoin.data.source.remote.retrofit.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock

class RemoteAlbumDataSourceTest {

    // Mock ApiService
    private val apiService: ApiService = mock(ApiService::class.java)
    private val remoteAlbumDataSource = RemoteAlbumDataSource(apiService, Dispatchers.Unconfined)

    @Test
    fun `fetchAlbums returns list of albums`() = runBlocking {
        // Given
        val albumsFromApi =
            listOf(FakeDataGenerator.generateFakeAlbumResponse(1), FakeDataGenerator.generateFakeAlbumResponse(2))
        val expectedAlbums = albumsFromApi.map { it.asModel() }

        // Mock ApiService response
        Mockito.`when`(apiService.getAlbums()).thenReturn(albumsFromApi)

        // When
        val result = remoteAlbumDataSource.fetchAlbums()

        // Then
        Assert.assertEquals(expectedAlbums, result)
    }
}