package com.adevinta.leboncoin.domain.usescase

import androidx.paging.PagingData
import com.adevinta.FakeDataGenerator
import com.adevinta.leboncoin.domain.model.Album
import com.adevinta.leboncoin.domain.repository.AlbumRepository
import com.adevinta.leboncoin.domain.usecase.GetAlbumByIdUseCase
import com.adevinta.leboncoin.domain.usecase.GetAlbumPagingUseCase
import com.adevinta.leboncoin.domain.usecase.GetRemoteAlbumUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock

class UseCaseTest {

    private lateinit var albumRepository: AlbumRepository
    private lateinit var getRemoteAlbumUseCase: GetRemoteAlbumUseCase
    private lateinit var getAlbumPagingUseCase: GetAlbumPagingUseCase
    private lateinit var getAlbumByIdUseCase: GetAlbumByIdUseCase

    @Before
    fun setup() {
        albumRepository = mock(AlbumRepository::class.java)
        getRemoteAlbumUseCase = GetRemoteAlbumUseCase(albumRepository)
        getAlbumPagingUseCase = GetAlbumPagingUseCase(albumRepository)
        getAlbumByIdUseCase = GetAlbumByIdUseCase(albumRepository)
    }

    @Test
    fun `getRemoteAlbumUseCase calls repository`() = runBlocking {
        // Given
        val expectedResult = flowOf(Result.success(true))
        Mockito.`when`(albumRepository.getRemoteAlbum()).thenReturn(expectedResult)

        // When
        val result = getRemoteAlbumUseCase().toList()

        // Then
        Assert.assertEquals(expectedResult.toList(), result)
    }

    @Test
    fun `getAlbumPaginatorUseCase calls repository`() = runBlocking {
        // Given
        val expectedPagingData: Flow<PagingData<Album>> = flowOf(PagingData.empty())
        Mockito.`when`(albumRepository.getPagingAlbum()).thenReturn(expectedPagingData)

        // When
        val result = getAlbumPagingUseCase().toList()

        // Then
        Assert.assertEquals(expectedPagingData.toList(), result)
    }

    @Test
    fun `getAlbumByIdUseCase calls repository`() = runBlocking {
        // Given
        val albumId = 1
        val expectedAlbum: Album = FakeDataGenerator.generateFakeAlbum(1)
        Mockito.`when`(albumRepository.getAlbum(albumId)).thenReturn(flowOf(expectedAlbum))

        // When
        val result = getAlbumByIdUseCase(albumId).first()

        // Then
        assertEquals(expectedAlbum, result)
    }
}