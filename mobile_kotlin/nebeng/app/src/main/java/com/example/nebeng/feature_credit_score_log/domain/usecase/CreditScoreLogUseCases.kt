package com.example.nebeng.feature_credit_score_log.domain.usecase

data class CreditScoreLogUseCases(
    val create: CreateCreditScoreLogUseCase,
    val readAll: ReadAllCreditScoreLogUseCase,
    val readById: ReadByIdCreditScoreLogUseCase,
    val readByDriverId: ReadByDriverIdCreditScoreLogUseCase,
    val update: UpdateCreditScoreLogUseCase,
    val delete: DeleteCreditScoreLogUseCase
)