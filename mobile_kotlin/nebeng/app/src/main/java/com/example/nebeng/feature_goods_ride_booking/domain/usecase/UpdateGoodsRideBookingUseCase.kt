package com.example.nebeng.feature_goods_ride_booking.domain.usecase

import com.example.nebeng.core.common.Result
import com.example.nebeng.feature_goods_ride_booking.data.remote.model.request.UpdateGoodsRideBookingRequest
import com.example.nebeng.feature_goods_ride_booking.data.repository.GoodsRideBookingRepository
import com.example.nebeng.feature_goods_ride_booking.domain.model.GoodsRideBooking
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpdateGoodsRideBookingUseCase @Inject constructor(
    private val repository: GoodsRideBookingRepository
) {
    suspend operator fun invoke(token: String, id: Int, request: UpdateGoodsRideBookingRequest): Flow<Result<GoodsRideBooking>> {
        return repository.updateGoodsRideBookingById(token, id, request)
    }
}