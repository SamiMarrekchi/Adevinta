package com.adevinta.leboncoin.di

import android.content.Context
import androidx.room.Room
import com.adevinta.leboncoin.data.source.local.AlbumDatabase
import com.adevinta.leboncoin.data.source.local.dao.AlbumDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {
    @Provides
    @Singleton
    fun providesDatabase(
        @ApplicationContext context: Context,
    ): AlbumDatabase = Room.databaseBuilder(
        context,
        AlbumDatabase::class.java,
        "album-database",
    ).build()

    @Provides
    fun providesAlbumDao(
        database: AlbumDatabase,
    ): AlbumDao = database.albumDao()
}
