package com.example.nebeng.feature_driver.data.repository

import com.example.nebeng.feature_driver.data.remote.model.request.CreateDriverRequest
import com.example.nebeng.feature_driver.data.remote.model.request.UpdateDriverRequest
import com.example.nebeng.feature_driver.domain.model.Driver
import kotlinx.coroutines.flow.Flow
import com.example.nebeng.core.common.Result
import com.example.nebeng.feature_driver.domain.model.DriverSummary

interface DriverRepository {
    suspend fun getAllDrivers(token: String): Flow<Result<List<Driver>>>

    suspend fun getDriverById(token: String, id: Int): Flow<Result<Driver>>

    suspend fun createDriver(token: String, request: CreateDriverRequest): Flow<Result<Driver>>

    suspend fun updateDriver(token: String, id: Int, request: UpdateDriverRequest): Flow<Result<Driver>>

    suspend fun deleteDriver(token: String, id: Int): Flow<Result<Boolean>>

    suspend fun getDriverByIdSummary(token: String, id: Int): Flow<Result<DriverSummary>>
}