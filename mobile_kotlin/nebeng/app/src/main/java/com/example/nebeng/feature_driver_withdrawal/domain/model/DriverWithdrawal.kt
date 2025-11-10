package com.example.nebeng.feature_driver_withdrawal.domain.model

import com.example.nebeng.core.utils.DriverWithdrawalStatus

data class DriverWithdrawal(
    val id: Int,
    val driverId: Int,
    val withdrawalAmount: Int,
    val bankName: String,
    val accountName: String,
    val status: DriverWithdrawalStatus,
    val rejectedReason: String,
    val createdAt: String,
    val updatedAt: String
)
