package com.example.nebeng.feature_goods_ride_booking.di

import com.example.nebeng.feature_goods_ride_booking.data.remote.api.GoodsRideBookingApi
import com.example.nebeng.feature_goods_ride_booking.data.repository.GoodsRideBookingRepository
import com.example.nebeng.feature_goods_ride_booking.data.repository.GoodsRideBookingRepositoryImpl
import com.example.nebeng.feature_goods_ride_booking.domain.usecase.CreateGoodsRideBookingUseCase
import com.example.nebeng.feature_goods_ride_booking.domain.usecase.DeleteGoodsRideBookingUseCase
import com.example.nebeng.feature_goods_ride_booking.domain.usecase.GoodsRideBookingUseCases
import com.example.nebeng.feature_goods_ride_booking.domain.usecase.ReadAllGoodsRideBookingUseCase
import com.example.nebeng.feature_goods_ride_booking.domain.usecase.ReadByDriverIdGoodsRideBookingUseCase
import com.example.nebeng.feature_goods_ride_booking.domain.usecase.ReadByIdGoodsRideBookingUseCase
import com.example.nebeng.feature_goods_ride_booking.domain.usecase.ReadByStatusGoodsRideBookingUseCase
import com.example.nebeng.feature_goods_ride_booking.domain.usecase.UpdateGoodsRideBookingUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object GoodsRideBookingModule {
    @Provides
    @Singleton
    fun provideGoodsRideBookingApi(
        retrofit: Retrofit
    ): GoodsRideBookingApi = retrofit.create(GoodsRideBookingApi::class.java)

    @Provides
    @Singleton
    fun provideGoodsRideBookingRepository(
        api: GoodsRideBookingApi
    ): GoodsRideBookingRepository = GoodsRideBookingRepositoryImpl(api)

    @Provides
    @Singleton
    fun provideGoodsRideBookingUseCases(
        repository: GoodsRideBookingRepository
    ): GoodsRideBookingUseCases{
        return GoodsRideBookingUseCases(
            create          = CreateGoodsRideBookingUseCase(repository),
            readAll         = ReadAllGoodsRideBookingUseCase(repository),
            readById        = ReadByIdGoodsRideBookingUseCase(repository),
            readByDriverId  = ReadByDriverIdGoodsRideBookingUseCase(repository),
            readByStatus    = ReadByStatusGoodsRideBookingUseCase(repository),
            update          = UpdateGoodsRideBookingUseCase(repository),
            delete          = DeleteGoodsRideBookingUseCase(repository)
        )
    }
}