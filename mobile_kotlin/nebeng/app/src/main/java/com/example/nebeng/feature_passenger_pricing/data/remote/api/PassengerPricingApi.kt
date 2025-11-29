package com.example.nebeng.feature_passenger_pricing.data.remote.api

import com.example.nebeng.feature_passenger_pricing.data.remote.model.response.ReadAllPassengerPricingResponse
import com.example.nebeng.feature_passenger_pricing.data.remote.model.response.ReadByIdPassengerPricingResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface PassengerPricingApi {
    @GET("api/passenger-pricings/")
    suspend fun getAllPassengerPricing(
        @Header("Authorization") token: String
    ): Response<ReadAllPassengerPricingResponse>

    @GET("api/passenger-pricings/{id}")
    suspend fun getPassengerPricingById(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): Response<ReadByIdPassengerPricingResponse>
}