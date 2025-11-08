package com.example.nebeng.core.utils

enum class VehicleType(val value: String) {
    MOTOR("Motor"),
    MOBIL("Mobil");

    companion object {
        fun fromString(value: String): VehicleType =
            entries.find { it.value.equals(value, ignoreCase = true) } ?: MOTOR
    }
}