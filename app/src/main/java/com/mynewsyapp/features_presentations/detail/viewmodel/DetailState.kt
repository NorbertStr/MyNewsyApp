package com.mynewsyapp.features_presentations.detail.viewmodel

import com.mynewsyapp.features_components.detail.domain.models.DetailArticle
import com.mynewsyapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class DetailState(
    val selectedDetailArticle: Flow<Resource<DetailArticle>> = emptyFlow(),
    val error: Boolean = false,
)
