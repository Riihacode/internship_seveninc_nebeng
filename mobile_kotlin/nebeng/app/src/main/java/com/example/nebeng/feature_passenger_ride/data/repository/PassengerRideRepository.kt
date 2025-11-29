package com.example.nebeng.feature_passenger_ride.data.repository

import com.example.nebeng.core.common.Result
import com.example.nebeng.feature_passenger_ride.data.remote.model.request.CreatePassengerRideRequest
import com.example.nebeng.feature_passenger_ride.data.remote.model.request.UpdatePassengerRideRequest
import com.example.nebeng.feature_passenger_ride.domain.model.PassengerRide
import com.example.nebeng.feature_passenger_ride.domain.model.PassengerRideSummary
import kotlinx.coroutines.flow.Flow

//interface PassengerRideRepository {
//    // Get All
//    suspend fun getAllPassengerRides(
//        token: String
//    ): Result<List<PassengerRide>>
//
//    // Get By Id Table
//    suspend fun getPassengerRideById(
//        token: String,
//        id: Int
//    ): Result<PassengerRide>
//
//    // Get By Driver Id in Drivers table
//    suspend fun getPassengerRidesByDriverId(
//        token: String,
//        driverId: Int
//    ): Result<List<PassengerRide>>
//
//    // Get By Status Passenger Ride
//    suspend fun getPassengerRidesByStatus(
//        token: String,
//        status: String
//    ): Result<List<PassengerRide>>
//
//    // Post Passenger Ride
//    suspend fun createPassengerRide(
//        token: String,
//        request: CreatePassengerRideRequest
//    ): Result<PassengerRide>
//
//    // Update Passenger Ride
//    suspend fun updatePassengerRide(
//        token: String,
//        id: Int,
//        request: UpdatePassengerRideRequest
//    ): Result<PassengerRide>
//
//    // Delete Passenger Ride
//    suspend fun deletePassengerRide(
//        token: String,
//        id: Int
//    ): Result<String>
//
//
//    // Get All
//    suspend fun getAllPassengerRidesSummary(
//        token: String
//    ): Result<List<PassengerRideSummary>>
//
//    // Get By Id Table
//    suspend fun getPassengerRideByIdSummary(
//        token: String,
//        id: Int
//    ): Result<PassengerRideSummary>
//}
interface PassengerRideRepository {

    suspend fun getAllPassengerRides(
        token: String
    ): Flow<Result<List<PassengerRide>>>

    suspend fun getPassengerRideById(
        token: String,
        id: Int
    ): Flow<Result<PassengerRide>>

    suspend fun getPassengerRidesByDriverId(
        token: String,
        driverId: Int
    ): Flow<Result<List<PassengerRide>>>

    suspend fun getPassengerRidesByStatus(
        token: String,
        status: String
    ): Flow<Result<List<PassengerRide>>>

    suspend fun createPassengerRide(
        token: String,
        request: CreatePassengerRideRequest
    ): Flow<Result<PassengerRide>>

    suspend fun updatePassengerRide(
        token: String,
        id: Int,
        request: UpdatePassengerRideRequest
    ): Flow<Result<PassengerRide>>

    suspend fun deletePassengerRide(
        token: String,
        id: Int
    ): Flow<Result<String>>

    suspend fun getAllPassengerRidesSummary(
        token: String
    ): Flow<Result<List<PassengerRideSummary>>>

    suspend fun getPassengerRideByIdSummary(
        token: String,
        id: Int
    ): Flow<Result<PassengerRideSummary>>
}