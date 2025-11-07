package com.example.nebeng.feature_payment_method.data.remote.model.mapper

import com.example.nebeng.feature_payment_method.data.remote.model.dto.DataDto
import com.example.nebeng.feature_payment_method.data.remote.model.request.CreatePaymentMethodRequest
import com.example.nebeng.feature_payment_method.data.remote.model.request.UpdatePaymentMethodRequest
import com.example.nebeng.feature_payment_method.data.remote.model.response.DataItem
import com.example.nebeng.feature_payment_method.domain.model.PaymentMethod
import com.example.nebeng.core.utils.PaymentStatus

/* ============================================================
   🔹 Mapper untuk DataDto → Domain (dipakai di create/update/readById)
   ============================================================ */
fun DataDto.toDomain(): PaymentMethod {
    return PaymentMethod(
        id = id,
        passengerRideBookingId = 0,   // API belum kirimkan
        customerId = 0,               // API belum kirimkan
        totalAmount = 0,              // API belum kirimkan
        paymentMethod = 0,            // API belum kirimkan
        paymentStatus = PaymentStatus.PENDING,
        creditUsed = 0,               // API belum kirimkan
        transactionDate = createdAt,  // gunakan createdAt sementara
        createdAt = createdAt,
        updatedAt = updatedAt
    )
}

/* ============================================================
   🔹 Mapper untuk DataItem → Domain (dipakai di readAll)
   ============================================================ */
fun DataItem.toDomain(): PaymentMethod {
    return PaymentMethod(
        id = id,
        passengerRideBookingId = 0,
        customerId = 0,
        totalAmount = 0,
        paymentMethod = 0,
        paymentStatus = PaymentStatus.PENDING,
        creditUsed = 0,
        transactionDate = createdAt,
        createdAt = createdAt,
        updatedAt = updatedAt
    )
}

/* ============================================================
   🔹 Mapper untuk Domain → Create Request (dipakai di create)
   ============================================================ */
fun PaymentMethod.toCreateRequest(): CreatePaymentMethodRequest {
    return CreatePaymentMethodRequest(
        accountNumber = "", // belum terdefinisi di domain
        accountName = "",
        bankName = ""
    )
}

/* ============================================================
   🔹 Mapper untuk Domain → Update Request (dipakai di update)
   ============================================================ */
fun PaymentMethod.toUpdateRequest(): UpdatePaymentMethodRequest {
    return UpdatePaymentMethodRequest(
        accountNumber = "",
        accountName = "",
        bankName = ""
    )
}