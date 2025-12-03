package com.example.nebeng.feature_a_homepage.domain.model.nebeng_motor.customer

data class HomepageUser(
    val userId: Int,
    val name: String,
    val username: String,
    val userType: String,
    val token: String,
    val customerId: Int,
)