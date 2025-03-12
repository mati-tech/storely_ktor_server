package com.mati.dao

import com.mati.db.OrderItemTable
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

// Define the DAO class for OrderItems
class OrderItemDao(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<OrderItemDao>(OrderItemTable)

    // Define the properties for each column in the OrderItems table
    var ordersIdOrders by OrderItemTable.ordersIdOrders
    var productsIdProducts by OrderItemTable.productsIdProducts
    var orderItemsQuantity by OrderItemTable.orderItemsQuantity
    var orderItemsPrice by OrderItemTable.orderItemsPrice
}