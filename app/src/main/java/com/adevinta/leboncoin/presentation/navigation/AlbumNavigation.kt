/*
 * Copyright 2022 The Android Open Source Project
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

package com.adevinta.leboncoin.presentation.navigation

import androidx.navigation.NavHostController
import com.adevinta.leboncoin.presentation.navigation.AlbumDestinationsArgs.ALBUM_ID_ARG
import com.adevinta.leboncoin.presentation.navigation.AlbumScreens.ALBUMS_SCREEN
import com.adevinta.leboncoin.presentation.navigation.AlbumScreens.ALBUM_DETAIL_SCREEN

/**
 * Screens used in [AlbumScreens]
 */
private object AlbumScreens {
    const val ALBUMS_SCREEN = "albums"
    const val ALBUM_DETAIL_SCREEN = "album"
}

/**
 * Arguments used in [AlbumDestinations] routes
 */
object AlbumDestinationsArgs {
    const val ALBUM_ID_ARG = "albumId"
}

/**
 * Destinations used in the [MainActivity]
 */
object AlbumDestinations {
    const val ALBUM_ROUTE = ALBUMS_SCREEN
    const val ALBUM_DETAIL_ROUTE = "$ALBUM_DETAIL_SCREEN/{$ALBUM_ID_ARG}"
}

/**
 * Models the navigation actions in the app.
 */
class AlbumNavigationActions(private val navController: NavHostController) {
    fun navigateToAlbumDetail(albumId: Int) {
        navController.navigate("$ALBUM_DETAIL_SCREEN/$albumId")
    }
}
