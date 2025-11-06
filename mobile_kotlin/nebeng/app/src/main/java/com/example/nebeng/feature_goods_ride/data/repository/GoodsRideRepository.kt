package com.example.nebeng.feature_goods_ride.data.repository

import com.example.nebeng.core.common.Result
import com.example.nebeng.feature_goods_ride.data.remote.model.request.CreateGoodsRideRequest
import com.example.nebeng.feature_goods_ride.data.remote.model.request.UpdateGoodsRideRequest
import com.example.nebeng.feature_goods_ride.domain.model.GoodsRide
import kotlinx.coroutines.flow.Flow

interface GoodsRideRepository {

    suspend fun createGoodsRide(
        token: String,
        request: CreateGoodsRideRequest
    ): Flow<Result<GoodsRide>>

    suspend fun getAllGoodsRides(
        token: String
    ): Flow<Result<List<GoodsRide>>>

    suspend fun getGoodsRideById(
        token: String,
        id: Int
    ): Flow<Result<GoodsRide>>

    suspend fun updateGoodsRideById(
        token: String,
        id: Int,
        request: UpdateGoodsRideRequest
    ): Flow<Result<GoodsRide>>

    suspend fun deleteGoodsRideById(
        token: String,
        id: Int
    ): Flow<Result<Boolean>>
}