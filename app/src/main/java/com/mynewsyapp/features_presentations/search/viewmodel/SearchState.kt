package com.mynewsyapp.features_presentations.search.viewmodel

import androidx.paging.PagingData
import com.mynewsyapp.features_components.search.domain.models.SearchArticle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class SearchState(
    val searchArticles: Flow<PagingData<SearchArticle>> = emptyFlow(),
    val query: String = "",
    val searchHistory: List<String> = listOf(),
)
