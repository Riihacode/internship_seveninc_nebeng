package com.example.nebeng.feature_goods_ride.domain.usecase

data class GoodsRideUseCases(
    val create: CreateGoodsRideUseCase,
    val readAll: ReadAllGoodsRideUseCase,
    val readById: ReadByIdGoodsRideUseCase,
    val update: UpdateGoodsRideUseCase,
    val delete: DeleteGoodsRideUseCase
)
