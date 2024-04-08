package com.adevinta.leboncoin.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.adevinta.leboncoin.presentation.navigation.AlbumDestinationsArgs.ALBUM_ID_ARG
import com.adevinta.leboncoin.presentation.screen.detail.AlbumDetailsScreen
import com.adevinta.leboncoin.presentation.screen.albums.AlbumListScreen

@Composable
fun AlbumNavGraph(modifier: Modifier = Modifier,
                  navController: NavHostController = rememberNavController(),
                  startDestination: String = AlbumDestinations.ALBUM_ROUTE,
                  navActions: AlbumNavigationActions = remember(navController) {
                      AlbumNavigationActions(navController)
                  }) {

    NavHost(navController = navController, startDestination = startDestination, modifier = modifier) {
        composable(AlbumDestinations.ALBUM_ROUTE) {
            AlbumListScreen(
                onAlbumClick = { album -> navActions.navigateToAlbumDetail(album.id) },
            )
        }

        composable(AlbumDestinations.ALBUM_DETAIL_ROUTE, arguments = listOf(
            navArgument(ALBUM_ID_ARG) { type = NavType.IntType; nullable = false },
        )
        ) {
            AlbumDetailsScreen(onBack = { navController.popBackStack() })
        }
    }
}