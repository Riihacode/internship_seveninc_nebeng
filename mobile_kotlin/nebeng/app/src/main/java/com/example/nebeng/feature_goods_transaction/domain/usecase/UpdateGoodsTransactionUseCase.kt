package com.example.nebeng.feature_goods_transaction.domain.usecase

import com.example.nebeng.core.common.Result
import com.example.nebeng.feature_goods_transaction.data.remote.model.request.UpdateGoodsTransactionRequest
import com.example.nebeng.feature_goods_transaction.data.repository.GoodsTransactionRepository
import com.example.nebeng.feature_goods_transaction.domain.model.GoodsTransaction
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpdateGoodsTransactionUseCase @Inject constructor(
    private val repository: GoodsTransactionRepository
) {
    suspend operator fun invoke(token: String, id: Int, request: UpdateGoodsTransactionRequest): Flow<Result<GoodsTransaction>> {
        return repository.updateGoodsTransactionById(token, id, request)
    }
}