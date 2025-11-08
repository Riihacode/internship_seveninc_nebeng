package com.example.nebeng.feature_goods_transaction.data.repository

import android.util.Log
import com.example.nebeng.core.common.Result
import com.example.nebeng.feature_goods_transaction.data.remote.api.GoodsTransactionApi
import com.example.nebeng.feature_goods_transaction.data.remote.model.mapper.GoodsTransactionMapper
import com.example.nebeng.feature_goods_transaction.data.remote.model.request.CreateGoodsTransactionRequest
import com.example.nebeng.feature_goods_transaction.data.remote.model.request.UpdateGoodsTransactionRequest
import com.example.nebeng.feature_goods_transaction.domain.model.GoodsTransaction
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GoodsTransactionRepositoryImpl @Inject constructor(
    private val api: GoodsTransactionApi
): GoodsTransactionRepository {
    // 🔹 CREATE
    override suspend fun createGoodsTransaction(
        token: String,
        request: CreateGoodsTransactionRequest
    ): Flow<Result<GoodsTransaction>> = flow {
        emit(Result.Loading)
        try {
            val response = api.createGoodsTransaction("Bearer $token", request)
            val transaction = GoodsTransactionMapper.fromCreateResponse(response)
            emit(Result.Success(transaction))
            Log.d("GoodsTransactionRepo", "✅ Created transaction id=${transaction.id}")
        } catch (e: Exception) {
            emit(Result.Error(e.message ?: "Unknown error while creating transaction"))
        }
    }.flowOn(Dispatchers.IO)


    // 🔹 READ ALL
    override suspend fun getAllGoodsTransactions(
        token: String
    ): Flow<Result<List<GoodsTransaction>>> = flow {
        emit(Result.Loading)
        try {
            val response = api.getAllGoodsTransactions("Bearer $token")
            val list = response.readAllGoodsTransactionResponse.map { GoodsTransactionMapper.fromDataDto(it) }
            emit(Result.Success(list))
            Log.d("GoodsTransactionRepo", "✅ Loaded ${list.size} transaction(s)")
        } catch (e: Exception) {
            emit(Result.Error(e.message ?: "Unknown error while fetching transactions"))
        }
    }.flowOn(Dispatchers.IO)


    // 🔹 READ BY ID
    override suspend fun getGoodsTransactionById(
        token: String,
        id: Int
    ): Flow<Result<GoodsTransaction>> = flow {
        emit(Result.Loading)
        try {
            val response = api.getGoodsTransactionById("Bearer $token", id)
            val transaction = GoodsTransactionMapper.fromReadByIdResponse(response)
            emit(Result.Success(transaction))
            Log.d("GoodsTransactionRepo", "✅ Fetched transaction id=$id")
        } catch (e: Exception) {
            emit(Result.Error(e.message ?: "Unknown error while fetching transaction by ID"))
        }
    }.flowOn(Dispatchers.IO)


    // 🔹 UPDATE
    override suspend fun updateGoodsTransactionById(
        token: String,
        id: Int,
        request: UpdateGoodsTransactionRequest
    ): Flow<Result<GoodsTransaction>> = flow {
        emit(Result.Loading)
        try {
            val response = api.updateGoodsTransactionById("Bearer $token", id, request)
            val transaction = GoodsTransactionMapper.fromDataDto(response.data)
            emit(Result.Success(transaction))
            Log.d("GoodsTransactionRepo", "✅ Updated transaction id=$id")
        } catch (e: Exception) {
            emit(Result.Error(e.message ?: "Unknown error while updating transaction"))
        }
    }.flowOn(Dispatchers.IO)


    // 🔹 DELETE
    override suspend fun deleteGoodsTransactionById(
        token: String,
        id: Int
    ): Flow<Result<String>> = flow {
        emit(Result.Loading)
        try {
            val response = api.deleteGoodsTransactionById("Bearer $token", id)
            emit(Result.Success(response.message))
            Log.d("GoodsTransactionRepo", "✅ Deleted transaction id=$id")
        } catch (e: Exception) {
            emit(Result.Error(e.message ?: "Unknown error while deleting transaction"))
        }
    }.flowOn(Dispatchers.IO)
}