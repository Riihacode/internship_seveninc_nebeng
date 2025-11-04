package com.example.nebeng.feature_driver.presentation

import com.example.nebeng.feature_driver.domain.model.Driver

data class DriverUiState(
    val isLoading: Boolean = false,
    val drivers: List<Driver> = emptyList(),
    val selectedDriver: Driver? = null,
    val error: String? = null
)