package com.example.nebeng.feature_a_history_order.di

import com.example.nebeng.feature_a_history_order.domain.usecase.GetHistoryOrdersUseCase
import com.example.nebeng.feature_passenger_ride_booking.data.repository.PassengerRideBookingRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HistoryOrderModule {
    @Provides
    @Singleton
    fun provideGetHistoryOrdersUseCase(
        bookingRepository: PassengerRideBookingRepository
    ): GetHistoryOrdersUseCase {
        return GetHistoryOrdersUseCase(bookingRepository)
    }
}