package com.adevinta.leboncoin.domain.usecase

import com.adevinta.leboncoin.domain.model.Album
import com.adevinta.leboncoin.domain.repository.AlbumRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAlbumByIdUseCase @Inject constructor(private val albumRepository: AlbumRepository) {
    operator fun invoke(albumId: Int): Flow<Album?> {
        return albumRepository.getAlbum(albumId)
    }
}