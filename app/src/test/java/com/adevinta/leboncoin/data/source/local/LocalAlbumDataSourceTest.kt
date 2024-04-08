package com.adevinta.leboncoin.data.source.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.adevinta.FakeDataGenerator
import com.adevinta.leboncoin.data.source.local.dao.AlbumDao
import com.adevinta.leboncoin.data.source.local.datasource.LocalAlbumDataSource
import com.adevinta.leboncoin.data.source.local.entity.asEntity
import com.adevinta.leboncoin.data.source.local.entity.asModel
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class LocalAlbumDataSourceTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var albumDao: AlbumDao
    private lateinit var localDataSource: LocalAlbumDataSource

    @Before
    fun setup() {
        albumDao = mock(AlbumDao::class.java)
        localDataSource = LocalAlbumDataSource(albumDao)
    }


    @Test
    fun `getAlbums returns list of Albums`() = runBlocking {
        // Given
        val albums =
            listOf(FakeDataGenerator.generateFakeAlbumEntity(1), FakeDataGenerator.generateFakeAlbumEntity(2))
        `when`(albumDao.getAll()).thenReturn(albums)

        // When
        val result = localDataSource.getAlbums()

        // Then
        assertEquals(albums.map { it.asModel() }, result)
    }

    @Test
    fun `insert invokes upsertAll in DAO`() = runBlocking {
        // Given
        val albums =
            listOf(FakeDataGenerator.generateFakeAlbum(1), FakeDataGenerator.generateFakeAlbum(2))


        // When
        localDataSource.insert(albums)
        val albumEntities = albums.map { it.asEntity() }
        `when`(albumDao.getAll()).thenReturn(albumEntities)
        // Then

        albums.forEachIndexed { index, album ->
            assertEquals(albumEntities[index], album)
        }
    }

    @Test
    fun `getAlbum returns Album by ID`() = runBlocking {
        // Given
        val albumId = 1
        val albumEntity = FakeDataGenerator.generateFakeAlbumEntity(albumId);
        `when`(albumDao.observeById(albumId)).thenReturn(flowOf(albumEntity))

        // When
        val result = localDataSource.getAlbum(albumId).first()

        // Then
        assertEquals(albumEntity.asModel(), result)
    }

}