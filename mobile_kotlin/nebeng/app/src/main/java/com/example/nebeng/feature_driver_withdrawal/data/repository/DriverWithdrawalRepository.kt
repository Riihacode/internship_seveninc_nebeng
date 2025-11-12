package com.example.nebeng.feature_driver_withdrawal.data.repository

import com.example.nebeng.core.common.Result
import com.example.nebeng.feature_driver_withdrawal.data.remote.model.request.CreateDriverWithdrawalRequest
import com.example.nebeng.feature_driver_withdrawal.data.remote.model.request.UpdateDriverWithdrawalRequest
import com.example.nebeng.feature_driver_withdrawal.domain.model.DriverWithdrawal
import kotlinx.coroutines.flow.Flow

interface DriverWithdrawalRepository {
    suspend fun createDriverWithdrawal(
        token: String,
        request: CreateDriverWithdrawalRequest
    ): Flow<Result<DriverWithdrawal>>

    suspend fun getAllDriverWithdrawals(
        token: String
    ): Flow<Result<List<DriverWithdrawal>>>

    suspend fun getDriverWithdrawalById(
        token: String,
        id: Int
    ): Flow<Result<DriverWithdrawal>>

    suspend fun getDriverWithdrawalsByDriverId(
        token: String,
        driverId: Int
    ): Flow<Result<List<DriverWithdrawal>>>

    suspend fun getDriverWithdrawalsByStatus(
        token: String,
        status: String
    ): Flow<Result<List<DriverWithdrawal>>>

    suspend fun updateDriverWithdrawalById(
        token: String,
        id: Int,
        request: UpdateDriverWithdrawalRequest
    ): Flow<Result<DriverWithdrawal>>

    suspend fun deleteDriverWithdrawalById(
        token: String,
        id: Int
    ): Flow<Result<String>>
}