package com.example.nebeng.feature_goods_transaction.domain.usecase

data class GoodsTransactionUseCases(
    val create: CreateGoodsTransactionUseCase,
    val readAll: ReadAllGoodsTransactionUseCase,
    val readById: ReadByIdGoodsTransactionUseCase,
    val update: UpdateGoodsTransactionUseCase,
    val delete: DeleteGoodsTransactionUseCase
)
