package com.example.nebeng.feature_a_homepage.domain.model.nebeng_motor.customer

import com.example.nebeng.core.utils.BookingStatus
import com.example.nebeng.core.utils.RideStatus
import com.example.nebeng.core.utils.VehicleType

data class PassengerRideBookingCustomer(
    // Tabel Passenger Ride Booking
    val idBooking: Int,
    val passengerRideId: Int,
    val customerId: Int,
    val createdAtBooking: String,
    val bookingCode: String?,
    val totalPrice: Int,
    val bookingStatus: BookingStatus,
    val seatsReservedBooking: Int,
//
//    val idCustomer: Int,
//    val customerName: String,
//    val customerTelephone : String ,
//
//    val idPassengerRide: Int,
//    val driverId: Int,
//    val departureTerminalId: Int,
//    val arrivalTerminalId: Int,
//    val rideStatus: RideStatus,
//    val seatsReservedRide: Int,
//    val departureTime: String,
//    val pricePerSeat: String,
//    val vehicleType: VehicleType,
//    val driverIdRide: Int,
//
//    val idDepartureTerminal: Int,
//    val idArrivalTerminal: Int,
//
//    val idDriver: Int,
//    val fullNameDriver: String,
)