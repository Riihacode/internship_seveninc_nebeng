package com.example.nebeng.feature_passenger_transaction.data.repository.updated

import com.example.nebeng.core.common.Result
import com.example.nebeng.feature_passenger_transaction.data.remote.api.updated.PassengerTransactionUpdatedApi
import com.example.nebeng.feature_passenger_transaction.data.remote.model.mapper.updated.*
import com.example.nebeng.feature_passenger_transaction.data.remote.model.request.*
import com.example.nebeng.feature_passenger_transaction.domain.model.updated.PassengerTransaction
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import retrofit2.HttpException
import java.io.IOException
import kotlin.collections.map

class PassengerTransactionUpdatedRepositoryImpl @Inject constructor(
    private val api: PassengerTransactionUpdatedApi
) : PassengerTransactionUpdatedRepository {

    override suspend fun getAllPassengerTransactions(token: String): Flow<Result<List<PassengerTransaction>>> = flow {
        emit(Result.Loading)
        try {
            val response = api.getAllPassengerTransactions(token)
//            val mapped = PassengerTransactionMapper.fromDataItemList(response.data)
            val mapped = response.data.map { it.toPassengerTransactionUpdated() }
            emit(Result.Success(mapped))
        } catch (e: Exception) {
            emit(Result.Error(handleError(e)))
        }
    }

    override suspend fun getPassengerTransactionById(token: String, id: Int): Flow<Result<PassengerTransaction>> = flow {
        emit(Result.Loading)
        try {
            val response = api.getPassengerTransactionById(token, id)
//            val mapped = PassengerTransactionMapper.fromByIdDto(response.data)
            val mapped = response.data.toPassengerTransactionUpdated()
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
//            val mapped = PassengerTransactionMapper.fromByPassengerRideIdDto(response.data)
            val mapped = response.data.toPassengerTransactionUpdated()
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
//            val mapped = PassengerTransactionMapper.fromCreateDto(response.data)
            val mapped = response.data.toPassengerTransactionUpdated()
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
//            val mapped = PassengerTransactionMapper.fromUpdateDto(response.data)
            val mapped = response.data.toPassengerTransactionUpdated()
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
//            val mapped = PassengerTransactionMapper.fromPatchStatusDto(response.data)
            val mapped = response.data.toPassengerTransactionUpdated()
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
}