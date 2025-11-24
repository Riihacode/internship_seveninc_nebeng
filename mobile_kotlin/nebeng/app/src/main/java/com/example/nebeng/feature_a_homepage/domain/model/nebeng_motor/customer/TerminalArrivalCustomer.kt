package com.example.nebeng.feature_a_homepage.domain.model.nebeng_motor.customer

import com.example.nebeng.core.utils.PublicTerminalSubtype
import com.example.nebeng.core.utils.TerminalType

data class TerminalArrivalCustomer(
    // Tabel Terminal
    val idArrivalTerminal: Int,
    val arrivalTerminalName: String,
    val terminalFullAddress: String,
    val terminalRegencyId: Int,
    val terminalLongitude: Double,
    val terminalLatitude: Double,
    val publicTerminalSubtype: PublicTerminalSubtype,
    val terminalType: TerminalType
) {
    companion object {
        fun empty() = TerminalArrivalCustomer(
            idArrivalTerminal = 0,
            arrivalTerminalName = "",
            terminalFullAddress = "",
            terminalRegencyId = 0,
            terminalLongitude = 0.0,
            terminalLatitude = 0.0,
            publicTerminalSubtype = PublicTerminalSubtype.TERMINAL_BIS,
            terminalType = TerminalType.PUBLIC
        )
    }
}

