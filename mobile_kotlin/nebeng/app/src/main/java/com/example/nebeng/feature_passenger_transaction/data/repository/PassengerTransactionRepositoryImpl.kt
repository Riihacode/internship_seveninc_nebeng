package com.example.nebeng.feature_passenger_transaction.data.repository

import android.util.Log
import com.example.nebeng.core.common.Result
import com.example.nebeng.feature_passenger_transaction.data.remote.api.PassengerTransactionApi
import com.example.nebeng.feature_passenger_transaction.data.remote.model.mapper.*
import com.example.nebeng.feature_passenger_transaction.data.remote.model.request.*
import com.example.nebeng.feature_passenger_transaction.domain.model.PassengerTransaction
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import retrofit2.HttpException
import java.io.IOException

//class PassengerTransactionRepositoryImpl @Inject constructor(
//    private val api: PassengerTransactionApi
//): PassengerTransactionRepository {
//    override suspend fun getAllPassengerTransactions(token: String): Flow<Result<List<PassengerTransaction>>> = flow {
//        emit(Result.Loading)
//        try {
//            val response = api.getAllPassengerTransactions("Bearer $token")
//            val data = response.data.map { it.toDomain() }
//            emit(Result.Success(data))
//            Log.d("PassengerTransactionRepo", "✅ getAll: ${data.size} item(s)")
//        } catch (e: Exception) {
//            emit(Result.Error(e.message ?: "Unknown error on getAllPassengerTransactions"))
//            Log.e("PassengerTransactionRepo", "❌ getAll error", e)
//        }
//    }.flowOn(Dispatchers.IO)
//
//
//    override suspend fun getPassengerTransactionById(token: String, id: Int): Flow<Result<PassengerTransaction>> = flow {
//        emit(Result.Loading)
//        try {
//            val response = api.getPassengerTransactionById("Bearer $token", id)
//            val data = response.data.toDomain()
//            emit(Result.Success(data))
//            Log.d("PassengerTransactionRepo", "✅ getById: id=$id")
//        } catch (e: Exception) {
//            emit(Result.Error(e.message ?: "Unknown error on getPassengerTransactionById"))
//            Log.e("PassengerTransactionRepo", "❌ getById error", e)
//        }
//    }.flowOn(Dispatchers.IO)
//
//
//    override suspend fun getByPassengerRideBookingId(
//        token: String,
//        passengerRideBookingId: Int
//    ): Flow<Result<PassengerTransaction>> = flow {
//        emit(Result.Loading)
//        try {
//            val response = api.getByPassengerRideBookingId("Bearer $token", passengerRideBookingId)
//            val data = response.data.toDomain()
//            emit(Result.Success(data))
//            Log.d("PassengerTransactionRepo", "✅ getByBookingId: booking=$passengerRideBookingId")
//        } catch (e: Exception) {
//            emit(Result.Error(e.message ?: "Unknown error on getByPassengerRideBookingId"))
//            Log.e("PassengerTransactionRepo", "❌ getByBookingId error", e)
//        }
//    }.flowOn(Dispatchers.IO)
//
//
//    override suspend fun createPassengerTransaction(
//        token: String,
//        request: CreatePassengerTransactionRequest
//    ): Flow<Result<PassengerTransaction>> = flow {
//        emit(Result.Loading)
//        try {
//            val response = api.createPassengerTransaction("Bearer $token", request)
//            val data = response.data.toDomain()
//            emit(Result.Success(data))
//            Log.d("PassengerTransactionRepo", "✅ create: id=${data.id}")
//        } catch (e: Exception) {
//            emit(Result.Error(e.message ?: "Unknown error on createPassengerTransaction"))
//            Log.e("PassengerTransactionRepo", "❌ create error", e)
//        }
//    }.flowOn(Dispatchers.IO)
//
//
//    override suspend fun updatePassengerTransactionById(
//        token: String,
//        id: Int,
//        request: UpdatePassengerTransactionRequest
//    ): Flow<Result<PassengerTransaction>> = flow {
//        emit(Result.Loading)
//        try {
//            val response = api.updatePassengerTransactionById("Bearer $token", id, request)
//            val data = response.data.toDomain()
//            emit(Result.Success(data))
//            Log.d("PassengerTransactionRepo", "✅ update: id=$id")
//        } catch (e: Exception) {
//            emit(Result.Error(e.message ?: "Unknown error on updatePassengerTransactionById"))
//            Log.e("PassengerTransactionRepo", "❌ update error", e)
//        }
//    }.flowOn(Dispatchers.IO)
//
//
//    override suspend fun patchPassengerTransactionStatusById(
//        token: String,
//        id: Int,
//        request: PatchStatusByIdPassengerTransactionRequest
//    ): Flow<Result<PassengerTransaction>> = flow {
//        emit(Result.Loading)
//        try {
//            val response = api.patchPassengerTransactionStatusById("Bearer $token", id, request)
//            val data = response.data.toDomain()
//            emit(Result.Success(data))
//            Log.d("PassengerTransactionRepo", "✅ patchStatus: id=$id newStatus=${data.paymentStatus}")
//        } catch (e: Exception) {
//            emit(Result.Error(e.message ?: "Unknown error on patchPassengerTransactionStatusById"))
//            Log.e("PassengerTransactionRepo", "❌ patchStatus error", e)
//        }
//    }.flowOn(Dispatchers.IO)
//
//
//    override suspend fun deletePassengerTransactionById(token: String, id: Int): Flow<Result<Boolean>> = flow {
//        emit(Result.Loading)
//        try {
//            val response = api.deletePassengerTransactionById("Bearer $token", id)
//            emit(Result.Success(true))
//            Log.d("PassengerTransactionRepo", "✅ delete: id=$id message=${response.message}")
//        } catch (e: Exception) {
//            emit(Result.Error(e.message ?: "Unknown error on deletePassengerTransactionById"))
//            Log.e("PassengerTransactionRepo", "❌ delete error", e)
//        }
//    }.flowOn(Dispatchers.IO)
//}
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
}