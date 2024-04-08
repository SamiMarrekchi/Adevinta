package com.adevinta.leboncoin.domain.usecase;

import com.adevinta.leboncoin.domain.repository.AlbumRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetRemoteAlbumUseCase @Inject constructor(private val albumRepository: AlbumRepository) {
    operator fun invoke(): Flow<Result<Boolean>> = albumRepository.getRemoteAlbum()
}
