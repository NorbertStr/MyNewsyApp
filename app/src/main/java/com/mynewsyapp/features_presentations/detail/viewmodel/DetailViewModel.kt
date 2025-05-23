package com.mynewsyapp.features_presentations.detail.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import com.mynewsyapp.features_components.detail.domain.use_cases.DetailUseCases
import com.mynewsyapp.features_presentations.core.navigation.UiScreen
import com.mynewsyapp.utils.K
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val detailUseCases: DetailUseCases,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val unknownRoute = "unknown"
    val id: Int = savedStateHandle.get<Int>(K.articleId) ?: -1
    private val route: String = savedStateHandle.get<String>(K.screenType) ?: unknownRoute

    var detailState by mutableStateOf(DetailState())
        private set

    init {
        load()
    }

    private fun load() {
        viewModelScope.launch {
            when (route) {
                UiScreen.DiscoverScreen().route -> {
                    detailState = detailState.copy(
                        selectedDetailArticle = detailUseCases.getDetailDiscoverArticleUseCase(
                            id
                        ),
                        error = false
                    )
                }

                UiScreen.HeadlineScreen().route -> {
                    detailState = detailState.copy(
                        selectedDetailArticle = detailUseCases.getDetailHeadlineArticleUseCase(
                            id
                        ),
                        error = false
                    )
                }

                UiScreen.SearchScreen().route -> {
                    detailState = detailState.copy(
                        selectedDetailArticle = detailUseCases.getDetailSearchArticleUseCase(
                            id
                        ),
                        error = false
                    )
                }

                unknownRoute -> {
                    detailState = detailState.copy(
                        error = true
                    )
                }
            }


        }
    }


}