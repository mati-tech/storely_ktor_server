package com.mati.model

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.math.BigDecimal

@Serializable
data class OrderItem(
    val idOrderItems: Int,
    val ordersIdOrders: Int,
    val productsIdProducts: Int,
    val orderItemsQuantity: Int,
    @Contextual val orderItemsPrice: BigDecimal
)
