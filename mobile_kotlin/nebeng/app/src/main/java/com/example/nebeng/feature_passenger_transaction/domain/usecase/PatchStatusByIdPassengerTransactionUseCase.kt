package com.example.nebeng.feature_passenger_transaction.domain.usecase

import com.example.nebeng.core.common.Result
import com.example.nebeng.feature_passenger_transaction.data.remote.model.request.PatchStatusByIdPassengerTransactionRequest
import com.example.nebeng.feature_passenger_transaction.data.repository.PassengerTransactionRepository
import com.example.nebeng.feature_passenger_transaction.domain.model.PassengerTransaction
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PatchStatusByIdPassengerTransactionUseCase@Inject constructor(
    private val repository: PassengerTransactionRepository
) {
    suspend operator fun invoke(
        token: String,
        id: Int,
        request: PatchStatusByIdPassengerTransactionRequest
    ): Flow<Result<PassengerTransaction>> {
        return repository.patchPassengerTransactionStatusById(token, id, request)
    }
}