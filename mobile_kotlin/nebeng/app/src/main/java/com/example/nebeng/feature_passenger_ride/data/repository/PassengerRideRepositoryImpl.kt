package com.example.nebeng.feature_passenger_ride.data.repository

import com.example.nebeng.core.common.Result
import com.example.nebeng.feature_passenger_ride.data.remote.api.PassengerRideApi
import com.example.nebeng.feature_passenger_ride.data.remote.model.mapper.toDomain
import com.example.nebeng.feature_passenger_ride.data.remote.model.mapper.toSummary
import com.example.nebeng.feature_passenger_ride.data.remote.model.request.CreatePassengerRideRequest
import com.example.nebeng.feature_passenger_ride.data.remote.model.request.UpdatePassengerRideRequest
import com.example.nebeng.feature_passenger_ride.domain.model.PassengerRide
import com.example.nebeng.feature_passenger_ride.domain.model.PassengerRideSummary
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Singleton

//@Singleton
//class PassengerRideRepositoryImpl @Inject constructor(
//    private val api: PassengerRideApi
//): PassengerRideRepository {
//
//    override suspend fun getAllPassengerRides(
//        token: String
//    ): Result<List<PassengerRide>> {
//        return try {
//            val response = api.getAllPassengerRides(token = "Bearer $token")
//            if (response.isSuccessful) {
//                val data = response.body()?.data?.map { it.toDomain() } ?: emptyList()
//                Result.Success(data)
//            } else {
//                Result.Error(message = response.message())
//            }
//        } catch(e: Exception) {
//            Result.Error(message = e.message ?: "Gagal memuat data passenger ride")
//        }
//    }
//
//    override suspend fun getPassengerRideById(
//        token: String,
//        id: Int
//    ): Result<PassengerRide> {
//        return try {
//            val response = api.getPassengerRideById("Bearer $token", id)
//            if(response.isSuccessful) {
//                val ride = response.body()?.data?.firstOrNull()?.toDomain()
//                if (ride != null) {
//                    Result.Success(ride)
//                } else {
//                    Result.Error("Passenger ride tidak ditemukan")
//                }
//            } else {
//                Result.Error(response.message())
//            }
//        } catch(e: kotlin.Exception) {
//            Result.Error(e.message ?: "Gagal memuat PassengerRides dengan ID $id")
//        }
//    }
//
//    override suspend fun getPassengerRidesByDriverId(
//        token: String,
//        driverId: Int
//    ): Result<List<PassengerRide>> {
//        return try {
//            val response = api.getPassengerRidesByDriverId("Bearer $token", driverId)
//            if(response.isSuccessful) {
//                val rides = response.body()?.data?.map { it.toDomain() } ?: emptyList()
//                Result.Success(rides)
//            } else {
//                Result.Error("Passenger ride tidak ditemukan")
//            }
//        } catch (e: Exception) {
//            Result.Error(e.message ?:  "Gagal memuat PassengerRides dari tabel driver dengan id $driverId")
//        }
//    }
//
//    override suspend fun getPassengerRidesByStatus(
//        token: String,
//        status: String
//    ): Result<List<PassengerRide>> {
//        return try {
//            val response = api.getPassengerRidesByStatus("Bearer $token", status)
//            if(response.isSuccessful) {
//                val rides = response.body()?.data?.map { it.toDomain() } ?: emptyList()
//                Result.Success(rides)
//            } else {
//                Result.Error(response.message())
//            }
//        } catch (e: Exception) {
//            Result.Error(e.message ?: "Gagal memuat rides dengan status $status")
//        }
//    }
//
//    override suspend fun createPassengerRide(
//        token: String,
//        request: CreatePassengerRideRequest
//    ): Result<PassengerRide> {
//        return try {
//            val response = api.createPassengerRide("Bearer $token", request)
//            if(response.isSuccessful) {
//                val data = response.body()?.data
//                if(data != null) {
//                    Result.Success(data.toDomain())
//                } else {
//                    Result.Error("Data kosong dari server")
//                }
//            } else {
//                Result.Error(response.message())
//            }
//        } catch (e: Exception) {
//            Result.Error(e.message ?: "Gagal membuat ride baru")
//        }
//    }
//
//    override suspend fun updatePassengerRide(
//        token: String,
//        id: Int,
//        request: UpdatePassengerRideRequest
//    ): Result<PassengerRide> {
//        return try {
//            val response = api.updatePassengerRide("Bearer $token", id, request)
//            if(response.isSuccessful) {
//                val data = response.body()?.data
//                if(data != null) {
//                    Result.Success(data.toDomain())
//                } else {
//                    Result.Error("Data kosong dari server")
//                }
//            } else {
//                Result.Error(response.message())
//            }
//        } catch (e: Exception) {
//            Result.Error(e.message ?: "Gagal memperbarui data ride")
//        }
//    }
//
//    override suspend fun deletePassengerRide(
//        token: String,
//        id: Int
//    ): Result<String> {
//        return try {
//            val response = api.deletePassengerRide("Bearer $token", id)
//            if(response.isSuccessful) {
//                Result.Success(response.body()?.message ?: "Dihapus")
//            } else {
//                Result.Error(response.message())
//            }
//        } catch (e: Exception) {
//            Result.Error(e.message ?: "Gagal menghapus passenger ride")
//        }
//    }
//
//    override suspend fun getAllPassengerRidesSummary(
//        token: String
//    ): Result<List<PassengerRideSummary>> {
//        return try {
//            val response = api.getAllPassengerRides(token = "Bearer $token")
//            if (response.isSuccessful) {
//                val data = response.body()?.data?.map { it.toSummary() } ?: emptyList()
//                Result.Success(data)
//            } else {
//                Result.Error(message = response.message())
//            }
//        } catch(e: Exception) {
//            Result.Error(message = e.message ?: "Gagal memuat data passenger ride")
//        }
//    }
//
//    override suspend fun getPassengerRideByIdSummary(
//        token: String,
//        id: Int
//    ): Result<PassengerRideSummary> {
//        return try {
//            val response = api.getPassengerRideById("Bearer $token", id)
//            if(response.isSuccessful) {
//                val ride = response.body()?.data?.firstOrNull()?.toSummary()
//                if (ride != null) {
//                    Result.Success(ride)
//                } else {
//                    Result.Error("Passenger ride tidak ditemukan")
//                }
//            } else {
//                Result.Error(response.message())
//            }
//        } catch(e: kotlin.Exception) {
//            Result.Error(e.message ?: "Gagal memuat PassengerRides dengan ID $id")
//        }
//    }
//}

