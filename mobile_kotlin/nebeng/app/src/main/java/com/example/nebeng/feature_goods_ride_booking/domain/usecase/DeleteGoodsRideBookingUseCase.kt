package com.example.nebeng.feature_goods_ride_booking.domain.usecase

import com.example.nebeng.feature_goods_ride_booking.data.repository.GoodsRideBookingRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import com.example.nebeng.core.common.Result

class DeleteGoodsRideBookingUseCase @Inject constructor(
    private val repository: GoodsRideBookingRepository
) {
    suspend operator fun invoke(token: String, id: Int): Flow<Result<String>> {
        return repository.deleteGoodsRideBookingById(token, id)
    }
}