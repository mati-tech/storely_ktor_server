package com.mati.model

import kotlinx.serialization.Contextual
import java.time.LocalDateTime
import java.util.Date

data class Payment (
    val idpayment: Int,
    val paymentDate: LocalDateTime?,
    val orderId: Int,
    val paymentMethod: String,
    val paymentStatus: String,
)