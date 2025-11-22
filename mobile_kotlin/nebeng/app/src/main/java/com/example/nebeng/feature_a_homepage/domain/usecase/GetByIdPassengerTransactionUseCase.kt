package com.example.nebeng.feature_a_homepage.domain.usecase

import com.example.nebeng.feature_passenger_transaction.data.repository.PassengerTransactionRepository
import javax.inject.Inject

class GetByIdPassengerTransactionUseCase @Inject constructor(
    private val repository: PassengerTransactionRepository
) {
}