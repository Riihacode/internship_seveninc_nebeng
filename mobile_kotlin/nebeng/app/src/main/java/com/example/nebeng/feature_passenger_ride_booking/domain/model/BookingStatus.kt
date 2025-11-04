package com.example.nebeng.feature_passenger_ride_booking.domain.model

enum class BookingStatus (val value: String) {
    PENDING("Pending"),
    DITERMA("Diterima"),
    DITOLAK("Ditolak");

    companion object {
        fun from(value: String?): BookingStatus? =
            entries.find { it.value.equals(value, ignoreCase = true) }
    }
}