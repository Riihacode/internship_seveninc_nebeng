package com.example.nebeng.feature_goods_ride.data.remote.api

import com.example.nebeng.feature_goods_ride.data.remote.model.request.CreateGoodsRideRequest
import com.example.nebeng.feature_goods_ride.data.remote.model.request.UpdateGoodsRideRequest
import com.example.nebeng.feature_goods_ride.data.remote.model.response.CreateGoodsRideResponse
import com.example.nebeng.feature_goods_ride.data.remote.model.response.DeleteGoodsRideResponse
import com.example.nebeng.feature_goods_ride.data.remote.model.response.ReadAllGoodsRideResponse
import com.example.nebeng.feature_goods_ride.data.remote.model.response.ReadByIdGoodsRideResponse
import com.example.nebeng.feature_goods_ride.data.remote.model.response.UpdateGoodsRideResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface GoodsRideApi {
    // =====================================
    // 🔹 1. Create Goods Ride
    // =====================================
    @POST("api/goods-rides/")
    suspend fun createGoodsRide(
        @Header("Authorization") token: String,
        @Body request: CreateGoodsRideRequest
    ): CreateGoodsRideResponse


    // =====================================
    // 🔹 2. Get All Goods Rides
    // =====================================
    @GET("api/goods-rides/")
    suspend fun getAllGoodsRides(
        @Header("Authorization") token: String
    ): ReadAllGoodsRideResponse


    // =====================================
    // 🔹 3. Get Goods Ride By ID
    // =====================================
    @GET("api/goods-rides/{id}")
    suspend fun getGoodsRideById(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): ReadByIdGoodsRideResponse


    // =====================================
    // 🔹 4. Update Goods Ride By ID
    // =====================================
    @PUT("api/goods-rides/{id}")
    suspend fun updateGoodsRideById(
        @Header("Authorization") token: String,
        @Path("id") id: Int,
        @Body request: UpdateGoodsRideRequest
    ): UpdateGoodsRideResponse


    // =====================================
    // 🔹 5. Delete Goods Ride By ID
    // =====================================
    @DELETE("api/goods-rides/{id}")
    suspend fun deleteGoodsRideById(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): DeleteGoodsRideResponse
}