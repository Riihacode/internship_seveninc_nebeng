package com.example.nebeng.feature_passenger_transaction.data.remote.model.mapper.updated

import com.example.nebeng.feature_passenger_transaction.data.remote.model.dto.updated.DataDto
import com.example.nebeng.feature_passenger_transaction.domain.model.updated.PassengerTransaction

fun DataDto.toPassengerTransactionUpdated(): PassengerTransaction {
    return PassengerTransaction(
        id = id,
        transactionDate = transactionDate,
        transactionCode = transactionCode,
        midtransTransactionId = midtransTransactionId,
        paymentStatus = paymentStatus,
        createdAt = createdAt,
        paymentProofImg = paymentProofImg,
        creditUsed = creditUsed,
        paymentMethodId = paymentMethodId,
        paymentType = paymentType,
        updatedAt = updatedAt,
        totalAmount = totalAmount,
        midtransOrderId = midtransOrderId,
        paymentExpiredAt = paymentExpiredAt,
        passengerRideBookingId = passengerRideBookingId,
        vaNumber = vaNumber,
        customerId = customerId,
    )
}