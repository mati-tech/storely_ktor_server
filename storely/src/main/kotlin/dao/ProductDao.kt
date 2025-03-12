package com.mati.dao
import com.mati.db.ProductTable
import com.mati.model.Product
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class ProductDao(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<ProductDao>(ProductTable)

    var name by ProductTable.name
    var description by ProductTable.description
    var price by ProductTable.price
    var stock by ProductTable.stock
    var categoryId by ProductTable.categoryId
    var imageUrl by ProductTable.imageUrl
    var createdAt by ProductTable.createdAt

}

// Extension function to convert DAO to Model
