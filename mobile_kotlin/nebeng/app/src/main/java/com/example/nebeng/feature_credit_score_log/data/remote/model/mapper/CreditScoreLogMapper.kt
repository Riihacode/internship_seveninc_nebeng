package com.example.nebeng.feature_credit_score_log.data.remote.model.mapper

import com.example.nebeng.feature_credit_score_log.data.remote.model.dto.DataDto
import com.example.nebeng.feature_credit_score_log.data.remote.model.dto.DataItemDto
import com.example.nebeng.feature_credit_score_log.data.remote.model.response.DataItemReadById
import com.example.nebeng.feature_credit_score_log.domain.model.CreditScoreLog

/**
 * Mapper untuk mengonversi berbagai bentuk DTO CreditScoreLog
 * menjadi domain model CreditScoreLog.
 *
 * Semua field di-keep (tidak dikurangi),
 * dan nullable di-handle sesuai definisi domain model.
 */
object CreditScoreLogMapper {

    // 🔹 Mapping dari DataDto → CreditScoreLog (Create/Update response)
    fun fromDataDto(dto: DataDto): CreditScoreLog {
        return CreditScoreLog(
            id = dto.id,
            driverId = dto.driverId,
            actionType = dto.actionType,
            scoreChange = dto.scoreChange.toString(),
            notes = dto.notes.ifBlank { null },
            createdAt = dto.createdAt
        )
    }

    // 🔹 Mapping dari DataItemDto → CreditScoreLog (ReadAll/ReadByDriverId response)
    fun fromDataItemDto(dto: DataItemDto): CreditScoreLog {
        return CreditScoreLog(
            id = dto.id,
            driverId = dto.driverId,
            actionType = dto.actionType,
            scoreChange = dto.scoreChange.toString(),
            notes = dto.notes.ifBlank { null },
            createdAt = dto.createdAt
        )
    }

    // 🔹 Mapping dari DataItemReadById → CreditScoreLog (ReadById response)
    fun fromDataItemReadById(dto: DataItemReadById): CreditScoreLog {
        return CreditScoreLog(
            id = dto.id,
            driverId = dto.driverId,
            actionType = dto.actionType,
            scoreChange = dto.scoreChange.toString(),
            notes = dto.notes.ifBlank { null },
            createdAt = dto.createdAt
        )
    }
}