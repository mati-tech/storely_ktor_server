package com.mati.db

import io.ktor.utils.io.core.*
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.javatime.datetime
import org.jetbrains.exposed.sql.javatime.timestamp
import java.time.LocalDateTime

object PaymentsTable : IntIdTable("payments"){
    val paymentDate =  datetime("payment_date").nullable()
    val orderId = integer("orders_idorders").references(OrdersTable.id)
    val paymentMethod = varchar("paymentsmethod", 45)
    val paymentStatus = varchar( "paymentstatus", 45)

}