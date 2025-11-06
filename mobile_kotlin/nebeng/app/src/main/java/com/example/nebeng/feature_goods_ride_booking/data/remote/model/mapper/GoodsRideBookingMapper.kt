package com.example.nebeng.feature_goods_ride_booking.data.remote.model.mapper

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.nebeng.core.utils.BookingStatus
import com.example.nebeng.core.utils.PublicTerminalSubtype
import com.example.nebeng.core.utils.RideStatus
import com.example.nebeng.core.utils.TransportType
import com.example.nebeng.feature_customer.domain.model.Customer
import com.example.nebeng.feature_driver.domain.model.Driver
import com.example.nebeng.feature_goods_ride_booking.data.remote.model.dto.*
import com.example.nebeng.feature_goods_ride_booking.data.remote.model.response.*
import com.example.nebeng.feature_goods_ride_booking.domain.model.*
import com.example.nebeng.feature_goods_ride.domain.model.*
import com.example.nebeng.feature_goods_transaction.domain.model.GoodsTransaction
import java.time.LocalDateTime

// -------------------- CUSTOMER --------------------
fun CustomerDto.toDomain() = Customer(
    id = id,
    userId = userId,
    fullName = fullName,
    telephone = telephone,
    fullAddress = fullAddress,
    profileImg = profileImg.toString(),
    faceImg = faceImg.toString(),
    faceWithIdImg = faceWithIdImg.toString(),
    idCardImg = idCardImg.toString(),
    idCardFullName = idCardFullname,
    idCardNumber = idCardNumber,
    idCardBirthdate = idCardBirthdate,
    verified = verified,
    creditAmount = creditAmount,
    createdAt = createdAt,
    updatedAt = updatedAt
)

// -------------------- DRIVER --------------------
fun DriverDto.toDomain() = Driver(
    id = id,
    userId = userId,
    fullName = fullName,
    telephone = telephone,
    fullAddress = fullAddress,
    profileImg = profileImg.toString(),
    faceImg = faceImg.toString(),
    faceWithIdImg = faceWithIdImg.toString(),
    idCardImg = idCardImg.toString(),
    driverLicenseImg = driverLicenseImg.toString(),
    policeClearanceCertificateImg = policeClearanceCertificateImg.toString(),
    idCardFullname = idCardFullname,
    idCardNumber = idCardNumber,
    idCardBirthdate = idCardBirthdate,
    driverLicenseNumber = driverLicenseNumber,
    driverLicenseType = driverLicenseType,
    driverLicenseExpired = driverLicenseExpired,
    policeClearanceCertificateNumber = policeClearanceCertificateNumber,
    policeClearanceCertificateFullname = policeClearanceCertificateFullname,
    policeClearanceCertificateExpired = policeClearanceCertificateExpired,
    creditScore = creditScore,
    balance = balance,
    idCardVerified = idCardVerified,
    driverLicenseVerified = driverLicenseVerified,
    policeClearanceVerified = policeClearanceVerified == 1,
    totalRating = totalRating,
    averageRating = averageRating,
    ratingCount = ratingCount,
    createdAt = createdAt,
    updatedAt = updatedAt
)

// -------------------- GOODS RIDE --------------------
@RequiresApi(Build.VERSION_CODES.O)
fun GoodsRideReadAllReadByIdDto.toDomain() = GoodsRide(
    id = id,
    driverId = driverId,
    transportType = TransportType.fromString(transportType),
    publicTerminalSubtype = PublicTerminalSubtype.fromLabel(publicTerminalSubtype),
    departureTerminalId = departureTerminalId,
    arrivalTerminalId = arrivalTerminalId,
    departureTime = LocalDateTime.parse(departureTime),
    maxWeight = maxWeight,
    weightReserved = weightReserved,
    pricePerKg = pricePerKg,
    commissionPercentage = commissionPercentage,
    rideStatus = RideStatus.fromString(rideStatus) ?: RideStatus.PENDING,
    createdAt = LocalDateTime.parse(createdAt),
    updatedAt = LocalDateTime.parse(updatedAt)
)

@RequiresApi(Build.VERSION_CODES.O)
fun GoodsRideByStatus.toDomain(): GoodsRide =
    GoodsRide(
        id = id,
        driverId = driverId,
        transportType = TransportType.fromString(transportType),
        publicTerminalSubtype = PublicTerminalSubtype.fromLabel(publicTerminalSubtype),
        departureTerminalId = departureTerminalId,
        arrivalTerminalId = arrivalTerminalId,
        departureTime = LocalDateTime.parse(departureTime),
        maxWeight = maxWeight,
        weightReserved = weightReserved,
        pricePerKg = pricePerKg,
        commissionPercentage = commissionPercentage,
        rideStatus = RideStatus.fromString(rideStatus) ?: RideStatus.PENDING,
        createdAt = LocalDateTime.parse(createdAt),
        updatedAt = LocalDateTime.parse(updatedAt)
    )

