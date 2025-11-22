package com.example.nebeng.core.utils

enum class BookingStatus(val value: String) {
    PENDING("Pending"),
    DITERIMA("Diterima"),
    DITOLAK("Ditolak");

    companion object {
        fun fromString(value: String?): BookingStatus =
            entries.find { it.value.equals(value, ignoreCase = true) } ?: PENDING
    }
}