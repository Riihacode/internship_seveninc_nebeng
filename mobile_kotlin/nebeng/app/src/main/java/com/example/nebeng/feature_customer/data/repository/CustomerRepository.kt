package com.example.nebeng.feature_customer.data.repository

import com.example.nebeng.feature_customer.data.remote.model.request.CreateCustomerRequest
import com.example.nebeng.feature_customer.data.remote.model.request.PatchAddCreditAmountCustomerRequest
import com.example.nebeng.feature_customer.data.remote.model.request.PatchDeductCreditAmountCustomerRequest
import com.example.nebeng.feature_customer.data.remote.model.request.UpdateCustomerRequest
import com.example.nebeng.feature_customer.domain.model.Customer
import kotlinx.coroutines.flow.Flow
import com.example.nebeng.core.common.Result
import com.example.nebeng.feature_passenger_ride_booking.domain.model.PassengerRideBookingFull

interface CustomerRepository {
    suspend fun getAllCustomers(token: String): Flow<Result<List<Customer>>>

    suspend fun getCustomerById(token: String, id: Int): Flow<Result<Customer>>

    suspend fun getCustomerByUserId(token: String, userId: Int): Flow<Result<List<Customer>>>

    suspend fun createCustomer(
        token: String,
        request: CreateCustomerRequest
    ): Flow<Result<Customer>>

    suspend fun updateCustomer(
        token: String,
        id: Int,
        request: UpdateCustomerRequest
    ): Flow<Result<Customer>>

    suspend fun patchVerifyTrue(
        token: String,
        id: Int
    ): Flow<Result<Customer>>

    suspend fun patchAddCredit(
        token: String,
        id: Int,
        request: PatchAddCreditAmountCustomerRequest
    ): Flow<Result<Customer>>

    suspend fun patchDeductCredit(
        token: String,
        id: Int,
        request: PatchDeductCreditAmountCustomerRequest
    ): Flow<Result<Customer>>

    suspend fun deleteCustomer(
        token: String,
        id: Int
    ): Flow<Result<Boolean>>
}