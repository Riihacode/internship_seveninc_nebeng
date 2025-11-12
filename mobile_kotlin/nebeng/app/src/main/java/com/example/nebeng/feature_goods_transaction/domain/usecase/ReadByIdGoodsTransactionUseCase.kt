package com.example.nebeng.feature_goods_transaction.domain.usecase

import com.example.nebeng.core.common.Result
import com.example.nebeng.feature_goods_transaction.data.repository.GoodsTransactionRepository
import com.example.nebeng.feature_goods_transaction.domain.model.GoodsTransaction
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReadByIdGoodsTransactionUseCase @Inject constructor(
    private val repository: GoodsTransactionRepository
) {
    suspend operator fun invoke(token: String, id: Int): Flow<Result<GoodsTransaction>> {
        return repository.getGoodsTransactionById(token, id)
    }
}