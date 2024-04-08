package com.adevinta.leboncoin.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagingData
import com.adevinta.FakeDataGenerator
import com.adevinta.leboncoin.domain.usecase.GetAlbumPagingUseCase
import com.adevinta.leboncoin.domain.usecase.GetRemoteAlbumUseCase
import com.adevinta.leboncoin.presentation.screen.albums.ListAlbumUiState
import com.adevinta.leboncoin.presentation.screen.albums.ListAlbumViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`


@OptIn(ExperimentalCoroutinesApi::class)
class ListAlbumViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()


    private lateinit var getRemoteAlbumUseCase: GetRemoteAlbumUseCase


    private lateinit var getAlbumPagingUseCase: GetAlbumPagingUseCase

    private lateinit var viewModel: ListAlbumViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
        getRemoteAlbumUseCase = mock()
        getAlbumPagingUseCase = mock()
        viewModel = ListAlbumViewModel(getRemoteAlbumUseCase, getAlbumPagingUseCase)
    }

    @Test
    fun `fetchRemoteData success`() = runBlocking {
        // Given
        `when`(getRemoteAlbumUseCase()).thenReturn(flowOf(Result.success(true)))

        // When
        viewModel.fetchRemoteData()

        // Then
        assertEquals(ListAlbumUiState.Success, viewModel.listAlbumUiState.value)
    }

    @Test
    fun `fetchRemoteData error`() = runBlocking {
        // Given
        val exception = Exception("error")
        val expected = Result.failure<Boolean>(exception = exception)
        `when`(getRemoteAlbumUseCase()).thenReturn(flowOf(expected))

        // When
        viewModel.fetchRemoteData()

        // Then
        assertEquals(ListAlbumUiState.Error("error").message, viewModel.listAlbumUiState.value)
    }

    @Test
    fun `getPagingAlbum success`() = runBlocking {
        // Given
        val result = listOf(FakeDataGenerator.generateFakeAlbum(1),FakeDataGenerator.generateFakeAlbum(2))

        val pagingData = MutableStateFlow(PagingData.from(result))
        `when`(getAlbumPagingUseCase()).thenReturn(pagingData)

        // When
        viewModel.getPagingAlbum()

        // Then
        assertEquals(pagingData.value, viewModel.pagingData.value)
    }
}
