package com.example.nebeng.feature_a_homepage.domain.model

import com.example.nebeng.core.utils.BookingStatus
import com.example.nebeng.core.utils.PaymentStatus
import com.example.nebeng.core.utils.PublicTerminalSubtype
import com.example.nebeng.core.utils.RideStatus
import com.example.nebeng.core.utils.TerminalType
import com.example.nebeng.core.utils.VehicleType

data class HomepageItemCustomer(
    // Tabel Passenger Ride Booking
    val idBooking: Int,
    val passengerRideId: Int,
    val customerId: Int,
    val createdAtBooking: String,
    val bookingCode: String?,
    val totalPrice: Int,
    val bookingStatus: BookingStatus,
    val seatsReservedBooking: Int,

    // Tabel Customer
    val idCustomer: Int,
    val customerName: String,
    val customerTelephone: String,

    // Tabel PassengerRide
    val idPassengerRide: Int,
    val driverId: Int,
    val departureTerminalId: Int,
    val arrivalTerminalId: Int,
    val rideStatus: RideStatus,
    val seatsReservedRide: Int,
    val seatsAvailableRide: Int,
    val departureTime: String,
    val pricePerSeat: String,
    val vehicleType: VehicleType,
    val driverIdRide: Int,

    // Tabel Passenger Transaction
    val idPassengerTransaction: Int,
    val transactionDate: String,
    val paymentStatus: PaymentStatus,

    // Tabel Payment Method
    val idPaymentMethod: Int,
    val bankName: String,
    val accountName:String,
    val accountNumber: String,

    // Tabel Terminal
    val idDepartureTerminal: Int,
    val idArrivalTerminal: Int,
    val departureTerminalName: String,
    val arrivalTerminalName: String,
    val terminalFullAddress: String,
    val terminalRegency: String,
    val terminalLongitude: Double,
    val terminalLatitude: Double,
    val publicTerminalSubtype: PublicTerminalSubtype,
    val terminalType: TerminalType,

    // Tabel Driver
    val idDriver: Int,
    val fullNameDriver: String,
    val telephoneDriver: String,
    val profileImgDriver: String?,
) {
    companion object {
        fun empty() = HomepageItemCustomer(
            idBooking = 0,
            passengerRideId = 0,
            customerId = 0,
            createdAtBooking = "",
            bookingCode = "",
            totalPrice = 0,
            bookingStatus = BookingStatus.PENDING,
            seatsReservedBooking = 0,

            idCustomer = 0,
            customerName = "",
            customerTelephone = "",

            idPassengerRide = 0,
            driverId = 0,
            departureTerminalId = 0,
            arrivalTerminalId = 0,
            rideStatus = RideStatus.PENDING,
            seatsReservedRide = 0,
            seatsAvailableRide = 0,
            departureTime = "",
            pricePerSeat = "",
            vehicleType = VehicleType.MOTOR,
            driverIdRide = 0,

            idPassengerTransaction = 0,
            transactionDate = "",
            paymentStatus = PaymentStatus.PENDING,

            idPaymentMethod = 0,
            bankName = "",
            accountName = "",
            accountNumber = "",

            idDepartureTerminal = 0,
            idArrivalTerminal = 0,
            departureTerminalName = "",
            arrivalTerminalName = "",
            terminalFullAddress = "",
            terminalRegency = "",
            terminalLongitude = 0.0,
            terminalLatitude = 0.0,
            publicTerminalSubtype = PublicTerminalSubtype.TERMINAL_BIS,
            terminalType = TerminalType.PUBLIC,

            idDriver = 0,
            fullNameDriver = "",
            telephoneDriver = "",
            profileImgDriver = "",
        )
    }
}
