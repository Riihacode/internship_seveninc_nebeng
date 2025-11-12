package com.example.nebeng.feature_goods_transaction.data.remote.model.mapper

import com.example.nebeng.core.utils.PaymentStatus
import com.example.nebeng.feature_goods_transaction.data.remote.model.dto.DataDto
import com.example.nebeng.feature_goods_transaction.data.remote.model.response.CreateGoodsTransactionResponse
import com.example.nebeng.feature_goods_transaction.data.remote.model.response.ReadByIdGoodsTransactionResponse
import com.example.nebeng.feature_goods_transaction.domain.model.GoodsTransaction

/**
 * Mapper untuk konversi dari DTO / Response API menjadi domain model GoodsTransaction
 */
object GoodsTransactionMapper {

    // 🔹 Map dari Create Response → Domain
    fun fromCreateResponse(response: CreateGoodsTransactionResponse): GoodsTransaction {
        val d = response.data
        return GoodsTransaction(
            id = d.id,
            goodsRideBookingId = d.goodsRideBookingId,
            customerId = d.customerId,
            totalAmount = d.totalAmount,
            paymentMethodId = d.paymentMethodId,
            paymentProofImg = d.paymentProofImg,
            paymentStatus = mapPaymentStatus(d.paymentStatus),
            creditUsed = d.creditUsed,
            transactionDate = d.transactionDate,
            createdAt = d.createdAt,
            updatedAt = d.updatedAt,
            transactionCode = d.transactionCode
        )
    }

    // 🔹 Map dari ReadById Response → Domain
    fun fromReadByIdResponse(dto: ReadByIdGoodsTransactionResponse): GoodsTransaction {
        return GoodsTransaction(
            id = dto.id,
            goodsRideBookingId = dto.goodsRideBookingId,
            customerId = dto.customerId,
            totalAmount = dto.totalAmount,
            paymentMethodId = dto.paymentMethodId,
            paymentProofImg = dto.paymentProofImg,
            paymentStatus = mapPaymentStatus(dto.paymentStatus),
            creditUsed = dto.creditUsed,
            transactionDate = dto.transactionDate,
            createdAt = dto.createdAt,
            updatedAt = dto.updatedAt,
            transactionCode = dto.transactionCode
        )
    }

    // 🔹 Map dari DataDto (umum untuk list / update) → Domain
    fun fromDataDto(dto: DataDto): GoodsTransaction {
        return GoodsTransaction(
            id = dto.id,
            goodsRideBookingId = dto.goodsRideBookingId,
            customerId = dto.customerId,
            totalAmount = dto.totalAmount,
            paymentMethodId = dto.paymentMethodId,
            paymentProofImg = dto.paymentProofImg,
            paymentStatus = mapPaymentStatus(dto.paymentStatus),
            creditUsed = dto.creditUsed,
            transactionDate = dto.transactionDate,
            createdAt = dto.createdAt,
            updatedAt = dto.updatedAt,
            transactionCode = dto.transactionCode
        )
    }

    // 🔹 Utility: convert string → enum
    private fun mapPaymentStatus(status: String): PaymentStatus {
        return when (status.uppercase()) {
            "PENDING" -> PaymentStatus.PENDING
            "DITERIMA" -> PaymentStatus.DITERIMA
            "DITOLAK" -> PaymentStatus.DITOLAK
            "CREDITED" -> PaymentStatus.CREDITED
            else -> PaymentStatus.PENDING
        }
    }
}