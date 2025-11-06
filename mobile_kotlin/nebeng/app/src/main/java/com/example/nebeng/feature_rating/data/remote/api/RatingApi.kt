package com.example.nebeng.feature_rating.data.remote.api

import com.example.nebeng.feature_rating.data.remote.model.request.CreateRatingRequest
import com.example.nebeng.feature_rating.data.remote.model.request.UpdateRatingRequest
import com.example.nebeng.feature_rating.data.remote.model.response.CreateRatingResponse
import com.example.nebeng.feature_rating.data.remote.model.response.DeleteRatingResponse
import com.example.nebeng.feature_rating.data.remote.model.response.ReadAllRatingResponse
import com.example.nebeng.feature_rating.data.remote.model.response.ReadByDriverIdRatingResponse
import com.example.nebeng.feature_rating.data.remote.model.response.ReadByIdRatingResponse
import com.example.nebeng.feature_rating.data.remote.model.response.UpdateRatingResponse
import retrofit2.Response
import retrofit2.http.*

interface RatingApi {
    /* ============================================================
       🔹 1. Get All Ratings
       ============================================================ */
    @GET("api/ratings")
    suspend fun getAllRatings(
        @Header("Authorization") token: String
    ): Response<ReadAllRatingResponse>

    /* ============================================================
       🔹 2. Get Rating By Id
       ============================================================ */
    @GET("api/ratings/{id}")
    suspend fun getRatingById(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): Response<ReadByIdRatingResponse>

    /* ============================================================
       🔹 3. Get Rating By Driver Id
       ============================================================ */
    @GET("api/ratings/driver/{driver_id}")
    suspend fun getRatingsByDriverId(
        @Header("Authorization") token: String,
        @Path("driver_id") driverId: Int
    ): Response<ReadByDriverIdRatingResponse>

    /* ============================================================
       🔹 4. Create Rating
       ============================================================ */
    @POST("api/ratings")
    suspend fun createRating(
        @Header("Authorization") token: String,
        @Body request: CreateRatingRequest
    ): Response<CreateRatingResponse>

    /* ============================================================
       🔹 5. Update Rating By Id
       ============================================================ */
    @PUT("api/ratings/{id}")
    suspend fun updateRating(
        @Header("Authorization") token: String,
        @Path("id") id: Int,
        @Body request: UpdateRatingRequest
    ): Response<UpdateRatingResponse>

    /* ============================================================
       🔹 6. Delete Rating By Id
       ============================================================ */
    @DELETE("api/ratings/{id}")
    suspend fun deleteRating(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): Response<DeleteRatingResponse>
}