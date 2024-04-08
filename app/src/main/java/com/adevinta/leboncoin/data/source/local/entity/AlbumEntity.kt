package com.adevinta.leboncoin.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.adevinta.leboncoin.domain.model.Album

@Entity(tableName = "album")
data class AlbumEntity(@PrimaryKey val id: Int, val albumId: Int, val title: String,
                       val url: String, val thumbnailUrl: String)

fun Album.asEntity() =
    AlbumEntity(id = id, albumId = albumId, title = title, url = url, thumbnailUrl = thumbnailUrl)

fun AlbumEntity.asModel() =
    Album(id = id, albumId = albumId, title = title, url = url, thumbnailUrl = thumbnailUrl)