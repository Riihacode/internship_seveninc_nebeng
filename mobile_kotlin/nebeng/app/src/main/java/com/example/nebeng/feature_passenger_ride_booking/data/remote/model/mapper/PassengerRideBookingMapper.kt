package com.example.nebeng.feature_passenger_ride_booking.data.remote.model.mapper

import com.example.nebeng.feature_customer.domain.model.Customer
import com.example.nebeng.feature_driver.domain.model.Driver
import com.example.nebeng.feature_passenger_ride.domain.model.PassengerRide
import com.example.nebeng.feature_passenger_ride_booking.data.remote.model.dto.*
import com.example.nebeng.feature_passenger_ride_booking.domain.model.*
import com.example.nebeng.feature_passenger_transaction.domain.model.PassengerTransaction

///**
// * ======================================
// *  DTO → DOMAIN MAPPER
// * ======================================
// */
//fun DataDto.toDomain(): PassengerRideBooking {
//    return PassengerRideBooking(
//        id = id,
//        bookingCode = bookingCode,
//        passengerRideId = passengerRideId,
//        customerId = customerId,
//        seatsReserved = seatsReserved,
//        totalPrice = totalPrice,
//        status = status,
//        createdAt = createdAt,
//        updatedAt = updatedAt,
//    )
//}
//
///**
// * ======================================
// *  CHILD DTO → DOMAIN
// * ======================================
// */
//fun PassengerRideDto.toDomain(): PassengerRide {
//    return PassengerRide(
//        id = id,
//        driverName = driver.fullName,
//        departureTerminalName = departureTerminalId.toString(),
//        arrivalTerminalName = arrivalTerminalId.toString(),
//        vehicleType = vehicleType,
//        pricePerSeat = pricePerSeat,
//        commissionPercentage = commissionPercentage,
//        seatsAvailable = seatsAvailable,
//        seatsReserved = seatsReserved,
//        rideStatus = rideStatus,
//        departureTime = departureTime,
//        createdAt = createdAt,
//        updatedAt = updatedAt
//        // driver ada di domain Driver
//    )
//}
//
//fun PassengerTransactionDto.toDomain(): PassengerTransaction {
//    return PassengerTransaction(
//        id = id,
//        passengerRideBookingId = passengerRideBookingId,
//        customerId = customerId,
//        paymentMethod = paymentMethodId,
//        transactionDate = transactionDate,
//        paymentStatus = paymentStatus,
//        totalAmount = totalAmount,
//        creditUsed = creditUsed,
//        paymentProofImg = paymentProofImg,
//        createdAt = createdAt,
//        updatedAt = updatedAt
//    )
//}
//
//fun CustomerDto.toDomain(): Customer {
//    return Customer(
//        id = id,
//        userId = userId,
//        fullName = fullName,
//        telephone = telephone,
//        fullAddress = fullAddress,
//
//        profileImg = profileImg,
//        verified = verified,
//        faceImg = faceImg,
//        faceWithIdImg = faceWithIdImg,
//
//        idCardImg = idCardImg,
//        idCardNumber = idCardNumber,
//        idCardFullName = idCardFullname,
//        idCardBirthdate = idCardBirthdate,
//
//        creditAmount = creditAmount,
//
//        createdAt = createdAt,
//        updatedAt = updatedAt
//    )
//}
//
//fun DriverDto.toDomain(): Driver {
//    return Driver(
//        id = id,
//        userId = userId,
//        fullName = fullName,
//        telephone = telephone,
//        fullAddress = fullAddress,
//        profileImg = profileImg,
//        faceImg = faceImg,
//        faceWithIdImg = faceWithIdImg,
//        idCardImg = idCardImg,
//        idCardFullname = idCardFullname,
//        idCardNumber = idCardNumber,
//        idCardBirthdate = idCardBirthdate,
//        driverLicenseNumber = driverLicenseNumber,
//        driverLicenseType = driverLicenseType,
//        driverLicenseExpired = driverLicenseExpired,
//        driverLicenseImg = driverLicenseImg,
//        policeClearanceCertificateNumber = policeClearanceCertificateNumber,
//        policeClearanceCertificateFullname = policeClearanceCertificateFullname,
//        policeClearanceCertificateImg = policeClearanceCertificateImg,
//        policeClearanceCertificateExpired = policeClearanceCertificateExpired,
//        idCardVerified = idCardVerified,
//        driverLicenseVerified = driverLicenseVerified,
//        policeClearanceVerified = policeClearanceVerified,
//        creditScore = creditScore,
//        balance = balance,
//        createdAt = createdAt,
//        updatedAt = updatedAt,
//
//        totalRating = totalRating,
//        ratingCount = ratingCount,
//        averageRating = averageRating
//    )
//}

