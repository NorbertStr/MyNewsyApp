package com.mynewsyapp.features_presentations.favourite

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.mynewsyapp.features_components.favourite.domain.model.FavouriteArticle
import com.mynewsyapp.features_presentations.core.components.NewsyArticleItem
import com.mynewsyapp.features_presentations.core.components.PaginationLoadingItem
import com.mynewsyapp.features_presentations.favourite.components.FavTopAppBar
import com.mynewsyapp.features_presentations.favourite.viewmodel.FavouriteViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavouriteScreen(
    favouriteViewModel: FavouriteViewModel = hiltViewModel(),
    onItemClick: (FavouriteArticle) -> Unit,
    onFavouriteChange: (FavouriteArticle) -> Unit,
    openDrawer: () -> Unit,
) {
    val state = favouriteViewModel.favouriteState
    val favouriteArticle = state.favouriteArticles.collectAsLazyPagingItems()
    Scaffold(
        topBar = {
            FavTopAppBar(
                openDrawer = openDrawer
            )
        }
    ) { innerPadding ->
        LazyColumn(contentPadding = innerPadding) {
            items(count = favouriteArticle.itemCount) { value ->
                favouriteArticle[value]?.let {
                    NewsyArticleItem(
                        article = it,
                        onClick = { clickedItem ->
                            onItemClick(clickedItem as FavouriteArticle)
                        },
                        onFavouriteChange = { article ->
                            onFavouriteChange(article as FavouriteArticle)
                        }
                    )
                }
            }
            item {
                val context = LocalContext.current
                PaginationLoadingItem(
                    pagingState = favouriteArticle.loadState.mediator?.append,
                    onError = { e ->
                        Toast.makeText(
                            context, e.message, Toast.LENGTH_SHORT
                        ).show()
                    },
                    onLoading = {
                        CircularProgressIndicator(
                            modifier = Modifier.fillMaxWidth()
                                .wrapContentWidth(
                                    align = Alignment.CenterHorizontally
                                )
                        )
                    }
                )
            }
        }
    }

}