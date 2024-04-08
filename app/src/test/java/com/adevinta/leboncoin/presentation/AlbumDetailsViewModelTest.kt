package com.adevinta.leboncoin.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.adevinta.FakeDataGenerator
import com.adevinta.leboncoin.domain.usecase.GetAlbumByIdUseCase
import com.adevinta.leboncoin.presentation.screen.detail.AlbumDetailsUiState
import com.adevinta.leboncoin.presentation.screen.detail.AlbumDetailsViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock

@ExperimentalCoroutinesApi
class AlbumDetailsViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var viewModel: AlbumDetailsViewModel
    private lateinit var getAlbumByIdUseCase: GetAlbumByIdUseCase

    @Before
    fun setup() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
        getAlbumByIdUseCase = mock(GetAlbumByIdUseCase::class.java)
        viewModel = AlbumDetailsViewModel(getAlbumByIdUseCase, mock())

    }

    @Test
    fun `getAlbumDetails success updates UI state`() = runBlocking {
        // Given
        viewModel.albumId = 1
        val album = FakeDataGenerator.generateFakeAlbum(1)
        val flow = flowOf(album)
        Mockito.`when`(getAlbumByIdUseCase(1)).thenReturn(flow)

        // When
        viewModel.getAlbumDetails()

        // Then
        val uiState = viewModel.detailsAlbumUiState.value
        assertEquals(AlbumDetailsUiState.Success(album), uiState)
    }

    @Test
    fun `getAlbumDetails with null album updates UI state to error`() = runBlocking {
        // Given
        viewModel.albumId = 0
        Mockito.`when`(getAlbumByIdUseCase(0)).thenReturn(flowOf(null))

        // When
        viewModel.getAlbumDetails()

        // Then
        val uiState = viewModel.detailsAlbumUiState.value
        assertEquals(AlbumDetailsUiState.Error("errr"), uiState)
    }
}