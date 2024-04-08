package com.adevinta.leboncoin.presentation.screen.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.adevinta.leboncoin.domain.model.Album
import com.adevinta.leboncoin.presentation.composable.DetailsTopAppBar
import com.adevinta.leboncoin.presentation.composable.ImageWithLoader
import com.adevinta.leboncoin.presentation.composable.Loading


@Composable
fun AlbumDetailsScreen(
    onBack: () -> Unit, modifier: Modifier = Modifier,
    viewModel: AlbumDetailsViewModel = hiltViewModel(),
    scaffoldState: ScaffoldState = rememberScaffoldState(),
) {
    val uiState by viewModel.detailsAlbumUiState.collectAsStateWithLifecycle()

    Scaffold(modifier = modifier.fillMaxSize(), scaffoldState = scaffoldState, topBar = { DetailsTopAppBar(onBack) }) { _ ->
        when (val albumDetailsUiState = uiState) {
            is AlbumDetailsUiState.Loading -> {
                Loading(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center)
            }

            is AlbumDetailsUiState.Error -> {
                Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = "Error loading data", fontSize = 16.sp)
                }
            }

            is AlbumDetailsUiState.Success -> {
                AlbumDetails(album = albumDetailsUiState.album)
            }
        }
    }
}

@Composable
fun AlbumDetails(album: Album) {
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        ImageWithLoader(modifier = Modifier.size(200.dp, 200.dp), album.url)
        Spacer(modifier = Modifier.size(8.dp))
        Text(text = album.title, fontSize = 14.sp, color = Color.Black)
    }
}