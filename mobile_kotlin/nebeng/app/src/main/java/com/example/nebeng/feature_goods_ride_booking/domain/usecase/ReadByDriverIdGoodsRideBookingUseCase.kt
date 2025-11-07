package com.example.nebeng.feature_goods_ride_booking.domain.usecase

import com.example.nebeng.core.common.Result
import com.example.nebeng.feature_goods_ride_booking.data.repository.GoodsRideBookingRepository
import com.example.nebeng.feature_goods_ride_booking.domain.model.GoodsRideBookingFull
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReadByDriverIdGoodsRideBookingUseCase @Inject constructor(
    private val repository: GoodsRideBookingRepository
) {
    suspend operator fun invoke(token: String, driverId: Int): Flow<Result<List<GoodsRideBookingFull>>> {
        return repository.getGoodsRideBookingByDriverId(token, driverId)
    }
}