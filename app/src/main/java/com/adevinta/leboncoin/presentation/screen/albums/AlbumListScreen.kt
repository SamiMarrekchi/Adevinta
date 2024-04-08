package com.adevinta.leboncoin.presentation.screen.albums


import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.adevinta.leboncoin.domain.model.Album
import com.adevinta.leboncoin.presentation.composable.Loading


@Composable
fun AlbumListScreen(onAlbumClick: (Album) -> Unit, modifier: Modifier = Modifier,
                    viewModel: ListAlbumViewModel = hiltViewModel()) {

    val uiState by viewModel.listAlbumUiState.collectAsStateWithLifecycle()
    val pagingData = viewModel.pagingData.collectAsLazyPagingItems()

    when (uiState) {
        is ListAlbumUiState.Loading -> {
            Loading(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center)
        }

        is ListAlbumUiState.Error -> {
            Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "Error loading data", fontSize = 16.sp)
            }
        }

        is ListAlbumUiState.Success -> {
            viewModel.getPagingAlbum()
            AlbumList(onAlbumClick, pagingData)
        }
    }

}

@Composable
fun AlbumList(onAlbumClick: (Album) -> Unit, albums: LazyPagingItems<Album>) {
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
    val columns = if (isLandscape) 4 else 2
    LazyVerticalGrid(columns = GridCells.Fixed(columns), modifier = Modifier.fillMaxSize()) {
        items(albums.itemCount) { index ->
            albums[index]?.let { AlbumItem(album = it, onAlbumClick) }
        }
    }
}


