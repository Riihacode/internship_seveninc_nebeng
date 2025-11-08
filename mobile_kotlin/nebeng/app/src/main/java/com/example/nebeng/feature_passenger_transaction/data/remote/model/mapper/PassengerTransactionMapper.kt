package com.example.nebeng.feature_passenger_transaction.data.remote.model.mapper

import com.example.nebeng.feature_customer.domain.model.Customer
import com.example.nebeng.feature_passenger_ride_booking.data.remote.model.mapper.toDomain
import com.example.nebeng.feature_passenger_ride_booking.domain.model.PassengerRideBooking
import com.example.nebeng.feature_passenger_transaction.data.remote.model.dto.CustomerDto
import com.example.nebeng.feature_passenger_transaction.data.remote.model.dto.PassengerRideBookingDto
import com.example.nebeng.feature_passenger_transaction.data.remote.model.dto.PaymentMethodDto
import com.example.nebeng.feature_passenger_transaction.data.remote.model.response.*
import com.example.nebeng.feature_passenger_transaction.domain.model.PassengerTransaction
import com.example.nebeng.feature_payment_method.domain.model.PaymentMethod
import com.example.nebeng.core.utils.PaymentStatus

///* ============================================================
//   🔹 Mapper untuk ReadAll / ReadById / ReadByPassengerRideBookingId
//   ============================================================ */
//fun DataItem.toDomain(): PassengerTransaction {
//    return PassengerTransaction(
//        id = id,
//        passengerRideBookingId = passengerRideBookingId,
//        customerId = customerId,
//        totalAmount = totalAmount,
//        paymentMethod = paymentMethodId,
//        paymentProofImg = paymentProofImg,
//        paymentStatus = PaymentStatus.from(paymentStatus) ?: PaymentStatus.PENDING,
//        creditUsed = creditUsed,
//        transactionDate = transactionDate,
//        createdAt = createdAt,
//        updatedAt = updatedAt
//    )
//}
//
//fun DataByIdPassengerTransaction.toDomain(): PassengerTransaction {
//    return PassengerTransaction(
//        id = id,
//        passengerRideBookingId = passengerRideBookingId,
//        customerId = customerId,
//        totalAmount = totalAmount,
//        paymentMethod = paymentMethodId,
//        paymentProofImg = paymentProofImg,
//        paymentStatus = PaymentStatus.from(paymentStatus) ?: PaymentStatus.PENDING,
//        creditUsed = creditUsed,
//        transactionDate = transactionDate,
//        createdAt = createdAt,
//        updatedAt = updatedAt
//    )
//}
//
//fun DataByPassengerRideId.toDomain(): PassengerTransaction {
//    return PassengerTransaction(
//        id = id,
//        passengerRideBookingId = passengerRideBookingId,
//        customerId = customerId,
//        totalAmount = totalAmount,
//        paymentMethod = paymentMethodId,
//        paymentProofImg = paymentProofImg,
//        paymentStatus = PaymentStatus.from(paymentStatus) ?: PaymentStatus.PENDING,
//        creditUsed = creditUsed,
//        transactionDate = transactionDate,
//        createdAt = createdAt,
//        updatedAt = updatedAt
//    )
//}
//
///* ============================================================
//   🔹 Mapper untuk Create / Update / PatchStatus Response
//   ============================================================ */
//fun DataCreatePassengerTransaction.toDomain(): PassengerTransaction {
//    return PassengerTransaction(
//        id = id,
//        passengerRideBookingId = passengerRideBookingId,
//        customerId = customerId,
//        totalAmount = totalAmount,
//        paymentMethod = paymentMethodId,
//        paymentProofImg = paymentProofImg,
//        paymentStatus = PaymentStatus.from(paymentStatus) ?: PaymentStatus.PENDING,
//        creditUsed = creditUsed,
//        transactionDate = transactionDate,
//        createdAt = createdAt,
//        updatedAt = updatedAt
//    )
//}
//
//fun DataUpdatePassengerTransaction.toDomain(): PassengerTransaction {
//    return PassengerTransaction(
//        id = id,
//        passengerRideBookingId = passengerRideBookingId,
//        customerId = customerId,
//        totalAmount = totalAmount,
//        paymentMethod = paymentMethodId,
//        paymentProofImg = paymentProofImg,
//        paymentStatus = PaymentStatus.from(paymentStatus) ?: PaymentStatus.PENDING,
//        creditUsed = creditUsed,
//        transactionDate = transactionDate,
//        createdAt = createdAt,
//        updatedAt = updatedAt
//    )
//}
//
//fun DataPatchStatusPassengerTransaction.toDomain(): PassengerTransaction {
//    return PassengerTransaction(
//        id = id,
//        passengerRideBookingId = passengerRideBookingId,
//        customerId = customerId,
//        totalAmount = totalAmount,
//        paymentMethod = paymentMethodId,
//        paymentProofImg = paymentProofImg,
//        paymentStatus = PaymentStatus.from(paymentStatus) ?: PaymentStatus.PENDING,
//        creditUsed = creditUsed,
//        transactionDate = transactionDate,
//        createdAt = createdAt,
//        updatedAt = updatedAt
//    )
//}
/* ============================================================
   🔹 DTO → Domain untuk relasi nested (Customer, PaymentMethod, Booking)
   ============================================================ */
