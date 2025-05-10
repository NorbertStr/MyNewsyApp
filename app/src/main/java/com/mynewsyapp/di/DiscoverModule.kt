package com.mynewsyapp.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.mynewsyapp.features_components.core.data.local.NewsyArticleDatabase
import com.mynewsyapp.features_components.core.domain.mapper.Mapper
import com.mynewsyapp.features_components.core.domain.models.NewsyArticle
import com.mynewsyapp.features_components.discover.data.local.dao.DiscoverArticleDao
import com.mynewsyapp.features_components.discover.data.local.dao.DiscoverRemoteKeyDao
import com.mynewsyapp.features_components.discover.data.local.models.DiscoverArticleDto
import com.mynewsyapp.features_components.discover.data.mapper.DiscoverMapper
import com.mynewsyapp.features_components.discover.data.remote.DiscoverApi
import com.mynewsyapp.features_components.discover.data.repository.DiscoverRepoImpl
import com.mynewsyapp.features_components.discover.domain.repository.DiscoverRepository
import com.mynewsyapp.features_components.discover.domain.use_cases.DiscoverUseCases
import com.mynewsyapp.features_components.discover.domain.use_cases.FetchDiscoverArticleUseCase
import com.mynewsyapp.features_components.discover.domain.use_cases.GetDiscoverCurrentCategoryUseCase
import com.mynewsyapp.features_components.discover.domain.use_cases.UpdateCurrentCategoryUseCase
import com.mynewsyapp.features_components.discover.domain.use_cases.UpdateFavouriteDiscoverArticleUseCase
import com.mynewsyapp.utils.K
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DiscoverModule {
    private val json = Json {
        coerceInputValues = true
        ignoreUnknownKeys = true
    }

    @Provides
    @Singleton
    fun provideDiscoverApi(): DiscoverApi {
        val contentType = "application/json".toMediaType()
        return Retrofit.Builder()
            .baseUrl(K.BASE_URL)
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
            .create(DiscoverApi::class.java)
    }

    @Provides
    @Singleton
    fun provideDiscoverRepository(
        api: DiscoverApi,
        database: NewsyArticleDatabase,
        mapper: Mapper<DiscoverArticleDto, NewsyArticle>,
    ): DiscoverRepository {
        return DiscoverRepoImpl(
            discoverApi = api,
            database = database,
            mapper = mapper
        )
    }

    @Provides
    @Singleton
    fun provideDiscoverArticleDao(
        database: NewsyArticleDatabase,
    ): DiscoverArticleDao = database.discoverArticleDao()

    @Provides
    @Singleton
    fun provideRemoteKeyDao(database: NewsyArticleDatabase): DiscoverRemoteKeyDao =
        database.discoverRemoteKeyDao()

    @Provides
    @Singleton
    fun provideDiscoverMapper(): Mapper<DiscoverArticleDto, NewsyArticle> =
        DiscoverMapper()

    @Provides
    @Singleton
    fun provideDiscoverUseCases(repository: DiscoverRepository): DiscoverUseCases {
        return DiscoverUseCases(
            fetchDiscoverArticleUseCase = FetchDiscoverArticleUseCase(repository),
            updateCurrentCategoryUseCase = UpdateCurrentCategoryUseCase(repository),
            getDiscoverCurrentCategoryUseCase = GetDiscoverCurrentCategoryUseCase(repository),
            updateFavouriteDiscoverArticleUseCase = UpdateFavouriteDiscoverArticleUseCase(repository)
        )
    }

}












