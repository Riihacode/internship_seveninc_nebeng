package com.example.nebeng.feature_passenger_ride.data.repository

import com.example.nebeng.core.common.Result
import com.example.nebeng.feature_passenger_ride.data.remote.model.request.CreatePassengerRideRequest
import com.example.nebeng.feature_passenger_ride.data.remote.model.request.UpdatePassengerRideRequest
import com.example.nebeng.feature_passenger_ride.domain.model.PassengerRide

interface PassengerRideRepository {
    // Get All
    suspend fun getAllPassengerRides(
        token: String
    ): Result<List<PassengerRide>>

    // Get By Id Table
    suspend fun getPassengerRideById(
        token: String,
        id: Int
    ): Result<PassengerRide>

    // Get By Driver Id in Drivers table
    suspend fun getPassengerRidesByDriverId(
        token: String,
        driverId: Int
    ): Result<List<PassengerRide>>

    // Get By Status Passenger Ride
    suspend fun getPassengerRidesByStatus(
        token: String,
        status: String
    ): Result<List<PassengerRide>>

    // Post Passenger Ride
    suspend fun createPassengerRide(
        token: String,
        request: CreatePassengerRideRequest
    ): Result<PassengerRide>

    // Update Passenger Ride
    suspend fun updatePassengerRide(
        token: String,
        id: Int,
        request: UpdatePassengerRideRequest
    ): Result<PassengerRide>

    // Delete Passenger Ride
    suspend fun deletePassengerRide(
        token: String,
        id: Int
    ): Result<String>
}