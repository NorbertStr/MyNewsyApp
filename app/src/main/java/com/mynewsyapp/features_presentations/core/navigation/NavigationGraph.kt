package com.mynewsyapp.features_presentations.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.mynewsyapp.features_presentations.detail.DetailScreen
import com.mynewsyapp.features_presentations.favourite.FavouriteScreen
import com.mynewsyapp.features_presentations.headline.HeadlineScreen
import com.mynewsyapp.features_presentations.home.HomeScreen
import com.mynewsyapp.features_presentations.search.SearchScreen
import com.mynewsyapp.features_presentations.setting.SettingScreen
import com.mynewsyapp.utils.K

@Composable
fun NewsyNavigationGraph(
    navController: NavHostController = rememberNavController(),
    navActions: NewsyNavigationActions,
    openDrawer: () -> Unit,
) {

    NavHost(
        navController = navController,
        startDestination = UiScreen.HomeScreen().route
    ) {
        composable(route = UiScreen.HomeScreen().route) {
            HomeScreen(
                onViewMoreClick = navActions.navigateToHeadlineScreen,
                onHeadlineItemClick = {
                    navActions.navigateToDetail(
                        it, UiScreen.HeadlineScreen().route
                    )
                },
                onDiscoverItemClick = {
                    navActions.navigateToDetail(
                        it, UiScreen.DiscoverScreen().route
                    )
                },
                onSearchClick = {
                    navActions.navigateToSearch()
                },
                openDrawer = openDrawer
            )
        }

        composable(route = UiScreen.HeadlineScreen().route) {
            HeadlineScreen(
                onItemClick = {
                    navActions.navigateToDetail(
                        it,
                        UiScreen.HeadlineScreen().route
                    )
                }
            )
        }
        composable(
            route = UiScreen.DetailScreen().routeWithArg,
            arguments = listOf(
                navArgument(name = K.articleId) {
                    type = NavType.IntType
                },
                navArgument(name = K.screenType) {
                    type = NavType.StringType
                }
            )
        ) {
            DetailScreen(onBack = {
                navController.navigateUp()
            })
        }

        composable(
            route = UiScreen.SearchScreen().route
        ) {
            SearchScreen(
                onSearchItemClick = {
                    navActions.navigateToDetail(
                        it.id,
                        UiScreen.SearchScreen().route
                    )
                },
                navigateUp = {
                    navController.navigateUp()
                }
            )
        }

        composable(route = UiScreen.FavouriteScreen().route) {
            FavouriteScreen(
                onItemClick = {
                    val screenType = when (it.category) {
                        K.HEADLINE_CATEGORY -> {
                            UiScreen.HeadlineScreen().route
                        }

                        K.SEARCH_CATEGORY -> {
                            UiScreen.HeadlineScreen().route
                        }

                        else -> UiScreen.DiscoverScreen().route
                    }
                    navActions.navigateToDetail(
                        it.id,
                        screenType
                    )
                },
                onFavouriteChange = {
                    // TODO:exercise Implement this
                },
                openDrawer = openDrawer
            )
        }

        composable(route = UiScreen.SettingsScreen().route) {
            SettingScreen {
                navController.navigateUp()
            }
        }


    }


}











