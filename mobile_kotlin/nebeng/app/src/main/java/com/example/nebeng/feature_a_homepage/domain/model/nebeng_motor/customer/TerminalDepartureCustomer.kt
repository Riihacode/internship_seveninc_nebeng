package com.example.nebeng.feature_a_homepage.domain.model.nebeng_motor.customer

import com.example.nebeng.core.utils.PublicTerminalSubtype
import com.example.nebeng.core.utils.TerminalType

data class TerminalDepartureCustomer(
    // Tabel Terminal
    val idDepartureTerminal: Int,
    val departureTerminalName: String,
    val terminalFullAddress: String,
    val terminalRegencyId: Int,
    val terminalLongitude: Double,
    val terminalLatitude: Double,
    val publicTerminalSubtype: PublicTerminalSubtype,
    val terminalType: TerminalType
) {
    companion object {
        fun empty() = TerminalDepartureCustomer(
            idDepartureTerminal = 0,
            departureTerminalName = "",
            terminalFullAddress = "",
            terminalRegencyId = 0,
            terminalLongitude = 0.0,
            terminalLatitude = 0.0,
            publicTerminalSubtype = PublicTerminalSubtype.TERMINAL_BIS,
            terminalType = TerminalType.PUBLIC
        )
    }
}