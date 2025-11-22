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
)
