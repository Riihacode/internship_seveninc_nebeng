package com.example.nebeng.feature_rating.di

import com.example.nebeng.feature_rating.data.remote.api.RatingApi
import com.example.nebeng.feature_rating.data.repository.RatingRepository
import com.example.nebeng.feature_rating.data.repository.RatingRepositoryImpl
import com.example.nebeng.feature_rating.domain.usecase.CreateRatingUseCase
import com.example.nebeng.feature_rating.domain.usecase.DeleteRatingUseCase
import com.example.nebeng.feature_rating.domain.usecase.RatingUseCases
import com.example.nebeng.feature_rating.domain.usecase.ReadAllRatingUseCase
import com.example.nebeng.feature_rating.domain.usecase.ReadByDriverIdRatingUseCase
import com.example.nebeng.feature_rating.domain.usecase.ReadByIdRatingUseCase
import com.example.nebeng.feature_rating.domain.usecase.UpdateRatingUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RatingModule {
    @Provides
    @Singleton
    fun provideRatingApi(
        retrofit: Retrofit
    ): RatingApi = retrofit.create(RatingApi::class.java)

    @Provides
    @Singleton
    fun provideRatingRepository(
        api: RatingApi
    ): RatingRepository = RatingRepositoryImpl(api)

    @Provides
    @Singleton
    fun provideRatingUseCase(
        repository: RatingRepository
    ): RatingUseCases {
        return RatingUseCases(
             create = CreateRatingUseCase(repository),
             readAll = ReadAllRatingUseCase(repository),
             readById = ReadByIdRatingUseCase(repository),
             readByDriverId = ReadByDriverIdRatingUseCase(repository),
             update =  UpdateRatingUseCase(repository),
             delete = DeleteRatingUseCase(repository)
        )
    }
}