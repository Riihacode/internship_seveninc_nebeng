package com.example.nebeng.feature_rating.domain.usecase

data class RatingUseCases(
    val create: CreateRatingUseCase,
    val readAll: ReadAllRatingUseCase,
    val readById: ReadByIdRatingUseCase,
    val readByDriverId: ReadByDriverIdRatingUseCase,
    val update: UpdateRatingUseCase,
    val delete: DeleteRatingUseCase
)