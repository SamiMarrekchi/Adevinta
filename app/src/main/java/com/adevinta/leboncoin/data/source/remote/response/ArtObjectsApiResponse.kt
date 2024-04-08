package com.adevinta.leboncoin.data.source.remote.response

import com.adevinta.leboncoin.domain.model.Album
import kotlinx.serialization.Serializable


@Serializable
data class AlbumResponse(val id:Int,val albumId:Int , val title:String ,val url:String,val thumbnailUrl:String)


fun AlbumResponse.asModel() =
    Album(id = id, albumId = albumId, title = title, url = url, thumbnailUrl = thumbnailUrl)