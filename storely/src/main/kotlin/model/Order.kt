package com.mati.model

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.math.BigDecimal
import java.time.LocalDateTime


@Serializable
data class Order (
    val id: Int,
    @Contextual val orderDate: LocalDateTime?,
    val orderStatus: String?,
    @Contextual val totalAmount: BigDecimal,
    val userId: Int
)