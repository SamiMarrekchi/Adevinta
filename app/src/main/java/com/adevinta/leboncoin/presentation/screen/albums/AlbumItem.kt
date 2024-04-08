package com.adevinta.leboncoin.presentation.screen.albums

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.adevinta.leboncoin.domain.model.Album
import com.adevinta.leboncoin.presentation.composable.ImageWithLoader

@Composable
fun AlbumItem(
    album: Album, onAlbumClick: (Album) -> Unit,

    ) {

    Card(shape = RoundedCornerShape(8.dp), elevation = 4.dp, modifier = Modifier
        .padding(16.dp)
        .fillMaxWidth()
        .clickable {
            onAlbumClick(album)
        }) {

        Box(modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)) {
            ImageWithLoader(modifier = Modifier.fillMaxSize(), url = album.url)
            Column(modifier = Modifier
                .padding(16.dp)
                .align(AbsoluteAlignment.BottomLeft)) {
                Text(text = album.title, fontSize = 12.sp, color = Color.White)
            }
        }
    }
}


