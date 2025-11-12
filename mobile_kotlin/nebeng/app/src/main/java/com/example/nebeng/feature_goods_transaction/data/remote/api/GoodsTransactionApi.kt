package com.example.nebeng.feature_goods_transaction.data.remote.api

import com.example.nebeng.feature_goods_transaction.data.remote.model.request.CreateGoodsTransactionRequest
import com.example.nebeng.feature_goods_transaction.data.remote.model.request.UpdateGoodsTransactionRequest
import com.example.nebeng.feature_goods_transaction.data.remote.model.response.CreateGoodsTransactionResponse
import com.example.nebeng.feature_goods_transaction.data.remote.model.response.DeleteGoodsTransactionResponse
import com.example.nebeng.feature_goods_transaction.data.remote.model.response.ReadAllGoodsTransactionResponse
import com.example.nebeng.feature_goods_transaction.data.remote.model.response.ReadByIdGoodsTransactionResponse
import com.example.nebeng.feature_goods_transaction.data.remote.model.response.UpdateGoodsTransactionResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface GoodsTransactionApi {
    // 🔹 1. Create Goods Transaction
    @POST("api/goods-transactions/")
    suspend fun createGoodsTransaction(
        @Header("Authorization") token: String,
        @Body request: CreateGoodsTransactionRequest
    ): CreateGoodsTransactionResponse


    // 🔹 2. Get All Goods Transactions
    @GET("api/goods-transactions/")
    suspend fun getAllGoodsTransactions(
        @Header("Authorization") token: String
    ): ReadAllGoodsTransactionResponse


    // 🔹 3. Get Goods Transaction by Id
    @GET("api/goods-transactions/{id}")
    suspend fun getGoodsTransactionById(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): ReadByIdGoodsTransactionResponse


    // 🔹 4. Update Goods Transaction by Id
    @PUT("api/goods-transactions/{id}")
    suspend fun updateGoodsTransactionById(
        @Header("Authorization") token: String,
        @Path("id") id: Int,
        @Body request: UpdateGoodsTransactionRequest
    ): UpdateGoodsTransactionResponse


    // 🔹 5. Delete Goods Transaction by Id
    @DELETE("api/goods-transactions/{id}")
    suspend fun deleteGoodsTransactionById(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): DeleteGoodsTransactionResponse
}