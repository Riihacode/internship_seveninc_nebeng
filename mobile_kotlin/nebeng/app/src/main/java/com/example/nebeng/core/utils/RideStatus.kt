package com.example.nebeng.core.utils

enum class RideStatus(val value: String) {
    PENDING("pending"),
    DALAM_PERJALANAN("dalam_perjalanan"),
    SELESAI("selesai"),
    DIBATALKAN("dibatalkan");

    companion object {
        fun fromString(value: String): RideStatus? =
            entries.find { it.value.equals(value, ignoreCase = true) } ?: PENDING
    }
}