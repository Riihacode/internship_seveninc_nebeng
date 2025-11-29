package com.example.nebeng.feature_a_homepage.domain.session.customer.nebeng_motor

enum class BookingStep {
    SELECT_RIDE,
    CONFIRM_PRICE,
    BOOKING_CREATED,
    TRANSACTION_CREATED,
    WAITING_PAYMENT,
    PAYMENT_CONFIRMED,

    WAITING_RIDE_ACCEPTANCE,
    RIDE_ACCEPTED,

    FAILED
}