@RequiresApi(Build.VERSION_CODES.O)
fun GoodsRideReadByDriverId.toDomain(): GoodsRide =
    GoodsRide(
        id = id,
        driverId = driverId,
        transportType = TransportType.fromString(transportType),
        publicTerminalSubtype = PublicTerminalSubtype.fromLabel(publicTerminalSubtype),
        departureTerminalId = departureTerminalId,
        arrivalTerminalId = arrivalTerminalId,
        departureTime = LocalDateTime.parse(departureTime),
        maxWeight = maxWeight,
        weightReserved = weightReserved,
        pricePerKg = pricePerKg,
        commissionPercentage = commissionPercentage,
        rideStatus = RideStatus.fromString(rideStatus) ?: RideStatus.PENDING,
        createdAt = LocalDateTime.parse(createdAt),
        updatedAt = LocalDateTime.parse(updatedAt)
    )

// -------------------- TRANSACTION --------------------
fun GoodsTransactionDto.toDomain() = GoodsTransaction(
    id = id,
    goodsRideBookingId = goodsRideBookingId,
    customerId = customerId,
    transactionDate = transactionDate,
    transactionCode = transactionCode,
    paymentMethodId = paymentMethodId,
    totalAmount = totalAmount,
    paymentStatus = paymentStatus,
    paymentProofImg = paymentProofImg.toString(),
    creditUsed = creditUsed,
    createdAt = createdAt,
    updatedAt = updatedAt
)

// -------------------- CORE MAPPING --------------------
@RequiresApi(Build.VERSION_CODES.O)
fun DataCreateUpdateDto.toDomain() = GoodsRideBooking(
    id = id,
    goodsRideId = goodsRideId,
    customerId = customerId,
    itemWeight = itemWeight,
    itemDescription = itemDescription,
    itemImg = itemImg,
    totalPrice = totalPrice,
    status = BookingStatus.from(status) ?: BookingStatus.PENDING,
    createdAt = LocalDateTime.parse(createdAt),
    updatedAt = LocalDateTime.parse(updatedAt)
)

// -------------------- RESPONSE MAPPER --------------------
@RequiresApi(Build.VERSION_CODES.O)
fun DataItemReadAll.toDomain(): GoodsRideBookingFull =
    GoodsRideBookingFull(
        booking = GoodsRideBooking(
            id = id,
            goodsRideId = goodsRideId,
            customerId = customerId,
            itemWeight = itemWeight,
            itemDescription = itemDescription,
            itemImg = itemImg.toString(),
            totalPrice = totalPrice,
            status = BookingStatus.from(status) ?: BookingStatus.PENDING,
            createdAt = LocalDateTime.parse(createdAt),
            updatedAt = LocalDateTime.parse(updatedAt)
        ),
        customer = customer.toDomain(),
        goodsRide = goodsRide.toDomain(),
        goodsTransaction = goodsTransaction.toDomain(),
        bookingCode = bookingCode
    )

@RequiresApi(Build.VERSION_CODES.O)
fun DataItemReadByDriverId.toDomain(): GoodsRideBookingFull =
    GoodsRideBookingFull(
        booking = GoodsRideBooking(
            id = id,
            goodsRideId = goodsRideId,
            customerId = customerId,
            itemWeight = itemWeight,
            itemDescription = itemDescription,
            itemImg = itemImg,
            totalPrice = totalPrice,
            status = BookingStatus.from(status) ?: BookingStatus.PENDING,
            createdAt = LocalDateTime.parse(createdAt),
            updatedAt = LocalDateTime.parse(updatedAt)
        ),
        customer = customer.toDomain(),
        goodsRide = goodsRide.toDomain(),
        goodsTransaction = null,
        bookingCode = bookingCode
    )

@RequiresApi(Build.VERSION_CODES.O)
fun DataReadById.toDomain(): GoodsRideBookingFull =
    GoodsRideBookingFull(
        booking = GoodsRideBooking(
            id = id,
            goodsRideId = goodsRideId,
            customerId = customerId,
            itemWeight = itemWeight,
            itemDescription = itemDescription,
            itemImg = itemImg.toString(),
            totalPrice = totalPrice,
            status = BookingStatus.from(status) ?: BookingStatus.PENDING,
            createdAt = LocalDateTime.parse(createdAt),
            updatedAt = LocalDateTime.parse(updatedAt)
        ),
        customer = customer.toDomain(),
        goodsRide = goodsRide.toDomain(),
        goodsTransaction = goodsTransaction.toDomain(),
        bookingCode = bookingCode
    )

@RequiresApi(Build.VERSION_CODES.O)
fun DataItemReadByStatus.toDomain(): GoodsRideBookingFull =
    GoodsRideBookingFull(
        booking = GoodsRideBooking(
            id = id,
            goodsRideId = goodsRideId,
            customerId = customerId,
            itemWeight = itemWeight,
            itemDescription = itemDescription,
            itemImg = itemImg,
            totalPrice = totalPrice,
            status = BookingStatus.from(status) ?: BookingStatus.PENDING,
            createdAt = LocalDateTime.parse(createdAt),
            updatedAt = LocalDateTime.parse(updatedAt)
        ),
        customer = customer.toDomain(),
        goodsRide = goodsRide.toDomain(),
        goodsTransaction = null,
        bookingCode = bookingCode
    )

//// -------------------- DOMAIN AGGREGATE MODEL --------------------
//data class GoodsRideBookingFull(
//    val booking: GoodsRideBooking,
//    val customer: Customer,
//    val goodsRide: GoodsRide,
//    val goodsTransaction: GoodsTransaction?,
//    val bookingCode: String
//)