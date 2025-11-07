package com.example.nebeng.feature_passenger_transaction.data.repository

import com.example.nebeng.core.common.Result
import com.example.nebeng.feature_passenger_transaction.data.remote.model.request.CreatePassengerTransactionRequest
import com.example.nebeng.feature_passenger_transaction.data.remote.model.request.PatchStatusByIdPassengerTransactionRequest
import com.example.nebeng.feature_passenger_transaction.data.remote.model.request.UpdatePassengerTransactionRequest
import com.example.nebeng.feature_passenger_transaction.domain.model.PassengerTransaction
import kotlinx.coroutines.flow.Flow

interface PassengerTransactionRepository {
    suspend fun getAllPassengerTransactions(token: String): Flow<Result<List<PassengerTransaction>>>

    suspend fun getPassengerTransactionById(token: String, id: Int): Flow<Result<PassengerTransaction>>

    suspend fun getByPassengerRideBookingId(token: String, passengerRideBookingId: Int): Flow<Result<PassengerTransaction>>

    suspend fun createPassengerTransaction(token: String, request: CreatePassengerTransactionRequest): Flow<Result<PassengerTransaction>>

    suspend fun updatePassengerTransactionById(token: String, id: Int, request: UpdatePassengerTransactionRequest): Flow<Result<PassengerTransaction>>

    suspend fun patchPassengerTransactionStatusById(token: String, id: Int, request: PatchStatusByIdPassengerTransactionRequest): Flow<Result<PassengerTransaction>>

    suspend fun deletePassengerTransactionById(token: String, id: Int): Flow<Result<Boolean>>
}