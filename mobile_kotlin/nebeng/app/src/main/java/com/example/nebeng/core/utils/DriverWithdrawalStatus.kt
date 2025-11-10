package com.example.nebeng.core.utils

enum class DriverWithdrawalStatus(val value: String) {
    PENDING("pending"),
    DITERIMA("diterima"),
    DITOLAK("ditolak");

    companion object {
        fun fromString(value: String): DriverWithdrawalStatus =
            entries.find { it.value.equals(value, ignoreCase = true) } ?: PENDING
    }
}