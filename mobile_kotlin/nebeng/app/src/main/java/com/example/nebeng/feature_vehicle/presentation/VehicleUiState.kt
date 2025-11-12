package com.example.nebeng.feature_vehicle.presentation

import com.example.nebeng.feature_vehicle.domain.model.Vehicle

/**
 * State UI untuk feature Vehicle.
 * Menangani seluruh proses (loading, error, data, flags create/update/delete/verify)
 */
data class VehicleUiState(
    val isLoading: Boolean = false,
    val error: String? = null,

    // Data utama
    val vehicles: List<Vehicle> = emptyList(),
    val selectedVehicle: Vehicle? = null,
    val createdVehicle: Vehicle? = null,

    // Flags state untuk UI reactivity
    val isCreated: Boolean = false,
    val isUpdated: Boolean = false,
    val isDeleted: Boolean = false,
    val isVerifiedTrue: Boolean = false,
    val isVerifiedFalse: Boolean = false
)