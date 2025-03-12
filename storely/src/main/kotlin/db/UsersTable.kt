package com.mati.db

import org.jetbrains.exposed.dao.id.IntIdTable

object UsersTable : IntIdTable("users") {
    val name = varchar("nameusers", 45)
    val email = varchar("emailusers", 45).uniqueIndex()
    val password = varchar("passwordusers", 45)
    val address = text("addressusers").nullable()
    val phone = varchar("phoneusers", 45).nullable()
}