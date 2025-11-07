package com.example.nebeng.feature_goods_ride.domain.usecase

import com.example.nebeng.feature_goods_ride.data.repository.GoodsRideRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import com.example.nebeng.core.common.Result
import com.example.nebeng.feature_goods_ride.data.remote.model.request.UpdateGoodsRideRequest
import com.example.nebeng.feature_goods_ride.domain.model.GoodsRide

class UpdateGoodsRideUseCase @Inject constructor(
    private val repository: GoodsRideRepository
) {
    suspend operator fun invoke(token: String, id: Int, request: UpdateGoodsRideRequest ): Flow<Result<GoodsRide>> {
        return repository.updateGoodsRideById(token, id, request)
    }
}