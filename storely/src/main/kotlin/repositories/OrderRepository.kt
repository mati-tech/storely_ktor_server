package com.mati.repositories

import com.mati.model.Order
import java.math.BigDecimal
import java.time.LocalDateTime

interface OrderRepository {

    // Create a new order
    suspend fun createOrder(orderDate: LocalDateTime?, orderStatus: String?, totalAmount: BigDecimal, userId: Int): Order?

    // Get an order by its ID
    suspend fun getOrderById(orderId: Int): Order?

    // Get all orders for a user
    suspend fun getOrdersByUserId(userId: Int): List<Order>

    // Update an order's status
    suspend fun updateOrderStatus(orderId: Int, newStatus: String): Boolean

    // Delete an order
    suspend fun deleteOrder(orderId: Int): Boolean
}
