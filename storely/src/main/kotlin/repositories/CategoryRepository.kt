package com.mati.repositories

import com.mati.model.Category

interface CategoryRepository {
    suspend fun createCategory(name: String): Category?
    suspend fun getCategoryById(categoryId: Int): Category?
    suspend fun getCategoryByName(name: String): Category?
    suspend fun getAllCategories(): List<Category>
    suspend fun updateCategory(categoryId: Int, newName: String): Boolean
    suspend fun deleteCategory(categoryId: Int): Boolean
}
