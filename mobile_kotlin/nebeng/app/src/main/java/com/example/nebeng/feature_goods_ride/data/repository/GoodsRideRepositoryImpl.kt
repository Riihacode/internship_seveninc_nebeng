package com.example.nebeng.feature_goods_ride.data.repository

import android.os.Build
import com.example.nebeng.feature_goods_ride.data.remote.api.GoodsRideApi
import javax.inject.Inject
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.nebeng.core.common.Result
import com.example.nebeng.feature_goods_ride.data.remote.model.mapper.toDomain
import com.example.nebeng.feature_goods_ride.data.remote.model.request.CreateGoodsRideRequest
import com.example.nebeng.feature_goods_ride.data.remote.model.request.UpdateGoodsRideRequest
import com.example.nebeng.feature_goods_ride.domain.model.GoodsRide
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
class GoodsRideRepositoryImpl @Inject constructor(
    private val api: GoodsRideApi
): GoodsRideRepository {
    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun createGoodsRide(
        token: String,
        request: CreateGoodsRideRequest
    ): Flow<Result<GoodsRide>> = flow {
        emit(Result.Loading)
        try {
            val response = api.createGoodsRide("Bearer $token", request)
            val data = response.data.toDomain()
            emit(Result.Success(data))
            Log.d("GoodsRideRepo", "✅ create: id=${data.id}")
        } catch (e: Exception) {
            emit(Result.Error(e.message ?: "Unknown error on createGoodsRide"))
            Log.e("GoodsRideRepo", "❌ create error", e)
        }
    }.flowOn(Dispatchers.IO)


    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getAllGoodsRides(
        token: String
    ): Flow<Result<List<GoodsRide>>> = flow {
        emit(Result.Loading)
        try {
            val response = api.getAllGoodsRides("Bearer $token")
            val data = response.data.map { it.toDomain() }
            emit(Result.Success(data))
            Log.d("GoodsRideRepo", "✅ getAll: ${data.size} rides")
        } catch (e: Exception) {
            emit(Result.Error(e.message ?: "Unknown error on getAllGoodsRides"))
            Log.e("GoodsRideRepo", "❌ getAll error", e)
        }
    }.flowOn(Dispatchers.IO)


    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getGoodsRideById(
        token: String,
        id: Int
    ): Flow<Result<GoodsRide>> = flow {
        emit(Result.Loading)
        try {
            val response = api.getGoodsRideById("Bearer $token", id)
            val data = response.data.toDomain()
            emit(Result.Success(data))
            Log.d("GoodsRideRepo", "✅ getById: id=$id")
        } catch (e: Exception) {
            emit(Result.Error(e.message ?: "Unknown error on getGoodsRideById"))
            Log.e("GoodsRideRepo", "❌ getById error", e)
        }
    }.flowOn(Dispatchers.IO)


    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun updateGoodsRideById(
        token: String,
        id: Int,
        request: UpdateGoodsRideRequest
    ): Flow<Result<GoodsRide>> = flow {
        emit(Result.Loading)
        try {
            val response = api.updateGoodsRideById("Bearer $token", id, request)
            val data = response.data.toDomain()
            emit(Result.Success(data))
            Log.d("GoodsRideRepo", "✅ update: id=$id")
        } catch (e: Exception) {
            emit(Result.Error(e.message ?: "Unknown error on updateGoodsRideById"))
            Log.e("GoodsRideRepo", "❌ update error", e)
        }
    }.flowOn(Dispatchers.IO)


    override suspend fun deleteGoodsRideById(
        token: String,
        id: Int
    ): Flow<Result<Boolean>> = flow {
        emit(Result.Loading)
        try {
            val response = api.deleteGoodsRideById("Bearer $token", id)
            emit(Result.Success(true))
            Log.d("GoodsRideRepo", "✅ delete: id=$id message=${response.message}")
        } catch (e: Exception) {
            emit(Result.Error(e.message ?: "Unknown error on deleteGoodsRideById"))
            Log.e("GoodsRideRepo", "❌ delete error", e)
        }
    }.flowOn(Dispatchers.IO)
}