package com.mati.repositories.impl

import com.mati.dao.OrderItemDao
import com.mati.db.OrderItemTable
import com.mati.model.OrderItem
import com.mati.repositories.OrderItemRepository
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import java.math.BigDecimal

class OrderItemPostgresRepos : OrderItemRepository {

    override suspend fun createOrderItem(orderId: Int, productId: Int, quantity: Int, price: BigDecimal): OrderItem? {
        return newSuspendedTransaction(Dispatchers.IO) {
            OrderItemDao.new {
                this.ordersIdOrders = orderId
                this.productsIdProducts = productId
                this.orderItemsQuantity = quantity
                this.orderItemsPrice = price
            }.toModel()
        }
    }

    override suspend fun getOrderItemById(orderItemId: Int): OrderItem? {
        return newSuspendedTransaction(Dispatchers.IO) {
            OrderItemDao.findById(orderItemId)?.toModel()
        }
    }

    override suspend fun getOrderItemsByOrderId(orderId: Int): List<OrderItem> {
        return newSuspendedTransaction(Dispatchers.IO) {
            OrderItemDao.find { OrderItemTable.ordersIdOrders eq orderId }
                .map { it.toModel() }
        }
    }


    override suspend fun updateOrderItem(orderItemId: Int, quantity: Int, price: BigDecimal): Boolean {
        return newSuspendedTransaction(Dispatchers.IO) {
            OrderItemDao.findById(orderItemId)?.let {
                it.orderItemsQuantity = quantity
                it.orderItemsPrice = price
                true
            } ?: false
        }
    }

    override suspend fun deleteOrderItem(orderItemId: Int): Boolean {
        return newSuspendedTransaction(Dispatchers.IO) {
            OrderItemDao.findById(orderItemId)?.let {
                it.delete()
                true
            } ?: false
        }
    }

    private fun OrderItemDao.toModel() = OrderItem(
        id.value,
        ordersIdOrders,
        productsIdProducts,
        orderItemsQuantity,
        orderItemsPrice
    )
}
