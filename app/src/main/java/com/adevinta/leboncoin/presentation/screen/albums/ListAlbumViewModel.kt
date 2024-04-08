package com.adevinta.leboncoin.presentation.screen.albums

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.adevinta.leboncoin.domain.model.Album
import com.adevinta.leboncoin.domain.usecase.GetAlbumPagingUseCase
import com.adevinta.leboncoin.domain.usecase.GetRemoteAlbumUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListAlbumViewModel @Inject constructor(
    private val getRemoteAlbumUseCase: GetRemoteAlbumUseCase,
    private val getAlbumPagingUseCase: GetAlbumPagingUseCase) : ViewModel() {

    private val _listAlbumUiState = MutableStateFlow<ListAlbumUiState>(ListAlbumUiState.Loading)
    val listAlbumUiState get() = _listAlbumUiState.asStateFlow()

    private val _pagingData = MutableStateFlow<PagingData<Album>>(PagingData.empty())
    val pagingData: StateFlow<PagingData<Album>> = _pagingData


    init {
        fetchRemoteData()
    }

    fun fetchRemoteData() {
        viewModelScope.launch {
            getRemoteAlbumUseCase().stateIn(viewModelScope).collect {
                if (it.isSuccess) {
                    _listAlbumUiState.update { ListAlbumUiState.Success }
                } else {
                    _listAlbumUiState.update { ListAlbumUiState.Error("errro") }
                }
            }

        }
    }

    fun getPagingAlbum() {
        viewModelScope.launch {
            getAlbumPagingUseCase().stateIn(viewModelScope).collect {
                _pagingData.value = it
            }
        }
    }
}

sealed interface ListAlbumUiState {
    object Loading : ListAlbumUiState
    data class Error(val message: String) : ListAlbumUiState
    object Success : ListAlbumUiState
}
