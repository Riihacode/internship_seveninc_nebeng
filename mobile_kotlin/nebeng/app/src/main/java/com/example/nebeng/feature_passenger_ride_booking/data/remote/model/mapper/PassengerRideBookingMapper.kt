package com.example.nebeng.feature_passenger_ride_booking.data.remote.model.mapper


import com.example.nebeng.feature_passenger_ride.domain.model.PassengerRide
import com.example.nebeng.feature_passenger_ride_booking.data.remote.model.dto.*
import com.example.nebeng.feature_passenger_ride_booking.domain.model.*

/**
 * ======================================
 *  DTO → DOMAIN MAPPER
 * ======================================
 */
fun PassengerRideBookingDto.toDomain(): PassengerRideBooking {
    return PassengerRideBooking(
        id = id,
        bookingCode = bookingCode,
        passengerRideId = passengerRideId,
        customerId = customerId,
        seatsReserved = seatsReserved,
        totalPrice = totalPrice,
        status = status,
        createdAt = createdAt,
        updatedAt = updatedAt,
        passengerRide = passengerRide?.toDomain(),
        passengerTransaction = passengerTransaction?.toDomain(),
        customer = customer?.toDomain(),
        driver = driver?.toDomain()
    )
}

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
        updatedAt = updatedAt,
        passengerRide = passengerRide?.toDomain(),
        passengerTransaction = passengerTransaction?.toDomain(),
        customer = customer?.toDomain(),
        driver = passengerRide?.driver?.toDomain() // driver disematkan di PassengerRide
    )
}

/**
 * ======================================
 *  CHILD DTO → DOMAIN
 * ======================================
 */
fun PassengerRideDto.toDomain(): PassengerRide {
    return PassengerRide(
        id = id,
        driverName = driver.fullName,
        departureTerminalName = departureTerminalId.toString(),
        arrivalTerminalName = arrivalTerminalId.toString(),
        vehicleType = vehicleType,
        pricePerSeat = pricePerSeat,
        commissionPercentage = commissionPercentage,
        seatsAvailable = seatsAvailable,
        seatsReserved = seatsReserved,
        rideStatus = rideStatus,
        departureTime = departureTime,
        createdAt = createdAt,
        updatedAt = updatedAt
        // driver ada di domain Driver
    )
}

fun PassengerTransactionDto.toDomain(): PassengerTransaction {
    return PassengerTransaction(
        id = id,
        passengerRideBookingId = passengerRideBookingId,
        customerId = customerId,
        paymentMethodId = paymentMethodId,
        transactionDate = transactionDate,
        paymentStatus = paymentStatus,
        totalAmount = totalAmount,
        creditUsed = creditUsed,
        paymentProofImg = paymentProofImg,
        createdAt = createdAt,
        updatedAt = updatedAt
    )
}

fun CustomerDto.toDomain(): Customer {
    return Customer(
        id = id,
        userId = userId,
        fullName = fullName,
        telephone = telephone,
        fullAddress = fullAddress,
        verified = verified,
        creditAmount = creditAmount,
        profileImg = profileImg,
        faceImg = faceImg,
        faceWithIdImg = faceWithIdImg,
        idCardImg = idCardImg,
        idCardFullname = idCardFullname,
        idCardNumber = idCardNumber,
        idCardBirthdate = idCardBirthdate,
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
        totalRating = totalRating,
        averageRating = averageRating,
        ratingCount = ratingCount,
        createdAt = createdAt,
        updatedAt = updatedAt
    )
}