/**
 * ============================================================
 * DTO ➜ DOMAIN (BOOKING SAJA / PURE ENTITY)
 * ============================================================
 */
fun DataDto.toDomain(): PassengerRideBooking {
    return PassengerRideBooking(
        id = id,
        bookingCode = bookingCode,
        passengerRideId = passengerRideId,
        customerId = customerId,
        seatsReserved = seatsReserved,
        totalPrice = totalPrice,
        status = status,
        createdAt = createdAt,
        updatedAt = updatedAt
    )
}

/**
 * ============================================================
 * CHILD DTO ➜ DOMAIN (ENTITY MURNI PER TABEL)
 * ============================================================
 */
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
    )
}

fun DriverDto.toDomain(): Driver {
    return Driver(
        id = id,
        userId = userId,
        fullName = fullName,
        telephone = telephone,
        fullAddress = fullAddress,
        profileImg = profileImg,
        faceImg = faceImg,
        faceWithIdImg = faceWithIdImg,
        idCardImg = idCardImg,
        idCardFullname = idCardFullname,
        idCardNumber = idCardNumber,
        idCardBirthdate = idCardBirthdate,
        driverLicenseNumber = driverLicenseNumber,
        driverLicenseType = driverLicenseType,
        driverLicenseExpired = driverLicenseExpired,
        driverLicenseImg = driverLicenseImg,
        policeClearanceCertificateNumber = policeClearanceCertificateNumber,
        policeClearanceCertificateFullname = policeClearanceCertificateFullname,
        policeClearanceCertificateImg = policeClearanceCertificateImg,
        policeClearanceCertificateExpired = policeClearanceCertificateExpired,
        idCardVerified = idCardVerified,
        driverLicenseVerified = driverLicenseVerified,
        policeClearanceVerified = policeClearanceVerified,
        creditScore = creditScore,
        balance = balance,
        createdAt = createdAt,
        updatedAt = updatedAt,
        totalRating = totalRating,
        ratingCount = ratingCount,
        averageRating = averageRating
    )
}

fun PassengerRideDto.toDomain(): PassengerRide {
    return PassengerRide(
        id = id,
        driverId = driverId,
        departureTerminalId = departureTerminalId,
        arrivalTerminalId = arrivalTerminalId,
        vehicleType = vehicleType,
        pricePerSeat = pricePerSeat,
        commissionPercentage = commissionPercentage,
        seatsAvailable = seatsAvailable,
        seatsReserved = seatsReserved,
        rideStatus = rideStatus,
        departureTime = departureTime,
        createdAt = createdAt,
        updatedAt = updatedAt
    )
}

fun PassengerTransactionDto.toDomain(): PassengerTransaction {
    return PassengerTransaction(
        id = id,
        passengerRideBookingId = passengerRideBookingId,
        customerId = customerId,
        paymentMethod = paymentMethodId,
        transactionDate = transactionDate,
        paymentStatus = paymentStatus,
        totalAmount = totalAmount,
        creditUsed = creditUsed,
        paymentProofImg = paymentProofImg,
        createdAt = createdAt,
        updatedAt = updatedAt
    )
}

/**
 * ============================================================
 * AGGREGATED MAPPER (NESTED JSON ➜ AGGREGATE DOMAIN)
 * ============================================================
 * Khusus dipakai di HISTORY (data lengkap)
 */
//fun DataDto.toFullDomain(): PassengerRideBookingFull {
//    return PassengerRideBookingFull(
//        booking     = this.toDomain(),
//        customer    = this.customer.toDomain(),
//        ride        = this.passengerRide.toDomain(),
//        driver      = this.passengerRide.driver.toDomain(),
//        transaction = this.passengerTransaction.toDomain()
//    )
//}
fun DataDto.toFullDomain(): PassengerRideBookingFull {
    return PassengerRideBookingFull(
        booking     = this.toDomain(),
        customer    = this.customer?.toDomain() ?: Customer.getEmpty(),
        ride        = this.passengerRide?.toDomain() ?: PassengerRide.getEmpty(),
        driver      = this.passengerRide?.driver?.toDomain() ?: Driver.getEmpty(),
        transaction = this.passengerTransaction?.toDomain() ?: PassengerTransaction.getEmpty()
    )
}
