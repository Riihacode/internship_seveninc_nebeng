package com.example.nebeng.feature_a_homepage.domain.mapper

import com.example.nebeng.core.utils.BookingStatus
import com.example.nebeng.core.utils.PaymentStatus
import com.example.nebeng.core.utils.RideStatus
import com.example.nebeng.core.utils.VehicleType
import com.example.nebeng.feature_a_homepage.domain.model.HomepageItemCustomer
import com.example.nebeng.feature_customer.domain.model.CustomerSummary
import com.example.nebeng.feature_driver.domain.model.DriverSummary
import com.example.nebeng.feature_passenger_ride.domain.model.PassengerRideSummary
import com.example.nebeng.feature_passenger_ride_booking.domain.model.feature_a_history_order.PassengerRideBookingSummary
import com.example.nebeng.feature_passenger_transaction.domain.model.PassengerTransactionSummary
import com.example.nebeng.feature_payment_method.domain.model.PaymentMethodSummary
import com.example.nebeng.feature_terminal.domain.model.TerminalSummary

fun toHomepageItemCustomer(
    booking             : PassengerRideBookingSummary,
    customer            : CustomerSummary,
    ride                : PassengerRideSummary,
    transaction         : PassengerTransactionSummary,
    payment             : PaymentMethodSummary,
    terminalDeparture   : TerminalSummary,
    terminalArrival     : TerminalSummary,
    driver              : DriverSummary
): HomepageItemCustomer {
    return HomepageItemCustomer(
        // PassengerRideBooking
        idBooking           = booking.bookingId,
        passengerRideId     = booking.ride.id,
        customerId          = booking.customer.id,
        createdAtBooking    = booking.createdAt,
        bookingCode         = booking.bookingCode,
        totalPrice          = booking.transaction.totalAmount,
        bookingStatus       = BookingStatus.fromString(booking.status),
        seatsReservedBooking= booking.ride.seatsReserved,

        // Customer
        idCustomer          = customer.id,
        customerName        = customer.fullName,
        customerTelephone   = customer.telephone,

        // Passenger Ride
        idPassengerRide     = ride.id,
        driverId            = ride.driverId,
        departureTerminalId = ride.departureTerminalId,
        arrivalTerminalId   = ride.arrivalTerminalId,
        rideStatus          = RideStatus.fromString(ride.rideStatus),
        seatsReservedRide   = ride.seatsReserved,
        seatsAvailableRide  = ride.seatsAvailable,
        departureTime       = ride.departureTime,
        pricePerSeat        = ride.pricePerSeat.toString(),
        vehicleType         = VehicleType.fromString(ride.vehicleType),
        driverIdRide        = ride.driverId,

        // Passenger Transaction
        idPassengerTransaction  = transaction.id,
        transactionDate         = transaction.transactionDate,
        paymentStatus           = transaction.paymentStatus ?: PaymentStatus.PENDING,

        // Payment Method
        idPaymentMethod     = payment.id,
        bankName            = payment.bankName,
        accountName         = payment.accountName,
        accountNumber       = payment.accountNumber,

        // Terminal — gabungan departure & arrival
        idDepartureTerminal     = terminalDeparture.id,
        idArrivalTerminal       = terminalArrival.id,
        departureTerminalName   = terminalDeparture.name,
        arrivalTerminalName     = terminalArrival.name,
        terminalFullAddress     = terminalDeparture.fullAddress,
        terminalRegency         = terminalDeparture.regencyId.toString(),
        terminalLongitude       = terminalDeparture.longitude,
        terminalLatitude        = terminalDeparture.latitude,
        publicTerminalSubtype   = terminalDeparture.publicTerminalSubtype,
        terminalType            = terminalDeparture.terminalType,

        // Driver
        idDriver        = driver.id,
        fullNameDriver  = driver.fullName,
        telephoneDriver = driver.telephone,
        profileImgDriver= driver.profileImg
    )
}