package com.example.nebeng.feature_goods_ride.domain.usecase

import com.example.nebeng.core.common.Result
import com.example.nebeng.feature_goods_ride.data.remote.model.request.CreateGoodsRideRequest
import com.example.nebeng.feature_goods_ride.data.repository.GoodsRideRepository
import com.example.nebeng.feature_goods_ride.domain.model.GoodsRide
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CreateGoodsRideUseCase @Inject constructor(
    private val repository: GoodsRideRepository
) {
    suspend operator fun invoke(token: String, request: CreateGoodsRideRequest): Flow<Result<GoodsRide>> {
        return repository.createGoodsRide(token, request)
    }
}