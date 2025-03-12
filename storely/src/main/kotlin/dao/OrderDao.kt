package com.mati.dao

import com.mati.db.OrdersTable
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class OrderDao(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<OrderDao>(OrdersTable)
    var orderDate by OrdersTable.orderDate
    var orderStatus by OrdersTable.orderStatus
    var totalAmount by OrdersTable.totalAmount
    var userId by OrdersTable.userId
}
