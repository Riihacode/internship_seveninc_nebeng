package com.example.nebeng.feature_passenger_transaction.data.repository

import com.example.nebeng.core.common.Result
import com.example.nebeng.feature_passenger_transaction.data.remote.api.PassengerTransactionApi
import com.example.nebeng.feature_passenger_transaction.data.remote.model.mapper.*
import com.example.nebeng.feature_passenger_transaction.data.remote.model.request.*
import com.example.nebeng.feature_passenger_transaction.domain.model.PassengerTransaction
import com.example.nebeng.feature_passenger_transaction.domain.model.PassengerTransactionSummary
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import retrofit2.HttpException
import java.io.IOException

class PassengerTransactionRepositoryImpl @Inject constructor(
    private val api: PassengerTransactionApi
) : PassengerTransactionRepository {

    override suspend fun getAllPassengerTransactions(token: String): Flow<Result<List<PassengerTransaction>>> = flow {
        emit(Result.Loading)
        try {
            val response = api.getAllPassengerTransactions(token)
            val mapped = PassengerTransactionMapper.fromDataItemList(response.data)
            emit(Result.Success(mapped))
        } catch (e: Exception) {
            emit(Result.Error(handleError(e)))
        }
    }

    override suspend fun getPassengerTransactionById(token: String, id: Int): Flow<Result<PassengerTransaction>> = flow {
        emit(Result.Loading)
        try {
            val response = api.getPassengerTransactionById(token, id)
            val mapped = PassengerTransactionMapper.fromByIdDto(response.data)
            emit(Result.Success(mapped))
        } catch (e: Exception) {
            emit(Result.Error(handleError(e)))
        }
    }

    override suspend fun getPassengerTransactionByPassengerRideBookingId(
        token: String,
        passengerRideBookingId: Int
    ): Flow<Result<PassengerTransaction>> = flow {
        emit(Result.Loading)
        try {
            val response = api.getPassengerTransactionByPassengerRideBookingId(token, passengerRideBookingId)
            val mapped = PassengerTransactionMapper.fromByPassengerRideIdDto(response.data)
            emit(Result.Success(mapped))
        } catch (e: Exception) {
            emit(Result.Error(handleError(e)))
        }
    }

    override suspend fun createPassengerTransaction(
        token: String,
        request: CreatePassengerTransactionRequest
    ): Flow<Result<PassengerTransaction>> = flow {
        emit(Result.Loading)
        try {
            val response = api.createPassengerTransaction(token, request)
            val mapped = PassengerTransactionMapper.fromCreateDto(response.data)
            emit(Result.Success(mapped))
        } catch (e: Exception) {
            emit(Result.Error(handleError(e)))
        }
    }

    override suspend fun updatePassengerTransactionById(
        token: String,
        id: Int,
        request: UpdatePassengerTransactionRequest
    ): Flow<Result<PassengerTransaction>> = flow {
        emit(Result.Loading)
        try {
            val response = api.updatePassengerTransactionById(token, id, request)
            val mapped = PassengerTransactionMapper.fromUpdateDto(response.data)
            emit(Result.Success(mapped))
        } catch (e: Exception) {
            emit(Result.Error(handleError(e)))
        }
    }

    override suspend fun patchStatusPassengerTransactionById(
        token: String,
        id: Int,
        request: PatchStatusByIdPassengerTransactionRequest
    ): Flow<Result<PassengerTransaction>> = flow {
        emit(Result.Loading)
        try {
            val response = api.patchStatusPassengerTransactionById(token, id, request)
            val mapped = PassengerTransactionMapper.fromPatchStatusDto(response.data)
            emit(Result.Success(mapped))
        } catch (e: Exception) {
            emit(Result.Error(handleError(e)))
        }
    }

    override suspend fun deletePassengerTransactionById(token: String, id: Int): Flow<Result<String>> = flow {
        emit(Result.Loading)
        try {
            val response = api.deletePassengerTransactionById(token, id)
            emit(Result.Success(response.message))
        } catch (e: Exception) {
            emit(Result.Error(handleError(e)))
        }
    }

    // helper function for clean error messages
    private fun handleError(e: Exception): String = when (e) {
        is HttpException -> e.message ?: "HTTP error"
        is IOException -> "Network error: ${e.message}"
        else -> "Unexpected error: ${e.message}"
    }


    override suspend fun getAllPassengerTransactionsSummary(token: String): Flow<Result<List<PassengerTransactionSummary>>> = flow {
        emit(Result.Loading)
        try {
            val response = api.getAllPassengerTransactions(token)
//            val mapped = PassengerTransactionMapper.fromDataItemList(response.data)
            val passengerTransaction = response.data.map { it.toSummary() }
            emit(Result.Success(passengerTransaction))
        } catch (e: Exception) {
            emit(Result.Error(handleError(e)))
        }
    }

    override suspend fun getPassengerTransactionByIdSummary(token: String, id: Int): Flow<Result<PassengerTransactionSummary>> = flow {
        emit(Result.Loading)
        try {
            val response = api.getPassengerTransactionById(token, id)
//            val mapped = PassengerTransactionMapper.fromByIdDto(response.data)
            val passengerTransaction = response.data.toSummary()
            emit(Result.Success(passengerTransaction))
        } catch (e: Exception) {
            emit(Result.Error(handleError(e)))
        }
    }

}