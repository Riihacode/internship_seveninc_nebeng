package com.example.nebeng.feature_terminal.data.remote.model.mapper

import com.example.nebeng.core.utils.PublicTerminalSubtype
import com.example.nebeng.core.utils.TerminalType
import com.example.nebeng.feature_terminal.data.remote.model.dto.DataDto
import com.example.nebeng.feature_terminal.domain.model.Terminal


object TerminalMapper {

    /**
     * Convert a single Terminal DataDto into domain model Terminal
     * Null-safe: automatically assigns default values if the API response lacks fields.
     */
    fun toDomain(dto: DataDto?): Terminal {
        val terminalType = dto?.terminalType?.trim()?.let {
            TerminalType.fromString(it)
        } ?: TerminalType.PUBLIC

        val subtype = dto?.publicTerminalSubtype?.trim()?.let {
            PublicTerminalSubtype.fromString(it)
        } ?: PublicTerminalSubtype.TERMINAL_BIS

        return Terminal(
            id = dto?.id ?: 0,
            name = dto?.name.orEmpty(),
            terminalType = terminalType,
            publicTerminalSubtype = subtype,
            provinceId = dto?.provinceId ?: 0,
            regencyId = dto?.regencyId ?: 0,
            districtId = dto?.districtId ?: 0,
            fullAddress = dto?.fullAddress.orEmpty(),
            longitude = dto?.longitude ?: 0.0,
            latitude = dto?.latitude ?: 0.0,
            createdAt = dto?.createdAt.orEmpty(),
            updatedAt = dto?.updatedAt.orEmpty()
        )
    }

    /**
     * Convert list of DataDto to list of Terminal (safe for nulls)
     */
    fun toDomainList(dtoList: List<DataDto?>?): List<Terminal> {
        return dtoList?.map { toDomain(it) } ?: emptyList()
    }
}