/*
 * Copyright 2019 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.adevinta.leboncoin.data.source.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.adevinta.leboncoin.data.source.local.entity.AlbumEntity
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object for the ArtEntity table.
 */
@Dao
interface AlbumDao {

    /**
     * Observes list of AlbumEntities grouped by album id.
     *
     * @return all albumEntity grouped By album id.
     */
    @Query("SELECT * FROM album")
    fun observeAll(): PagingSource<Int, AlbumEntity>

    @Query("SELECT * FROM album")
    suspend fun getAll(): List<AlbumEntity>


    /**
     * Observes a single album.
     *
     * @param albumId the album id.
     * @return the album with albumId.
     */

    @Query("SELECT * FROM album WHERE id = :albumId")
    fun observeById(albumId: Int): Flow<AlbumEntity?>


    /**
     * Insert or update a list of albumEntity in the database. If a artEntity already exists, replace it.
     *
     * @param albumEntities the list of albumEntity to be inserted or updated.
     */
    @Upsert
    suspend fun upsertAll(albumEntities: List<AlbumEntity>)


}
