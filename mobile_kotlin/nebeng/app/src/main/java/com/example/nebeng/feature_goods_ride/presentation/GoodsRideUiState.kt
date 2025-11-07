package com.example.nebeng.feature_goods_ride.presentation

import com.example.nebeng.feature_goods_ride.domain.model.GoodsRide
import com.example.nebeng.core.common.Result

data class GoodsRideUiState(
    val goodsRides: Result<List<GoodsRide>> = Result.Loading,
    val selectedRide: Result<GoodsRide> = Result.Loading,
    val createResult: Result<GoodsRide> = Result.Loading,
    val updateResult: Result<GoodsRide> = Result.Loading,
    val deleteResult: Result<Boolean> = Result.Loading
)
