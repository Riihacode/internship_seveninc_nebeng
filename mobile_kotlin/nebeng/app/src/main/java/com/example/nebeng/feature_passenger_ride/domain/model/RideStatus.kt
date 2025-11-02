package com.example.nebeng.feature_passenger_ride.domain.model

enum class RideStatus(val value: String) {
    PENDING("pending"),
    DALAM_PERJALANAN("dalam_perjalanan"),
    SELESAI("selesai"),
    DIBATALKAN("dibatalkan");

    companion object {
        fun from(value: String): RideStatus? =
            entries.find { it.value.equals(value, ignoreCase = true) }
    }
}