package com.mati.db

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Table
import java.math.BigDecimal

object OrderItemTable : IntIdTable("order_items") {

    val ordersIdOrders = integer("orders_idorders").references(OrdersTable.id) // Foreign key to Orders table
    val productsIdProducts = integer("products_idproducts").references(ProductTable.id) // Foreign key to Products table
    val orderItemsQuantity = integer("order_items_quantity")
    val orderItemsPrice = decimal("order_items_price", 10, 2) // For price (numeric with 2 decimal points)
}
