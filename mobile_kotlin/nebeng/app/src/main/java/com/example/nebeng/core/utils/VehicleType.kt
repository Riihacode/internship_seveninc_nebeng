package com.example.nebeng.core.utils

enum class VehicleType(val value: String) {
    MOTOR("Motor"),
    MOBIL("Mobil"),
    BARANG("Barang"),
    BARANG_TRANSPORTASI_UMUM("Barang Transportasi Umum");

    companion object {
        fun fromString(value: String): VehicleType =
            entries.find { it.value.equals(value, ignoreCase = true) } ?: MOTOR
    }
}