package com.example.nebeng.feature_driver_withdrawal.data.remote.model.mapper

import com.example.nebeng.core.utils.DriverWithdrawalStatus
import com.example.nebeng.feature_driver_withdrawal.data.remote.model.dto.DataDto
import com.example.nebeng.feature_driver_withdrawal.data.remote.model.response.DataUpdate
import com.example.nebeng.feature_driver_withdrawal.domain.model.DriverWithdrawal

/**
 * Mapper untuk mengonversi berbagai bentuk DTO DriverWithdrawal
 * menjadi domain model DriverWithdrawal. Semua atribut disertakan
 * tanpa ada yang dikurangi.
 */
object DriverWithdrawalMapper {

    // 🔹 Mapping dari DataDto (Create / Read / Response umum)
    fun fromDataDto(dto: DataDto): DriverWithdrawal {
        return DriverWithdrawal(
            id = dto.id,
            driverId = dto.driverId,
            withdrawalAmount = dto.withdrawalAmount,
            bankName = dto.bankName,
            accountName = dto.accountName,
            status = DriverWithdrawalStatus.fromString(dto.status),
            rejectedReason = dto.rejectedReason,
            createdAt = dto.createdAt,
            updatedAt = dto.updatedAt
        )
    }

    // 🔹 Mapping dari DataUpdate (khusus response Update)
    fun fromDataUpdate(dto: DataUpdate): DriverWithdrawal {
        return DriverWithdrawal(
            id = dto.id,
            driverId = dto.driverId,
            withdrawalAmount = dto.withdrawalAmount,
            bankName = dto.bankName,
            accountName = dto.accountName,
            status = DriverWithdrawalStatus.fromString(dto.status),
            rejectedReason = dto.rejectedReason,
            createdAt = dto.createdAt,
            updatedAt = dto.updatedAt
        )
    }

    // 🔹 Mapping dari List<DataDto> (misal ReadAll / ReadByDriverId / ReadByStatus)
    fun fromDataDtoList(list: List<DataDto>): List<DriverWithdrawal> {
        return list.map { fromDataDto(it) }
    }
}