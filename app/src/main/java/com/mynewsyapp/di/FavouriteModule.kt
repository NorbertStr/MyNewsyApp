package com.mynewsyapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.mynewsyapp.features_components.core.data.local.NewsyArticleDatabase
import com.mynewsyapp.features_components.core.domain.mapper.Mapper
import com.mynewsyapp.features_components.favourite.data.dao.FavouriteDao
import com.mynewsyapp.features_components.favourite.data.mapper.FavouriteMapper
import com.mynewsyapp.features_components.favourite.data.models.FavouriteDto
import com.mynewsyapp.features_components.favourite.data.repository.FavouriteRepoImpl
import com.mynewsyapp.features_components.favourite.domain.model.FavouriteArticle
import com.mynewsyapp.features_components.favourite.domain.repository.FavouriteRepository
import com.mynewsyapp.features_components.favourite.domain.use_cases.FavouriteUseCases
import com.mynewsyapp.features_components.favourite.domain.use_cases.GetAllFavouriteUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FavouriteModule {

    @Provides
    @Singleton
    fun provideFavouriteDao(database: NewsyArticleDatabase) =
        database.favouriteDao()

    @Provides
    @Singleton
    fun provideFavouriteMapper(): Mapper<FavouriteDto, FavouriteArticle> =
        FavouriteMapper()

    @Provides
    @Singleton
    fun provideRepository(
        dao: FavouriteDao,
        favouriteMapper: Mapper<FavouriteDto, FavouriteArticle>,
    ): FavouriteRepository = FavouriteRepoImpl(
        dao, favouriteMapper
    )

    @Provides
    @Singleton
    fun provideFavouriteUseCases(
        repository: FavouriteRepository,
    ): FavouriteUseCases =
        FavouriteUseCases(
            getAllFavouriteUseCase = GetAllFavouriteUseCase(repository)
        )

}