package com.example.nebeng.feature_payment_method.data.repository

import com.example.nebeng.core.common.Result
import com.example.nebeng.feature_payment_method.data.remote.model.request.CreatePaymentMethodRequest
import com.example.nebeng.feature_payment_method.data.remote.model.request.UpdatePaymentMethodRequest
import com.example.nebeng.feature_payment_method.domain.model.PaymentMethod
import kotlinx.coroutines.flow.Flow

interface PaymentMethodRepository {
    suspend fun getAllPaymentMethods(token: String): Flow<Result<List<PaymentMethod>>>
    suspend fun getPaymentMethodById(token: String, id: Int): Flow<Result<PaymentMethod>>
    suspend fun createPaymentMethod(token: String, request: CreatePaymentMethodRequest): Flow<Result<PaymentMethod>>
    suspend fun updatePaymentMethod(token: String, id: Int, request: UpdatePaymentMethodRequest): Flow<Result<PaymentMethod>>
    suspend fun deletePaymentMethod(token: String, id: Int): Flow<Result<Boolean>>
}