fun CustomerDto.toDomain(): Customer {
    return Customer(
        id = id,
        userId = userId,
        fullName = fullName,
        telephone = telephone,
        fullAddress = fullAddress,
        profileImg = profileImg,
        verified = verified,
        faceImg = faceImg,
        faceWithIdImg = faceWithIdImg,
        idCardImg = idCardImg,
        idCardNumber = idCardNumber,
        idCardFullName = idCardFullname,
        idCardBirthdate = idCardBirthdate,
        creditAmount = creditAmount,
        createdAt = createdAt,
        updatedAt = updatedAt
//        user = null
    )
}

fun PaymentMethodDto.toDomain(): PaymentMethod {
    return PaymentMethod(
        id = id,
        passengerRideBookingId = 0, // placeholder jika tidak ada di response
        customerId = 0,
        totalAmount = 0,
        paymentMethod = id,
        paymentStatus = PaymentStatus.PENDING,
        creditUsed = 0,
        transactionDate = createdAt,
        createdAt = createdAt,
        updatedAt = updatedAt
    )
}

fun PassengerRideBookingDto.toDomain(): PassengerRideBooking {
    return PassengerRideBooking(
        id = id,
        passengerRideId = passengerRideId,
        customerId = customerId,
        bookingCode = bookingCode,
        totalPrice = totalPrice,
        seatsReserved = seatsReserved,
        status = status,
        createdAt = createdAt,
        updatedAt = updatedAt,
        passengerRide = null,
        passengerTransaction = null,
        customer = null,
        driver = null
    )
}

/* ============================================================
   🔹 Mapper utama PassengerTransaction DTO → Domain
   ============================================================ */
fun DataItem.toDomain(): PassengerTransaction {
    return PassengerTransaction(
        id = id,
        passengerRideBookingId = passengerRideBookingId,
        customerId = customerId,
        totalAmount = totalAmount,
        paymentMethod = paymentMethodId,
        paymentProofImg = paymentProofImg,
        paymentStatus = PaymentStatus.fromString(paymentStatus) ?: PaymentStatus.PENDING,
        creditUsed = creditUsed,
        transactionDate = transactionDate,
        createdAt = createdAt,
        updatedAt = updatedAt
    ).copy(
        // relasi opsional
        customer = customer.toDomain(),
        paymentMethodDetail = paymentMethod.toDomain(),
        booking = passengerRideBooking.toDomain()
    )
}

fun DataByIdPassengerTransaction.toDomain(): PassengerTransaction {
    return PassengerTransaction(
        id = id,
        passengerRideBookingId = passengerRideBookingId,
        customerId = customerId,
        totalAmount = totalAmount,
        paymentMethod = paymentMethodId,
        paymentProofImg = paymentProofImg,
        paymentStatus = PaymentStatus.fromString(paymentStatus) ?: PaymentStatus.PENDING,
        creditUsed = creditUsed,
        transactionDate = transactionDate,
        createdAt = createdAt,
        updatedAt = updatedAt
    ).copy(
        customer = customer.toDomain(),
        paymentMethodDetail = paymentMethod.toDomain(),
        booking = passengerRideBooking.toDomain()
    )
}

fun DataByPassengerRideId.toDomain(): PassengerTransaction {
    return PassengerTransaction(
        id = id,
        passengerRideBookingId = passengerRideBookingId,
        customerId = customerId,
        totalAmount = totalAmount,
        paymentMethod = paymentMethodId,
        paymentProofImg = paymentProofImg,
        paymentStatus = PaymentStatus.fromString(paymentStatus) ?: PaymentStatus.PENDING,
        creditUsed = creditUsed,
        transactionDate = transactionDate,
        createdAt = createdAt,
        updatedAt = updatedAt
    ).copy(
        customer = customer.toDomain(),
        paymentMethodDetail = paymentMethod.toDomain()
    )
}

/* ============================================================
   🔹 Mapper untuk Create / Update / PatchStatus Response
   ============================================================ */
fun DataCreatePassengerTransaction.toDomain(): PassengerTransaction {
    return PassengerTransaction(
        id = id,
        passengerRideBookingId = passengerRideBookingId,
        customerId = customerId,
        totalAmount = totalAmount,
        paymentMethod = paymentMethodId,
        paymentProofImg = paymentProofImg,
        paymentStatus = PaymentStatus.fromString(paymentStatus) ?: PaymentStatus.PENDING,
        creditUsed = creditUsed,
        transactionDate = transactionDate,
        createdAt = createdAt,
        updatedAt = updatedAt
    )
}

fun DataUpdatePassengerTransaction.toDomain(): PassengerTransaction {
    return PassengerTransaction(
        id = id,
        passengerRideBookingId = passengerRideBookingId,
        customerId = customerId,
        totalAmount = totalAmount,
        paymentMethod = paymentMethodId,
        paymentProofImg = paymentProofImg,
        paymentStatus = PaymentStatus.fromString(paymentStatus) ?: PaymentStatus.PENDING,
        creditUsed = creditUsed,
        transactionDate = transactionDate,
        createdAt = createdAt,
        updatedAt = updatedAt
    )
}

fun DataPatchStatusPassengerTransaction.toDomain(): PassengerTransaction {
    return PassengerTransaction(
        id = id,
        passengerRideBookingId = passengerRideBookingId,
        customerId = customerId,
        totalAmount = totalAmount,
        paymentMethod = paymentMethodId,
        paymentProofImg = paymentProofImg,
        paymentStatus = PaymentStatus.fromString(paymentStatus) ?: PaymentStatus.PENDING,
        creditUsed = creditUsed,
        transactionDate = transactionDate,
        createdAt = createdAt,
        updatedAt = updatedAt
    )
}