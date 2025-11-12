package com.example.nebeng.feature_goods_transaction.data.repository

import com.example.nebeng.feature_goods_transaction.data.remote.model.request.CreateGoodsTransactionRequest
import kotlinx.coroutines.flow.Flow
import com.example.nebeng.core.common.Result
import com.example.nebeng.feature_goods_transaction.data.remote.model.request.UpdateGoodsTransactionRequest
import com.example.nebeng.feature_goods_transaction.domain.model.GoodsTransaction

interface GoodsTransactionRepository {
    suspend fun createGoodsTransaction(
        token: String,
        request: CreateGoodsTransactionRequest
    ): Flow<Result<GoodsTransaction>>

    suspend fun getAllGoodsTransactions(
        token: String
    ): Flow<Result<List<GoodsTransaction>>>

    suspend fun getGoodsTransactionById(
        token: String,
        id: Int
    ): Flow<Result<GoodsTransaction>>

    suspend fun updateGoodsTransactionById(
        token: String,
        id: Int,
        request: UpdateGoodsTransactionRequest
    ): Flow<Result<GoodsTransaction>>

    suspend fun deleteGoodsTransactionById(
        token: String,
        id: Int
    ): Flow<Result<String>>
}