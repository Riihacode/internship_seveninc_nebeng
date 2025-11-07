package com.example.nebeng.feature_goods_ride_booking.domain.usecase

import com.example.nebeng.feature_goods_ride_booking.data.repository.GoodsRideBookingRepository
import javax.inject.Inject
import com.example.nebeng.core.common.Result
import com.example.nebeng.feature_goods_ride_booking.data.remote.model.request.CreateGoodsRideBookingRequest
import com.example.nebeng.feature_goods_ride_booking.domain.model.GoodsRideBooking
import kotlinx.coroutines.flow.Flow

class CreateGoodsRideBookingUseCase @Inject constructor(
    private val repository: GoodsRideBookingRepository
) {
    suspend operator fun invoke(token: String, request: CreateGoodsRideBookingRequest): Flow<Result<GoodsRideBooking>> {
        return repository.createGoodsRideBooking(token, request)
    }
}