@Singleton
class PassengerRideRepositoryImpl @Inject constructor(
    private val api: PassengerRideApi
) : PassengerRideRepository {

    override suspend fun getAllPassengerRides(token: String): Flow<Result<List<PassengerRide>>> = flow {
        emit(Result.Loading)
        val res = api.getAllPassengerRides("Bearer $token")
        if (res.isSuccessful) {
            emit(Result.Success(res.body()?.data?.map { it.toDomain() } ?: emptyList()))
        } else {
            emit(Result.Error(res.message()))
        }
    }.catch { emit(Result.Error(it.message ?: "Unknown error")) }
        .flowOn(Dispatchers.IO)

    override suspend fun getPassengerRideById(token: String, id: Int): Flow<Result<PassengerRide>> = flow {
        emit(Result.Loading)
        val res = api.getPassengerRideById("Bearer $token", id)
        if (res.isSuccessful) {
            val data = res.body()?.data?.firstOrNull()?.toDomain()
            if (data != null) emit(Result.Success(data))
            else emit(Result.Error("Passenger ride tidak ditemukan"))
        } else emit(Result.Error(res.message()))
    }.catch { emit(Result.Error(it.message ?: "Unknown error")) }
        .flowOn(Dispatchers.IO)

    override suspend fun getPassengerRidesByDriverId(token: String, driverId: Int): Flow<Result<List<PassengerRide>>> =
        flow {
            emit(Result.Loading)
            val res = api.getPassengerRidesByDriverId("Bearer $token", driverId)
            if (res.isSuccessful) {
                emit(Result.Success(res.body()?.data?.map { it.toDomain() } ?: emptyList()))
            } else emit(Result.Error(res.message()))
        }.catch { emit(Result.Error(it.message ?: "Unknown error")) }
            .flowOn(Dispatchers.IO)

    override suspend fun getPassengerRidesByStatus(token: String, status: String): Flow<Result<List<PassengerRide>>> =
        flow {
            emit(Result.Loading)
            val res = api.getPassengerRidesByStatus("Bearer $token", status)
            if (res.isSuccessful) {
                emit(Result.Success(res.body()?.data?.map { it.toDomain() } ?: emptyList()))
            } else emit(Result.Error(res.message()))
        }.catch { emit(Result.Error(it.message ?: "Unknown error")) }
            .flowOn(Dispatchers.IO)

    override suspend fun createPassengerRide(token: String, request: CreatePassengerRideRequest): Flow<Result<PassengerRide>> =
        flow {
            emit(Result.Loading)
            val res = api.createPassengerRide("Bearer $token", request)
            if (res.isSuccessful) {
                val data = res.body()?.data?.toDomain()
                if (data != null) emit(Result.Success(data)) else emit(Result.Error("Data kosong dari server"))
            } else emit(Result.Error(res.message()))
        }.catch { emit(Result.Error(it.message ?: "Unknown error")) }
            .flowOn(Dispatchers.IO)

    override suspend fun updatePassengerRide(token: String, id: Int, request: UpdatePassengerRideRequest): Flow<Result<PassengerRide>> =
        flow {
            emit(Result.Loading)
            val res = api.updatePassengerRide("Bearer $token", id, request)
            if (res.isSuccessful) {
                val data = res.body()?.data?.toDomain()
                if (data != null) emit(Result.Success(data)) else emit(Result.Error("Data kosong dari server"))
            } else emit(Result.Error(res.message()))
        }.catch { emit(Result.Error(it.message ?: "Unknown error")) }
            .flowOn(Dispatchers.IO)

    override suspend fun deletePassengerRide(token: String, id: Int): Flow<Result<String>> =
        flow {
            emit(Result.Loading)
            val res = api.deletePassengerRide("Bearer $token", id)
            if (res.isSuccessful) emit(Result.Success(res.body()?.message ?: "Dihapus"))
            else emit(Result.Error(res.message()))
        }.catch { emit(Result.Error(it.message ?: "Unknown error")) }
            .flowOn(Dispatchers.IO)

    override suspend fun getAllPassengerRidesSummary(token: String): Flow<Result<List<PassengerRideSummary>>> =
        flow {
            emit(Result.Loading)
            val res = api.getAllPassengerRides("Bearer $token")
            if (res.isSuccessful) {
                emit(Result.Success(res.body()?.data?.map { it.toSummary() } ?: emptyList()))
            } else emit(Result.Error(res.message()))
        }.catch { emit(Result.Error(it.message ?: "Unknown error")) }
            .flowOn(Dispatchers.IO)

    override suspend fun getPassengerRideByIdSummary(token: String, id: Int): Flow<Result<PassengerRideSummary>> =
        flow {
            emit(Result.Loading)
            val res = api.getPassengerRideById("Bearer $token", id)
            if (res.isSuccessful) {
                val data = res.body()?.data?.firstOrNull()?.toSummary()
                if (data != null) emit(Result.Success(data))
                else emit(Result.Error("Passenger ride tidak ditemukan"))
            } else emit(Result.Error(res.message()))
        }.catch { emit(Result.Error(it.message ?: "Unknown error")) }
            .flowOn(Dispatchers.IO)
}