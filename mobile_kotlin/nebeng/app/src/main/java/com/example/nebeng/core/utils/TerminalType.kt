package com.example.nebeng.core.utils

enum class TerminalType(val value: String) {
    PUBLIC("public"),
    PRIVATE("private");

    companion object {
        fun fromString(value: String): TerminalType =
            entries.find { it.value.equals(value, ignoreCase = true)} ?: PUBLIC
    }
}