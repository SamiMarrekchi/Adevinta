package com.adevinta.leboncoin.di

import com.adevinta.leboncoin.data.repository.AlbumRepositoryImp
import com.adevinta.leboncoin.domain.repository.AlbumRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface RepositoryModule {
    @Binds
    fun providesAlbumRepository(albumRepositoryImp: AlbumRepositoryImp): AlbumRepository


}
