package com.adevinta

import com.adevinta.leboncoin.data.source.local.entity.AlbumEntity
import com.adevinta.leboncoin.data.source.remote.response.AlbumResponse
import com.adevinta.leboncoin.domain.model.Album

object FakeDataGenerator {
    fun generateFakeAlbumEntity(id: Int): AlbumEntity {
        return AlbumEntity(id = id, albumId = id * 10, title = "Album $id", url = "https://example.com/album$id.jpg", thumbnailUrl = "https://example.com/thumbnail$id.jpg")
    }

    fun generateFakeAlbum(id: Int): Album {
        return Album(
            id = id,
            albumId = id * 10,
            title = "Album $id",
            url = "https://example.com/album$id.jpg",
            thumbnailUrl = "https://example.com/thumbnail$id.jpg",
        )
    }

    fun generateFakeAlbumResponse(id: Int): AlbumResponse {
        return AlbumResponse(
            id = id,
            albumId = id * 10,
            title = "Album $id",
            url = "https://example.com/album$id.jpg",
            thumbnailUrl = "https://example.com/thumbnail$id.jpg",
        )
    }
}