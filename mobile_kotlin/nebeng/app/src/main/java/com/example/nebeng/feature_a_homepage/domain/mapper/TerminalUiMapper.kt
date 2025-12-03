package com.example.nebeng.feature_a_homepage.domain.mapper

import com.example.nebeng.feature_a_homepage.domain.model.nebeng_motor.customer.TerminalCustomer
import com.example.nebeng.feature_a_homepage.presentation.screen_role.customer.nebeng_motor.page_01.bottom_sheet.LocationUiModel

// Generic mapper untuk UI (dipakai di PassengerRideMotorScreen)
fun TerminalCustomer.toLocationUi() = LocationUiModel(
    id = id,
    name = name,
    fullAddress = terminalFullAddress,
    regency = regencyName,
    rawTerminal = this
)

fun TerminalCustomer.toDepartureLocationUi() = LocationUiModel(
    id = id,
    name = name,
    fullAddress = terminalFullAddress,
    regency = regencyName,
    rawTerminal = this
)

// Keduanya identik, dibuat hanya jika suatu saat perlu dibedakan
fun TerminalCustomer.toArrivalLocationUi() = LocationUiModel(
    id = id,
    name = name,
    fullAddress = terminalFullAddress,
    regency = regencyName,
    rawTerminal = this
)