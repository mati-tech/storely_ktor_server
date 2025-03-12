package com.mati.db

import org.jetbrains.exposed.dao.id.IntIdTable

object CategoryTable : IntIdTable("categories") {
    val name = varchar("namecategories", 45).uniqueIndex()  // Unique constraint on category name
}
