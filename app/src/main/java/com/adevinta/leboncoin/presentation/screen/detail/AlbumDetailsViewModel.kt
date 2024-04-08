package com.adevinta.leboncoin.presentation.screen.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adevinta.leboncoin.domain.model.Album
import com.adevinta.leboncoin.domain.usecase.GetAlbumByIdUseCase
import com.adevinta.leboncoin.presentation.navigation.AlbumDestinationsArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumDetailsViewModel @Inject constructor(
    private val getAlbumByIdUseCase: GetAlbumByIdUseCase, savedStateHandle: SavedStateHandle) :
    ViewModel() {

    private val _detailsAlbumUiState =
        MutableStateFlow<AlbumDetailsUiState>(AlbumDetailsUiState.Loading)
    val detailsAlbumUiState get() = _detailsAlbumUiState.asStateFlow()
    var albumId: Int = savedStateHandle[AlbumDestinationsArgs.ALBUM_ID_ARG] ?: -1

    init {
        getAlbumDetails()
    }

    fun getAlbumDetails() {
        viewModelScope.launch {
            getAlbumByIdUseCase(albumId).collect { album ->
                if (album == null) {
                    _detailsAlbumUiState.update { AlbumDetailsUiState.Error("errr") }
                } else {
                    _detailsAlbumUiState.update { AlbumDetailsUiState.Success(album) }
                }
            }

        }
    }
}

sealed class AlbumDetailsUiState {
    object Loading : AlbumDetailsUiState()
    data class Error(val message: String) : AlbumDetailsUiState()
    data class Success(val album: Album) : AlbumDetailsUiState()
}
