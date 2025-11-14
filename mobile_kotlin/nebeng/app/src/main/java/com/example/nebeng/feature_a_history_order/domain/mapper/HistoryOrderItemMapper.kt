package com.example.nebeng.feature_a_history_order.domain.mapper

import android.util.Log
import com.example.nebeng.core.utils.BookingStatus
import com.example.nebeng.core.utils.RideStatus
import com.example.nebeng.core.utils.VehicleType
import com.example.nebeng.feature_a_history_order.domain.model.HistoryOrderItem
import com.example.nebeng.feature_a_history_order.presentation.support_for_present.model.HistoryItemData
import com.example.nebeng.feature_passenger_ride_booking.domain.model.PassengerRideBookingFull

/**
 * ============================================================
 * MAPPER: Domain ➜ Presentation
 * ============================================================
 * - Mengubah domain (PassengerRideBookingFull)
 *   menjadi data siap tampil (HistoryItemData)
 * - Dipakai oleh HistoryOrderViewModel sebelum update UiState
 */
//fun PassengerRideBookingFull.toHistoryItemData(): HistoryItemData {
//    return HistoryItemData(
//        code = booking.bookingCode ?: "-",
//        title = when (ride.vehicleType.lowercase()) {
//            "mobil" -> "Nebeng Mobil"
//            "motor" -> "Nebeng Motor"
//            else -> "Nebeng"
//        },
//        driverName = ride.driverName, // sudah di PassengerRide domain
//        departure = ride.departureTerminalName,
//        arrival = ride.arrivalTerminalName,
//        seats = booking.seatsReserved,
//        price = booking.totalPrice,
//        status = booking.status
//    )
//}
//fun PassengerRideBookingFull.toHistoryItemData(
//    driverName: String? = null,
//    departureTerminalName: String? = null,
//    arrivalTerminalName: String? = null
//): HistoryItemData {
//    return HistoryItemData(
//        code = booking.bookingCode ?: "-",
//        title = if (ride.vehicleType.lowercase() == "mobil") "Nebeng Mobil" else "Nebeng Motor",
//        driverName = driverName ?: "Driver #${ride.driverId}",
//        departure = departureTerminalName ?: "Terminal #${ride.departureTerminalId}",
//        arrival = arrivalTerminalName ?: "Terminal #${ride.arrivalTerminalId}",
//        seats = booking.seatsReserved,
//        price = booking.totalPrice,
//        status = booking.status
//    )
//}



