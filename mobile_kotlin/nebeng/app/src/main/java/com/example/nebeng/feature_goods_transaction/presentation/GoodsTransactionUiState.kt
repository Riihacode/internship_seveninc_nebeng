package com.example.nebeng.feature_goods_transaction.presentation

import com.example.nebeng.feature_goods_transaction.domain.model.GoodsTransaction
import com.example.nebeng.core.common.Result

data class GoodsTransactionUiState(
    val isLoading: Boolean = false,
    val transactions: List<GoodsTransaction> = emptyList(),
    val selectedTransaction: GoodsTransaction? = null,
    val createResult: Result<GoodsTransaction>? = null,
    val updateResult: Result<GoodsTransaction>? = null,
    val deleteResult: Result<String>? = null,
    val errorMessage: String? = null
)