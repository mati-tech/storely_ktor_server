package com.mati.db

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.javatime.datetime

object OrdersTable : IntIdTable("orders") {

    val orderDate = datetime("order_date").nullable()  // Nullable field
    val orderStatus = varchar("order_status", 50).nullable()  // Nullable field
    val totalAmount = decimal("total_amount_order", 10, 2)
    val userId = integer("users_idusers").references(UsersTable.id)  // Foreign key to Users table
}
