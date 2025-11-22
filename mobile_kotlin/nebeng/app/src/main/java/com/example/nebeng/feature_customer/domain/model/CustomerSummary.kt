package com.example.nebeng.feature_customer.domain.model

data class CustomerSummary(
    val id: Int,
    val userId: Int,
    val fullName: String,
    val telephone: String,
    val fullAddress: String,

    val profileImg: String?,
    val verified: Boolean,
    val faceImg: String?,
    val faceWithIdImg: String?,

    val idCardImg: String?,
    val idCardNumber: String,
    val idCardFullName: String,
    val idCardBirthdate: String,

    val creditAmount: Int,
    val createdAt: String,
    val updatedAt: String

) {
    companion object {
        fun getEmpty(): Customer = Customer(
            id = 0,
            userId = 0,
            fullName = "",
            telephone = "",
            fullAddress = "",
            profileImg = null,
            verified = false,
            faceImg = null,
            faceWithIdImg = null,
            idCardImg = null,
            idCardNumber = "",
            idCardFullName = "",
            idCardBirthdate = "",
            creditAmount = 0,
            createdAt = "",
            updatedAt = ""
        )
    }
}