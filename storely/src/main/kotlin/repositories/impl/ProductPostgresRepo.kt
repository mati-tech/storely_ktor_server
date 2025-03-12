package com.mati.repositories.impl

import com.mati.dao.ProductDao
import com.mati.db.ProductTable
import com.mati.model.Product
import com.mati.repositories.ProductRepository
import org.jetbrains.exposed.sql.transactions.transaction

class ProductPostgresRepo : ProductRepository {
    override suspend fun findById(id: Int): Product? {
        return transaction {
            ProductDao.findById(id)?.toProduct()
        }
    }

    override suspend fun findAll(): List<Product> {
        return transaction {
            ProductDao.all().map { it.toProduct() }
        }
    }

    override suspend fun findByName(name: String): List<Product> {
        return transaction {
            ProductDao.find { ProductTable.name eq name }
                .map { it.toProduct() }
        }
    }

    override suspend fun add(product: Product): Product {
        return transaction {
            ProductDao.new {
                name = product.name
                description = product.description
                price = product.price
                stock = product.stock
                categoryId = product.categoryId
                imageUrl = product.imageUrl
                createdAt = product.createdAt
            }.toProduct()
        }
    }

    override suspend fun update(id: Int, product: Product): Boolean {
        return transaction {
            val existingProduct = ProductDao.findById(id) ?: return@transaction false
            existingProduct.apply {
                name = product.name
                description = product.description
                price = product.price
                stock = product.stock
                categoryId = product.categoryId
                imageUrl = product.imageUrl
                createdAt = product.createdAt
            }
            true
        }
    }

    override suspend fun deleteById(id: Int): Boolean {
        return transaction {
            val existingProduct = ProductDao.findById(id) ?: return@transaction false
            existingProduct.delete()
            true
        }
    }

    override suspend fun findByCategoryId(categoryId: Int): List<Product> {
        return transaction {
            ProductDao.find { ProductTable.categoryId eq categoryId }
                .map { it.toProduct() }
        }
    }

    private fun ProductDao.toProduct() = Product(
        id = id.value,
        name = name,
        description = description,
        price = price,
        stock = stock,
        categoryId = categoryId,
        imageUrl = imageUrl,
        createdAt = createdAt
    )
}