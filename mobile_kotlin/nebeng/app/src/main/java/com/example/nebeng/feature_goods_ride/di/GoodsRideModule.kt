package com.example.nebeng.feature_goods_ride.di

import com.example.nebeng.feature_goods_ride.data.remote.api.GoodsRideApi
import com.example.nebeng.feature_goods_ride.data.repository.GoodsRideRepository
import com.example.nebeng.feature_goods_ride.data.repository.GoodsRideRepositoryImpl
import com.example.nebeng.feature_goods_ride.domain.usecase.CreateGoodsRideUseCase
import com.example.nebeng.feature_goods_ride.domain.usecase.DeleteGoodsRideUseCase
import com.example.nebeng.feature_goods_ride.domain.usecase.GoodsRideUseCases
import com.example.nebeng.feature_goods_ride.domain.usecase.ReadAllGoodsRideUseCase
import com.example.nebeng.feature_goods_ride.domain.usecase.ReadByIdGoodsRideUseCase
import com.example.nebeng.feature_goods_ride.domain.usecase.UpdateGoodsRideUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object GoodsRideModule {
    @Provides
    @Singleton
    fun provideGoodsRideApi(
        retrofit: Retrofit
    ): GoodsRideApi = retrofit.create(GoodsRideApi::class.java)

    @Provides
    @Singleton
    fun provideGoodsRideRepository(
        api: GoodsRideApi
    ): GoodsRideRepository = GoodsRideRepositoryImpl(api)

    @Provides
    @Singleton
    fun provideGoodsRideUseCases(
        repository: GoodsRideRepository
    ): GoodsRideUseCases {
        return GoodsRideUseCases(
            create = CreateGoodsRideUseCase(repository),
            readAll = ReadAllGoodsRideUseCase(repository),
            readById = ReadByIdGoodsRideUseCase(repository),
            update = UpdateGoodsRideUseCase(repository),
            delete = DeleteGoodsRideUseCase(repository),
        )
    }
}