package com.example.nebeng.feature_passenger_ride_booking.data.remote.model.dto

import com.google.gson.annotations.SerializedName

data class PassengerRideBookingDto(
    @SerializedName("id") val id: Int,
    @SerializedName("booking_code") val bookingCode: String?,
    @SerializedName("passenger_ride_id") val passengerRideId: Int,
    @SerializedName("customer_id") val customerId: Int,
    @SerializedName("seats_reserved") val seatsReserved: Int,
    @SerializedName("total_price") val totalPrice: Int,
    @SerializedName("status") val status: String,
    @SerializedName("created_at") val createdAt: String,
    @SerializedName("updated_at") val updatedAt: String,

    // === Relasi (bisa null tergantung endpoint) ===
    @field:SerializedName("passenger_ride") val passengerRide: PassengerRideDto?,
    @field:SerializedName("passenger_transaction") val passengerTransaction: PassengerTransactionDto?,
    @field:SerializedName("customer") val customer: CustomerDto?,
    @field:SerializedName("driver") val driver: DriverDto?
)
