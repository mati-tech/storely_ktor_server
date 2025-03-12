package com.mati.repositories

import com.mati.model.Product


interface ProductRepository {

    // Find a product by its ID
    suspend fun findById(id: Int): Product?

    // Get all products
    suspend fun findAll(): List<Product>

    // Add a new product
    suspend fun add(product: Product): Product

    // Update an existing product
     suspend fun update(id: Int, product: Product): Boolean

    // Delete a product by its ID
     suspend fun findByName(name: String): List<Product>
     suspend fun deleteById(id: Int): Boolean

    // Find products by category ID
     suspend fun findByCategoryId(categoryId: Int): List<Product>

}