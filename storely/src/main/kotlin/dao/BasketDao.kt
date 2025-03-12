package com.mati.dao

import com.mati.db.BasketTable

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class BasketDao(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<BasketDao>(BasketTable)

    var userId by BasketTable.userId
    var productId by BasketTable.productId
    var quantity by BasketTable.quantity
    var addedAt by BasketTable.addedAt

    // Optionally, you can add relationships to other tables for convenience
    val user by UserDao referencedOn BasketTable.userId
    val product by ProductDao referencedOn BasketTable.productId
}
