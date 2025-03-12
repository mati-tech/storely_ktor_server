package com.mati.db

import org.jetbrains.exposed.dao.id.IntIdTable

object FavoriteTable : IntIdTable("favorites") {
    val userId = integer("users_idusers").references(UsersTable.id)  // Foreign key to Users table
    val productId = integer("products_idproducts").references(ProductTable.id)  // Foreign key to Products table
    // The unique constraint between users and products
    init {
        index(true, userId, productId)
    }
}