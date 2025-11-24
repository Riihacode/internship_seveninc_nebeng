package com.example.nebeng.feature_passenger_ride_booking.data.remote.model.mapper

import com.example.nebeng.feature_customer.domain.model.Customer
import com.example.nebeng.feature_driver.domain.model.Driver
import com.example.nebeng.feature_passenger_ride.domain.model.PassengerRide
import com.example.nebeng.feature_passenger_ride_booking.data.remote.model.dto.*
import com.example.nebeng.feature_passenger_ride_booking.domain.model.*
import com.example.nebeng.feature_passenger_ride_booking.domain.model.feature_a_history_order.BookingCustomerSummary
import com.example.nebeng.feature_passenger_ride_booking.domain.model.feature_a_history_order.BookingDriverSummary
import com.example.nebeng.feature_passenger_ride_booking.domain.model.feature_a_history_order.BookingRideSummary
import com.example.nebeng.feature_passenger_ride_booking.domain.model.feature_a_history_order.BookingTransactionSummary
import com.example.nebeng.feature_passenger_ride_booking.domain.model.feature_a_history_order.PassengerRideBookingSummary
import com.example.nebeng.feature_passenger_transaction.domain.model.PassengerTransaction

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
fun DataDto.toSummary(): PassengerRideBookingSummary {
    return PassengerRideBookingSummary(
        bookingId = id,
        bookingCode = bookingCode,
        createdAt = createdAt,
        customer = BookingCustomerSummary(
            id = customer?.id ?: 0,
            fullName = customer?.fullName.orEmpty(),
            telephone = customer?.telephone.orEmpty(),
        ),
        driver = BookingDriverSummary(
            id = passengerRide?.driver?.id ?: 0,
            fullName = passengerRide?.driver?.fullName.orEmpty(),
            averageRating = passengerRide?.driver?.averageRating,
            telephone = passengerRide?.driver?.telephone.orEmpty(),
            profileImg = passengerRide?.driver?.profileImg.orEmpty()
        ),
        ride = BookingRideSummary(
            id = passengerRide?.id ?: 0,
            departureTerminalId = passengerRide?.departureTerminalId ?: 0,
            arrivalTerminalId = passengerRide?.arrivalTerminalId ?: 0,
            vehicleType = passengerRide?.vehicleType.orEmpty(),
            departureTime = passengerRide?.departureTime.orEmpty(),
            seatsReserved = seatsReserved,
            pricePerSeat = passengerRide?.pricePerSeat ?: 0,
            rideStatus = passengerRide?.rideStatus.orEmpty()
        ),
        transaction = BookingTransactionSummary(
            id = passengerTransaction?.id ?: 0,
            totalAmount = passengerTransaction?.totalAmount ?: 0,
            paymentStatus = passengerTransaction?.paymentStatus?.name,
            creditUsed = passengerTransaction?.creditUsed ?: 0
        ),
        status = status
    )
}