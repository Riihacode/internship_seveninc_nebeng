package com.example.nebeng.core.utils

enum class PublicTerminalSubtype(val label: String) {
    TERMINAL_BIS("Terminal Bis"),
    STASIUN_KERETA("Stasiun Kereta"),
    BANDARA("Bandara"),
    PELABUHAN("Pelabuhan");

    companion object {
        fun fromString(value: String): PublicTerminalSubtype =
            entries.find { it.label.equals(value, ignoreCase = true) } ?: TERMINAL_BIS
    }
}
