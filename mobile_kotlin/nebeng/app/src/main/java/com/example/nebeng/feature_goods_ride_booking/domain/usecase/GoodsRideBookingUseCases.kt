package com.example.nebeng.feature_goods_ride_booking.domain.usecase

data class GoodsRideBookingUseCases(
    val create: CreateGoodsRideBookingUseCase,
    val readAll: ReadAllGoodsRideBookingUseCase,
    val readById: ReadByIdGoodsRideBookingUseCase,
    val readByDriverId: ReadByDriverIdGoodsRideBookingUseCase,
    val readByStatus: ReadByStatusGoodsRideBookingUseCase,
    val update: UpdateGoodsRideBookingUseCase,
    val delete: DeleteGoodsRideBookingUseCase
)