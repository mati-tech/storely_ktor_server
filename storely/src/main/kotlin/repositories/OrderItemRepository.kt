package com.mati.repositories

import com.mati.model.OrderItem
import java.math.BigDecimal

interface OrderItemRepository {
    suspend fun createOrderItem(orderId: Int, productId: Int, quantity: Int, price: BigDecimal): OrderItem?
    suspend fun getOrderItemById(orderItemId: Int): OrderItem?
    suspend fun getOrderItemsByOrderId(orderId: Int): List<OrderItem>
    suspend fun updateOrderItem(orderItemId: Int, quantity: Int, price: BigDecimal): Boolean
    suspend fun deleteOrderItem(orderItemId: Int): Boolean
}
