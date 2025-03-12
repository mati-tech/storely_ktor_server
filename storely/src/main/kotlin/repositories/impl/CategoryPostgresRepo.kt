package com.mati.repositories.impl

import com.mati.dao.CategoryDao
import com.mati.db.CategoryTable
import com.mati.model.Category
import com.mati.repositories.CategoryRepository
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction

class CategoryPostgresRepo : CategoryRepository {

    override suspend fun createCategory(name: String): Category? {
        return newSuspendedTransaction(Dispatchers.IO) {
            CategoryDao.new {
                this.name = name
            }.toModel()
        }
    }

    override suspend fun getCategoryById(categoryId: Int): Category? {
        return newSuspendedTransaction(Dispatchers.IO) {
            CategoryDao.findById(categoryId)?.toModel()
        }
    }

    override suspend fun getCategoryByName(name: String): Category? {
        return newSuspendedTransaction(Dispatchers.IO) {
            CategoryDao.find { CategoryTable.name eq name }
                .firstOrNull()?.toModel()
        }
    }

    override suspend fun getAllCategories(): List<Category> {
        return newSuspendedTransaction(Dispatchers.IO) {
            CategoryDao.all().map { it.toModel() }
        }
    }

    override suspend fun updateCategory(categoryId: Int, newName: String): Boolean {
        return newSuspendedTransaction(Dispatchers.IO) {
            CategoryDao.findById(categoryId)?.let {
                it.name = newName
                true
            } ?: false
        }
    }

    override suspend fun deleteCategory(categoryId: Int): Boolean {
        return newSuspendedTransaction(Dispatchers.IO) {
            CategoryDao.findById(categoryId)?.let {
                it.delete()
                true
            } ?: false
        }
    }

    private fun CategoryDao.toModel() = Category(
        id.value,
        name
    )
}
