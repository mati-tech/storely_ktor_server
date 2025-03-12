package com.mati.db

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.SqlExpressionBuilder
import org.jetbrains.exposed.sql.javatime.CurrentTimestamp
import org.jetbrains.exposed.sql.javatime.datetime
import java.time.LocalDateTime

object BasketTable : IntIdTable("basket") {
    val userId = integer("user_id").references(UsersTable.id) // Foreign key to Users table
    val productId = integer("product_id").references(ProductTable.id) // Foreign key to Products table
    val quantity = integer("quantity").default(1)
    val addedAt = datetime("added_at")
    // Unique constraint to avoid duplicate entries for a user-product pair
    init {
        uniqueIndex(userId, productId)
    }
}
