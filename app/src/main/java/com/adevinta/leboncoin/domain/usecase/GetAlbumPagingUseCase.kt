package com.adevinta.leboncoin.domain.usecase

import androidx.paging.PagingData
import com.adevinta.leboncoin.domain.model.Album
import com.adevinta.leboncoin.domain.repository.AlbumRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAlbumPagingUseCase @Inject constructor(private val albumRepository: AlbumRepository) {
    operator fun invoke(): Flow<PagingData<Album>> {
        return albumRepository.getPagingAlbum()
    }
}