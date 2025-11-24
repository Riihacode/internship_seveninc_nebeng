package com.example.nebeng.feature_a_homepage.domain.mapper

import com.example.nebeng.core.utils.BookingStatus
import com.example.nebeng.core.utils.PaymentStatus
import com.example.nebeng.core.utils.RideStatus
import com.example.nebeng.core.utils.VehicleType
import com.example.nebeng.feature_a_homepage.domain.model.nebeng_motor.customer.CustomerCurrentCustomer
import com.example.nebeng.feature_a_homepage.domain.model.nebeng_motor.customer.DriverCustomer
import com.example.nebeng.feature_a_homepage.domain.model.nebeng_motor.customer.PassengerRideBookingCustomer
import com.example.nebeng.feature_a_homepage.domain.model.nebeng_motor.customer.PassengerRideCustomer
import com.example.nebeng.feature_a_homepage.domain.model.nebeng_motor.customer.PassengerTransactionCustomer
import com.example.nebeng.feature_a_homepage.domain.model.nebeng_motor.customer.PaymentMethodCustomer
import com.example.nebeng.feature_a_homepage.domain.model.nebeng_motor.customer.TerminalArrivalCustomer
import com.example.nebeng.feature_a_homepage.domain.model.nebeng_motor.customer.TerminalDepartureCustomer
import com.example.nebeng.feature_customer.domain.model.CustomerSummary
import com.example.nebeng.feature_driver.domain.model.DriverSummary
import com.example.nebeng.feature_passenger_ride.domain.model.PassengerRideSummary
import com.example.nebeng.feature_passenger_ride_booking.domain.model.feature_a_history_order.PassengerRideBookingSummary
import com.example.nebeng.feature_passenger_transaction.domain.model.PassengerTransactionSummary
import com.example.nebeng.feature_payment_method.domain.model.PaymentMethodSummary
import com.example.nebeng.feature_terminal.domain.model.TerminalSummary

fun PassengerRideBookingSummary.toPassengerRideBookingCustomer(): PassengerRideBookingCustomer {
    return PassengerRideBookingCustomer(
        idBooking               = bookingId,
        passengerRideId         = ride.id,
        customerId              = customer.id,
        createdAtBooking        = createdAt,
        bookingCode             = bookingCode.orEmpty(),
        totalPrice              = transaction.totalAmount,
        bookingStatus           = BookingStatus.fromString(status),
        seatsReservedBooking    = ride.seatsReserved,

        idCustomer              = customer.id,
        customerName            = customer.fullName,
        customerTelephone       = customer.telephone,

        idPassengerRide         = ride.id,
        driverId                = driver.id,
        departureTerminalId     = ride.departureTerminalId,
        arrivalTerminalId       = ride.arrivalTerminalId,
        rideStatus              = RideStatus.fromString(ride.rideStatus),
        seatsReservedRide       = ride.seatsReserved,
        departureTime           = ride.departureTime,
        pricePerSeat            = ride.pricePerSeat.toString(),
        vehicleType             = VehicleType.fromString(ride.vehicleType),
        driverIdRide            = driver.id,

        idDepartureTerminal     = ride.departureTerminalId,
        idArrivalTerminal       = ride.arrivalTerminalId,

        idDriver                = driver.id,
        fullNameDriver          = driver.fullName
    )
}

fun CustomerSummary.toCustomerCurrentCustomer(): CustomerCurrentCustomer {
    return CustomerCurrentCustomer(
        idCustomer          = id,
        customerName        = fullName,
        customerTelephone   = telephone
    )
}

fun PassengerRideSummary.toPassengerRideCustomer(): PassengerRideCustomer {
    return PassengerRideCustomer(
        idPassengerRide     = id,
        driverIdRide        = driverId,
        departureTerminalId = departureTerminalId,
        arrivalTerminalId   = arrivalTerminalId,
        rideStatus          = RideStatus.fromString(rideStatus),
        seatsReservedRide   = seatsReserved,
        seatsAvailableRide  = seatsAvailable,
        departureTime       = departureTime,
        pricePerSeat        = pricePerSeat.toString(),
        vehicleType         = VehicleType.fromString(vehicleType),
        driverId            = driverId
    )
}

fun PassengerTransactionSummary.toPassengerTransactionCustomer(): PassengerTransactionCustomer{
    return PassengerTransactionCustomer(
        idPassengerTransaction  = id,
        transactionDate         = transactionDate,
        paymentStatus           = paymentStatus ?: PaymentStatus.PENDING
    )
}

fun PaymentMethodSummary.toPaymentMethoCustomer(): PaymentMethodCustomer {
    return PaymentMethodCustomer(
        idPaymentMethod     = id,
        bankName            = bankName,
        accountName         = accountName,
        accountNumber       = accountNumber
    )
}

fun TerminalSummary.toTerminalArrivalCustomer(): TerminalArrivalCustomer {
    return TerminalArrivalCustomer(
        idArrivalTerminal       = id,
        arrivalTerminalName     = name,
        terminalFullAddress     = fullAddress,
        terminalRegencyId       = regencyId,
        terminalLongitude       = longitude,
        terminalLatitude        = latitude,
        publicTerminalSubtype   = publicTerminalSubtype,
        terminalType            = terminalType
    )
}

fun TerminalSummary.toTerminalDepartureCustomer(): TerminalDepartureCustomer {
    return TerminalDepartureCustomer(
        idDepartureTerminal     = id,
        departureTerminalName   = name,
        terminalFullAddress     = fullAddress,
        terminalRegencyId       = regencyId,
        terminalLongitude       = longitude,
        terminalLatitude        = latitude,
        publicTerminalSubtype   = publicTerminalSubtype,
        terminalType            = terminalType
    )
}

fun DriverSummary.toDriverCustomer(): DriverCustomer {
    return DriverCustomer(
        idDriver            = id,
        fullNameDriver      = fullName,
        telephoneDriver     = telephone,
        profileImgDriver    = profileImg.orEmpty()
    )
}