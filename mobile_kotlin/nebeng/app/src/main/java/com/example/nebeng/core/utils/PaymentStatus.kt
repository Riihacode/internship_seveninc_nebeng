package com.example.nebeng.core.utils

enum class PaymentStatus(val value: String) {
    PENDING("Pending"),
    DITERIMA("Diterima"),
    DITOLAK("Ditolak"),
    CREDITED("Credited");

    companion object {
        fun from(value: String): PaymentStatus? =
            entries.find { it.value.equals(value, ignoreCase = true) }

    }
}