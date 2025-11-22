package com.example.nebeng.feature_payment_method.data.repository

import android.util.Log
import com.example.nebeng.core.common.Result
import com.example.nebeng.feature_payment_method.data.remote.api.PaymentMethodApi
import com.example.nebeng.feature_payment_method.data.remote.model.mapper.toDomain
import com.example.nebeng.feature_payment_method.data.remote.model.mapper.toSummary
import com.example.nebeng.feature_payment_method.data.remote.model.request.CreatePaymentMethodRequest
import com.example.nebeng.feature_payment_method.data.remote.model.request.UpdatePaymentMethodRequest
import com.example.nebeng.feature_payment_method.domain.model.PaymentMethod
import com.example.nebeng.feature_payment_method.domain.model.PaymentMethodSummary
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class PaymentMethodRepositoryImpl @Inject constructor(
    private val api: PaymentMethodApi
): PaymentMethodRepository {
    override suspend fun getAllPaymentMethods(token: String): Flow<Result<List<PaymentMethod>>> = flow {
        emit(Result.Loading)
        try {
            val response = api.getAllPaymentMethods("Bearer $token")
            if (response.isSuccessful) {
                val methods = response.body()?.data?.map { it.toDomain() }.orEmpty()
                emit(Result.Success(methods))
                Log.d("PaymentRepo", "✅ getAllPaymentMethods: ${methods.size} method(s) loaded")
            } else {
                emit(Result.Error(response.errorBody()?.string() ?: "Failed to fetch payment methods"))
            }
        } catch (e: Exception) {
            emit(Result.Error(e.message ?: "Unknown error while fetching payment methods"))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun getPaymentMethodById(token: String, id: Int): Flow<Result<PaymentMethod>> = flow {
        emit(Result.Loading)
        try {
            val response = api.getPaymentMethodById("Bearer $token", id)
            if (response.isSuccessful) {
                val method = response.body()?.data?.toDomain()
                if (method != null) {
                    emit(Result.Success(method))
                    Log.d("PaymentRepo", "✅ getPaymentMethodById: ${method.id}")
                } else emit(Result.Error("Payment method not found"))
            } else {
                emit(Result.Error(response.errorBody()?.string() ?: "Failed to fetch by ID"))
            }
        } catch (e: Exception) {
            emit(Result.Error(e.message ?: "Unknown error while fetching payment method by ID"))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun createPaymentMethod(
        token: String,
        request: CreatePaymentMethodRequest
    ): Flow<Result<PaymentMethod>> = flow {
        emit(Result.Loading)
        try {
            val response = api.createPaymentMethod("Bearer $token", request)
            if (response.isSuccessful) {
                val method = response.body()?.data?.toDomain()
                if (method != null) {
                    emit(Result.Success(method))
                    Log.d("PaymentRepo", "✅ createPaymentMethod: ${method.id}")
                } else emit(Result.Error("Create response empty"))
            } else {
                emit(Result.Error(response.errorBody()?.string() ?: "Failed to create payment method"))
            }
        } catch (e: Exception) {
            emit(Result.Error(e.message ?: "Unknown error while creating payment method"))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun updatePaymentMethod(
        token: String,
        id: Int,
        request: UpdatePaymentMethodRequest
    ): Flow<Result<PaymentMethod>> = flow {
        emit(Result.Loading)
        try {
            val response = api.updatePaymentMethod("Bearer $token", id, request)
            if (response.isSuccessful) {
                val method = response.body()?.data?.toDomain()
                if (method != null) {
                    emit(Result.Success(method))
                    Log.d("PaymentRepo", "✅ updatePaymentMethod: id=$id updated")
                } else emit(Result.Error("Update response empty"))
            } else {
                emit(Result.Error(response.errorBody()?.string() ?: "Failed to update payment method"))
            }
        } catch (e: Exception) {
            emit(Result.Error(e.message ?: "Unknown error while updating payment method"))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun deletePaymentMethod(token: String, id: Int): Flow<Result<Boolean>> = flow {
        emit(Result.Loading)
        try {
            val response = api.deletePaymentMethod("Bearer $token", id)
            if (response.isSuccessful) {
                emit(Result.Success(true))
                Log.d("PaymentRepo", "✅ deletePaymentMethod: id=$id deleted")
            } else {
                emit(Result.Error(response.errorBody()?.string() ?: "Failed to delete payment method"))
            }
        } catch (e: Exception) {
            emit(Result.Error(e.message ?: "Unknown error while deleting payment method"))
        }
    }.flowOn(Dispatchers.IO)



    override suspend fun getAllPaymentMethodsSummary(token: String): Flow<Result<List<PaymentMethodSummary>>> = flow {
        emit(Result.Loading)
        try {
            val response = api.getAllPaymentMethods("Bearer $token")
            if (response.isSuccessful) {
                val methods = response.body()?.data?.map { it.toSummary() }.orEmpty()
                emit(Result.Success(methods))
                Log.d("PaymentRepo", "✅ getAllPaymentMethods: ${methods.size} method(s) loaded")
            } else {
                emit(Result.Error(response.errorBody()?.string() ?: "Failed to fetch payment methods"))
            }
        } catch (e: Exception) {
            emit(Result.Error(e.message ?: "Unknown error while fetching payment methods"))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun getPaymentMethodByIdSummary(token: String, id: Int): Flow<Result<PaymentMethodSummary>> = flow {
        emit(Result.Loading)
        try {
            val response = api.getPaymentMethodById("Bearer $token", id)
            if (response.isSuccessful) {
                val method = response.body()?.data?.toSummary()
                if (method != null) {
                    emit(Result.Success(method))
                    Log.d("PaymentRepo", "✅ getPaymentMethodById: ${method.id}")
                } else emit(Result.Error("Payment method not found"))
            } else {
                emit(Result.Error(response.errorBody()?.string() ?: "Failed to fetch by ID"))
            }
        } catch (e: Exception) {
            emit(Result.Error(e.message ?: "Unknown error while fetching payment method by ID"))
        }
    }.flowOn(Dispatchers.IO)
}