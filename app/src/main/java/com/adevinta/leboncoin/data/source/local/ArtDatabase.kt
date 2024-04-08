package com.adevinta.leboncoin.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.adevinta.leboncoin.data.source.local.dao.AlbumDao
import com.adevinta.leboncoin.data.source.local.entity.AlbumEntity

@Database(entities = [AlbumEntity::class], version = 1, exportSchema = false)
abstract class AlbumDatabase : RoomDatabase() {
    abstract fun albumDao(): AlbumDao



}