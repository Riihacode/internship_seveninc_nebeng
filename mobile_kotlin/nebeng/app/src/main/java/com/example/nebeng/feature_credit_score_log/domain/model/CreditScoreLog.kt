package com.example.nebeng.feature_credit_score_log.domain.model

data class CreditScoreLog(
    val id: Int,
    val driverId: Int,
    val actionType: String,
    val scoreChange: String,
    val notes: String?,          // nullable karena di DB bisa null
    val createdAt: String        // pakai String, atau bisa Timestamp kalau pakai DateTime API
)