//object HistoryOrderItemMapper {
//
//    fun fromFull(full: PassengerRideBookingFull): HistoryOrderItem {
//        val booking = full.booking
//        val ride = full.ride
//        val driver = full.driver
//
//        return HistoryOrderItem(
//            bookingId = booking.id,
//            bookingCode = booking.bookingCode,
//            createdAt = booking.createdAt,
//            departureTerminalId = ride.departureTerminalId,
//            arrivalTerminalId = ride.arrivalTerminalId,
//            seatsReserved = booking.seatsReserved,
//            totalPrice = booking.totalPrice,
//            bookingStatus = BookingStatus.fromString(booking.status),
//            vehicleType = VehicleType.fromString(ride.vehicleType),
////            rideStatus = ride.rideStatus,
//            rideStatus = RideStatus.fromString(ride.rideStatus),
//            driverName = driver.fullName
//        )
//    }
//
//    // HistoryItemDataMapper.kt
//    fun HistoryItemData.toDomain(): HistoryOrderItem {
//        return HistoryOrderItem(
//            bookingId = this.id.toIntOrNull() ?: 0,
//            bookingCode = this.fromCity + this.toCity,
//            createdAt = "2025-11-09T00:00:00",
//            departureTerminalId = 0,
//            arrivalTerminalId = 0,
//            seatsReserved = this.seats,
//            totalPrice = this.totalPrice.filter { it.isDigit() }.toIntOrNull() ?: 0,
//            bookingStatus = BookingStatus.fromString(this.status),
//            vehicleType = VehicleType.fromString(this.category),
////            rideStatus = this.status,
//            rideStatus = RideStatus.fromString(this.status),
//            driverName = this.vehicle
//        )
//    }
//
//}
//object HistoryOrderItemMapper {
//
//    fun fromFull(full: PassengerRideBookingFull): HistoryOrderItem {
//        val booking = full.booking
//        val ride = full.ride
//        val driver = full.driver
//
//        return HistoryOrderItem(
//            bookingId = booking.id,
//            bookingCode = booking.bookingCode,
//            createdAt = booking.createdAt,
//            departureTerminalId = ride.departureTerminalId,
//            arrivalTerminalId = ride.arrivalTerminalId,
//            seatsReserved = booking.seatsReserved,
//            totalPrice = booking.totalPrice,
//            bookingStatus = BookingStatus.fromString(booking.status),
//            vehicleType = VehicleType.fromString(ride.vehicleType),
//            rideStatus = RideStatus.fromString(ride.rideStatus ?: "pending"),
//            driverName = driver.fullName
//        )
//    }
//
//    // Presentation -> Domain (misal untuk navigasi)
//    fun HistoryItemData.toDomain(): HistoryOrderItem {
//        return HistoryOrderItem(
//            bookingId = id.toIntOrNull() ?: 0,
//            bookingCode = "$fromCity$toCity",
//            createdAt = "2025-11-09T00:00:00",
//            departureTerminalId = 0,
//            arrivalTerminalId = 0,
//            seatsReserved = seats,
//            totalPrice = totalPrice.filter { it.isDigit() }.toIntOrNull() ?: 0,
////            bookingStatus = BookingStatus.fromString(status),
//            bookingStatus = BookingStatus.PENDING,
//            vehicleType = VehicleType.fromString(category),
////            rideStatus = RideStatus.fromString(status),
//            rideStatus = status ?: RideStatus.PENDING,
//            driverName = vehicle
//        )
//    }
//}
//object HistoryOrderItemMapper {
//
//    // Remote → Domain
//    fun fromFull(full: PassengerRideBookingFull): HistoryOrderItem {
//        val booking = full.booking
//        val ride = full.ride
//        val driver = full.driver
//        return HistoryOrderItem(
//            bookingId = booking.id,
//            bookingCode = booking.bookingCode,
//            createdAt = booking.createdAt,
//            departureTerminalId = ride.departureTerminalId,
//            arrivalTerminalId = ride.arrivalTerminalId,
//            seatsReserved = booking.seatsReserved,
//            totalPrice = booking.totalPrice,
//            bookingStatus = BookingStatus.Companion.fromString(booking.status),
//            vehicleType = VehicleType.Companion.fromString(ride.vehicleType),
//            rideStatus = RideStatus.Companion.fromString(ride.rideStatus),
//            driverName = driver.fullName
//        )
//    }
//
//    // Domain → UI
//    fun HistoryOrderItem.toPresentation(): HistoryItemData {
//        val safeVehicle = vehicleType.value
//        val safeStatus = rideStatus ?: RideStatus.PENDING
//        val safePrice = if (totalPrice > 0) "Rp$totalPrice" else "Rp -"
//        return HistoryItemData(
//            id = bookingId.toString(),
//            category = safeVehicle,
//            status = safeStatus,
//            fromCity = departureTerminalId.toString(),
//            toCity = arrivalTerminalId.toString(),
//            fromTerminal = "-",
//            toTerminal = "-",
//            dayDate = createdAt.take(10),
//            time = createdAt.takeLast(8),
//            vehicle = safeVehicle,
//            plate = driverName ?: "-",
//            seats = seatsReserved,
//            totalPrice = safePrice
//        )
//    }
//
//    // UI → Domain (opsional, misal navigasi detail)
//    fun HistoryItemData.toDomain(): HistoryOrderItem {
//        return HistoryOrderItem(
//            bookingId = id.toIntOrNull() ?: 0,
//            bookingCode = "$fromCity$toCity",
//            createdAt = "2025-11-09T00:00:00",
//            departureTerminalId = 0,
//            arrivalTerminalId = 0,
//            seatsReserved = seats,
//            totalPrice = totalPrice.filter { it.isDigit() }.toIntOrNull() ?: 0,
//            bookingStatus = BookingStatus.PENDING,
//            vehicleType = VehicleType.Companion.fromString(category),
//            rideStatus = status ?: RideStatus.PENDING,
//            driverName = vehicle
//        )
//    }
//}


/**
 * ============================================================
 * 🔄 Mapper: HistoryOrderItemMapper
 * ------------------------------------------------------------
 * Bertugas mengonversi data antar-domain:
 * - Dari PassengerRideBookingFull (feature booking)
 * - Ke HistoryOrderItem (feature history order)
 * ============================================================
 */
object HistoryOrderItemMapper {

    /**
     * Mengubah model booking lengkap menjadi item riwayat.
     * Digunakan di use case GetHistoryOrdersUseCase.
     */
    fun fromFull(full: PassengerRideBookingFull): HistoryOrderItem {
        val booking = full.booking
        val ride = full.ride
        val driver = full.driver

        val customer = full.customer

        val mapped = HistoryOrderItem(
            bookingId = booking.id,
            bookingCode = booking.bookingCode,
            createdAt = booking.createdAt,
            departureTerminalId = ride.departureTerminalId,
            arrivalTerminalId = ride.arrivalTerminalId,
            seatsReserved = booking.seatsReserved,
            totalPrice = booking.totalPrice,
            bookingStatus = BookingStatus.fromString(booking.status),
            vehicleType = VehicleType.fromString(ride.vehicleType),
            rideStatus = RideStatus.fromString(ride.rideStatus),
            driverName = driver.fullName,

            customerName = customer.fullName,
            customerId = customer.id,
            averageRating = driver.averageRating
        )

        // 🔍 Debug log agar mudah melacak data yang tidak lengkap
        if (driver.fullName.isBlank() || ride.vehicleType.isBlank()) {
            Log.w(
                "HistoryOrderItemMapper",
                "⚠️ Data tidak lengkap: bookingId=${booking.id}, driver=${driver.fullName}, vehicle=${ride.vehicleType}"
            )
        }

        if (customer.fullName.isBlank()) {
            Log.w(
                "HistoryOrderItemMapper",
                "⚠️ Data tidak lengkap: bookingId=${customer.id}, customer=${customer.fullName}"
            )
        }
        return mapped
    }
}