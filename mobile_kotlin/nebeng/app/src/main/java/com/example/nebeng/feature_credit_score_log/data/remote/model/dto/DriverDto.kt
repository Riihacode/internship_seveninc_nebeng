package com.example.nebeng.feature_credit_score_log.data.remote.model.dto

import com.google.gson.annotations.SerializedName

data class DriverDto(
    @field:SerializedName("credit_score") val creditScore: Int,
    @field:SerializedName("face_img") val faceImg: String,
    @field:SerializedName("driver_license_number") val driverLicenseNumber: String,
    @field:SerializedName("driver_license_img") val driverLicenseImg: String,
    @field:SerializedName("id_card_rejected_reason") val idCardRejectedReason: String,
    @field:SerializedName("police_clearance_certificate_expired") val policeClearanceCertificateExpired: String,
    @field:SerializedName("police_clearance_certificate_number") val policeClearanceCertificateNumber: String,
    @field:SerializedName("created_at") val createdAt: String,
    @field:SerializedName("full_address") val fullAddress: String,
    @field:SerializedName("face_with_id_img") val faceWithIdImg: String,
    @field:SerializedName("police_clearance_verified") val policeClearanceVerified: Int,
    @field:SerializedName("driver_license_type") val driverLicenseType: String,
    @field:SerializedName("id_card_img") val idCardImg: String,
    @field:SerializedName("police_clearance_certificate_fullname") val policeClearanceCertificateFullname: String,
    @field:SerializedName("balance") val balance: Int,
    @field:SerializedName("updated_at") val updatedAt: String,
    @field:SerializedName("total_rating") val totalRating: Int,
    @field:SerializedName("id") val id: Int,
    @field:SerializedName("driver_license_rejected_reason") val driverLicenseRejectedReason: String,
    @field:SerializedName("id_card_fullname") val idCardFullname: String,
    @field:SerializedName("telephone") val telephone: String,
    @field:SerializedName("average_rating") val averageRating: Int,
    @field:SerializedName("id_card_verified") val idCardVerified: Boolean,
    @field:SerializedName("police_clearance_certificate_img") val policeClearanceCertificateImg: String,
    @field:SerializedName("rating_count") val ratingCount: Int,
    @field:SerializedName("profile_img") val profileImg: String,
    @field:SerializedName("full_name") val fullName: String,
    @field:SerializedName("user_id") val userId: Int,
    @field:SerializedName("driver_license_verified") val driverLicenseVerified: Boolean,
    @field:SerializedName("police_clearance_rejected_reason") val policeClearanceRejectedReason: String,
    @field:SerializedName("id_card_number") val idCardNumber: String,
    @field:SerializedName("driver_license_expired") val driverLicenseExpired: String,
    @field:SerializedName("id_card_birthdate") val idCardBirthdate: String
)
