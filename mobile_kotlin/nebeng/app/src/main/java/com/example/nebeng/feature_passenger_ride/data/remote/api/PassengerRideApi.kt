package com.example.nebeng.feature_passenger_ride.data.remote.api

import com.example.nebeng.feature_passenger_ride.data.remote.model.request.CreatePassengerRideRequest
import com.example.nebeng.feature_passenger_ride.data.remote.model.request.UpdatePassengerRideRequest
import com.example.nebeng.feature_passenger_ride.data.remote.model.response.CreatePassengerRideResponse
import com.example.nebeng.feature_passenger_ride.data.remote.model.response.DeletePassengerRideResponse
import com.example.nebeng.feature_passenger_ride.data.remote.model.response.PassengerRideResponse
import com.example.nebeng.feature_passenger_ride.data.remote.model.response.UpdatePassengerRideResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface PassengerRideApi {
    @GET("api/passenger-rides")
    suspend fun getAllPassengerRides(
        @Header("Authorization") token: String
    ): Response<PassengerRideResponse>

    @GET("api/passenger-rides/{id}")
    suspend fun getPassengerRideById(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): Response<PassengerRideResponse>

    @GET("api/passenger-rides/driver/{id}")
    suspend fun getPassengerRidesByDriverId(
        @Header("Authorization") token: String,
        @Path("id") driverId: Int
    ): Response<PassengerRideResponse>

    @GET("api/passenger-rides/status/{status}")
    suspend fun getPassengerRidesByStatus(
        @Header("Authorization") token: String,
        @Path("status") status: String
    ): Response<PassengerRideResponse>

    @POST("api/passenger-rides/")
    suspend fun createPassengerRide(
        @Header("Authorization") token: String,
        @Body request: CreatePassengerRideRequest
    ): Response<CreatePassengerRideResponse>

    @PUT("api/passenger-rides/{id}")
    suspend fun updatePassengerRide(
        @Header("Authorization") token: String,
        @Path("id") id: Int,
        @Body request: UpdatePassengerRideRequest
    ): Response<UpdatePassengerRideResponse>

    @DELETE("api/passenger-rides/{id}")
    suspend fun deletePassengerRide(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): Response<DeletePassengerRideResponse>
}